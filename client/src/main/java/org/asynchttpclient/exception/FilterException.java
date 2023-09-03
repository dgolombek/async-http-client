/*
 *    Copyright (c) 2023 AsyncHttpClient Project. All rights reserved.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.asynchttpclient.exception;

import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.filter.RequestFilter;
import org.asynchttpclient.filter.ResponseFilter;

/**
 * An exception that can be thrown by an {@link AsyncHandler} to interrupt invocation of
 * the {@link RequestFilter} and {@link ResponseFilter}. It also interrupts the request and response processing.
 */
public class FilterException extends Exception {

    private static final long serialVersionUID = -3963344749394925069L;

    public FilterException(final String message) {
        super(message);
    }

    public FilterException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
