/*
 * Copyright (c) 2010-2012 Sonatype, Inc. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
package org.asynchttpclient.webdav;

import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.HttpResponseStatus;
import org.asynchttpclient.Response;
import org.asynchttpclient.netty.NettyResponse;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Simple {@link AsyncHandler} that add support for WebDav's response manipulation.
 *
 * @param <T> the result type
 */
public abstract class WebDavCompletionHandlerBase<T> implements AsyncHandler<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebDavCompletionHandlerBase.class);
    private static final DocumentBuilderFactory DOCUMENT_BUILDER_FACTORY;
    private final List<HttpResponseBodyPart> bodyParts = Collections.synchronizedList(new ArrayList<>());
    private @Nullable HttpResponseStatus status;
    private @Nullable HttpHeaders headers;

    static {
        DOCUMENT_BUILDER_FACTORY = DocumentBuilderFactory.newInstance();
        if (Boolean.getBoolean("org.asynchttpclient.webdav.enableDtd")) {
            try {
                DOCUMENT_BUILDER_FACTORY.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            } catch (ParserConfigurationException e) {
                LOGGER.error("Failed to disable doctype declaration");
                throw new ExceptionInInitializerError(e);
            }
        }
    }

    @Override
    public final State onBodyPartReceived(final HttpResponseBodyPart content) {
        bodyParts.add(content);
        return State.CONTINUE;
    }

    @Override
    public final State onStatusReceived(final HttpResponseStatus status) {
        this.status = status;
        return State.CONTINUE;
    }

    @Override
    public final State onHeadersReceived(final HttpHeaders headers) {
        this.headers = headers;
        return State.CONTINUE;
    }

    private Document readXMLResponse(InputStream stream, HttpResponseStatus initialStatus) {
        Document document;
        try {
            document = DOCUMENT_BUILDER_FACTORY.newDocumentBuilder().parse(stream);
            status = parse(document, initialStatus);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return document;
    }

    private static HttpResponseStatus parse(Document document, HttpResponseStatus initialStatus) {
        HttpResponseStatus status = initialStatus;
        Element element = document.getDocumentElement();
        NodeList statusNode = element.getElementsByTagName("status");
        for (int i = 0; i < statusNode.getLength(); i++) {
            Node node = statusNode.item(i);

            String value = node.getFirstChild().getNodeValue();
            int statusCode = Integer.parseInt(value.substring(value.indexOf(' '), value.lastIndexOf(' ')).trim());
            String statusText = value.substring(value.lastIndexOf(' '));
            status = new HttpStatusWrapper(status, statusText, statusCode);
        }
        return status;
    }

    @Override
    public final T onCompleted() throws Exception {
        if (status != null) {
            Document document = null;
            if (status.getStatusCode() == 207) {
                document = readXMLResponse(new NettyResponse(status, headers, bodyParts).getResponseBodyAsStream(), status);
            }
            // recompute response as readXMLResponse->parse might have updated it
            return onCompleted(new WebDavResponse(new NettyResponse(status, headers, bodyParts), document));
        } else {
            throw new IllegalStateException("Status is null");
        }
    }

    @Override
    public void onThrowable(Throwable t) {
        LOGGER.debug(t.getMessage(), t);
    }

    /**
     * Invoked once the HTTP response has been fully read.
     *
     * @param response The {@link Response}
     * @return Type of the value that will be returned by the associated {@link Future}
     * @throws Exception if something wrong happens
     */
    public abstract T onCompleted(WebDavResponse response) throws Exception;

    private static class HttpStatusWrapper extends HttpResponseStatus {

        private final HttpResponseStatus wrapped;

        private final String statusText;

        private final int statusCode;

        HttpStatusWrapper(HttpResponseStatus wrapper, String statusText, int statusCode) {
            super(wrapper.getUri());
            wrapped = wrapper;
            this.statusText = statusText;
            this.statusCode = statusCode;
        }

        @Override
        public int getStatusCode() {
            return statusText == null ? wrapped.getStatusCode() : statusCode;
        }

        @Override
        public String getStatusText() {
            return statusText == null ? wrapped.getStatusText() : statusText;
        }

        @Override
        public String getProtocolName() {
            return wrapped.getProtocolName();
        }

        @Override
        public int getProtocolMajorVersion() {
            return wrapped.getProtocolMajorVersion();
        }

        @Override
        public int getProtocolMinorVersion() {
            return wrapped.getProtocolMinorVersion();
        }

        @Override
        public String getProtocolText() {
            return wrapped.getStatusText();
        }

        @Override
        public SocketAddress getRemoteAddress() {
            return wrapped.getRemoteAddress();
        }

        @Override
        public SocketAddress getLocalAddress() {
            return wrapped.getLocalAddress();
        }
    }
}
