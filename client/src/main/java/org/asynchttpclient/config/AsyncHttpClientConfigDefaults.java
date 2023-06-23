/*
 *    Copyright (c) 2014-2023 AsyncHttpClient Project. All rights reserved.
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
package org.asynchttpclient.config;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public final class AsyncHttpClientConfigDefaults {

    public static final String ASYNC_CLIENT_CONFIG_ROOT = "org.asynchttpclient.";
    public static final String THREAD_POOL_NAME_CONFIG = "threadPoolName";
    public static final String MAX_CONNECTIONS_CONFIG = "maxConnections";
    public static final String MAX_CONNECTIONS_PER_HOST_CONFIG = "maxConnectionsPerHost";
    public static final String ACQUIRE_FREE_CHANNEL_TIMEOUT = "acquireFreeChannelTimeout";
    public static final String CONNECTION_TIMEOUT_CONFIG = "connectTimeout";
    public static final String POOLED_CONNECTION_IDLE_TIMEOUT_CONFIG = "pooledConnectionIdleTimeout";
    public static final String CONNECTION_POOL_CLEANER_PERIOD_CONFIG = "connectionPoolCleanerPeriod";
    public static final String READ_TIMEOUT_CONFIG = "readTimeout";
    public static final String REQUEST_TIMEOUT_CONFIG = "requestTimeout";
    public static final String CONNECTION_TTL_CONFIG = "connectionTtl";
    public static final String FOLLOW_REDIRECT_CONFIG = "followRedirect";
    public static final String MAX_REDIRECTS_CONFIG = "maxRedirects";
    public static final String COMPRESSION_ENFORCED_CONFIG = "compressionEnforced";

    public static final String ENABLE_AUTOMATIC_DECOMPRESSION_CONFIG = "enableAutomaticDecompression";
    public static final String USER_AGENT_CONFIG = "userAgent";
    public static final String ENABLED_PROTOCOLS_CONFIG = "enabledProtocols";
    public static final String ENABLED_CIPHER_SUITES_CONFIG = "enabledCipherSuites";
    public static final String FILTER_INSECURE_CIPHER_SUITES_CONFIG = "filterInsecureCipherSuites";
    public static final String USE_PROXY_SELECTOR_CONFIG = "useProxySelector";
    public static final String USE_PROXY_PROPERTIES_CONFIG = "useProxyProperties";
    public static final String VALIDATE_RESPONSE_HEADERS_CONFIG = "validateResponseHeaders";
    public static final String AGGREGATE_WEBSOCKET_FRAME_FRAGMENTS_CONFIG = "aggregateWebSocketFrameFragments";
    public static final String ENABLE_WEBSOCKET_COMPRESSION_CONFIG = "enableWebSocketCompression";
    public static final String STRICT_302_HANDLING_CONFIG = "strict302Handling";
    public static final String KEEP_ALIVE_CONFIG = "keepAlive";
    public static final String MAX_REQUEST_RETRY_CONFIG = "maxRequestRetry";
    public static final String DISABLE_URL_ENCODING_FOR_BOUND_REQUESTS_CONFIG = "disableUrlEncodingForBoundRequests";
    public static final String USE_LAX_COOKIE_ENCODER_CONFIG = "useLaxCookieEncoder";
    public static final String USE_OPEN_SSL_CONFIG = "useOpenSsl";
    public static final String USE_INSECURE_TRUST_MANAGER_CONFIG = "useInsecureTrustManager";
    public static final String DISABLE_HTTPS_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG = "disableHttpsEndpointIdentificationAlgorithm";
    public static final String SSL_SESSION_CACHE_SIZE_CONFIG = "sslSessionCacheSize";
    public static final String SSL_SESSION_TIMEOUT_CONFIG = "sslSessionTimeout";
    public static final String TCP_NO_DELAY_CONFIG = "tcpNoDelay";
    public static final String SO_REUSE_ADDRESS_CONFIG = "soReuseAddress";
    public static final String SO_KEEP_ALIVE_CONFIG = "soKeepAlive";
    public static final String SO_LINGER_CONFIG = "soLinger";
    public static final String SO_SND_BUF_CONFIG = "soSndBuf";
    public static final String SO_RCV_BUF_CONFIG = "soRcvBuf";
    public static final String HTTP_CLIENT_CODEC_MAX_INITIAL_LINE_LENGTH_CONFIG = "httpClientCodecMaxInitialLineLength";
    public static final String HTTP_CLIENT_CODEC_MAX_HEADER_SIZE_CONFIG = "httpClientCodecMaxHeaderSize";
    public static final String HTTP_CLIENT_CODEC_MAX_CHUNK_SIZE_CONFIG = "httpClientCodecMaxChunkSize";
    public static final String HTTP_CLIENT_CODEC_INITIAL_BUFFER_SIZE_CONFIG = "httpClientCodecInitialBufferSize";
    public static final String HTTP_CLIENT_CODEC_PARSE_HTTP_AFTER_CONNECT_REQUEST = "httpClientCodecParseHttpAfterConnectRequest";
    public static final String HTTP_CLIENT_CODEC_ALLOW_DUPLICATE_CONTENT_LENGTHS = "httpClientCodecAllowDuplicateContentLengths";
    public static final String DISABLE_ZERO_COPY_CONFIG = "disableZeroCopy";
    public static final String HANDSHAKE_TIMEOUT_CONFIG = "handshakeTimeout";
    public static final String CHUNKED_FILE_CHUNK_SIZE_CONFIG = "chunkedFileChunkSize";
    public static final String WEBSOCKET_MAX_BUFFER_SIZE_CONFIG = "webSocketMaxBufferSize";
    public static final String WEBSOCKET_MAX_FRAME_SIZE_CONFIG = "webSocketMaxFrameSize";
    public static final String KEEP_ENCODING_HEADER_CONFIG = "keepEncodingHeader";
    public static final String SHUTDOWN_QUIET_PERIOD_CONFIG = "shutdownQuietPeriod";
    public static final String SHUTDOWN_TIMEOUT_CONFIG = "shutdownTimeout";
    public static final String USE_NATIVE_TRANSPORT_CONFIG = "useNativeTransport";
    public static final String USE_ONLY_EPOLL_NATIVE_TRANSPORT = "useOnlyEpollNativeTransport";
    public static final String IO_THREADS_COUNT_CONFIG = "ioThreadsCount";
    public static final String HASHED_WHEEL_TIMER_TICK_DURATION = "hashedWheelTimerTickDuration";
    public static final String HASHED_WHEEL_TIMER_SIZE = "hashedWheelTimerSize";
    public static final String EXPIRED_COOKIE_EVICTION_DELAY = "expiredCookieEvictionDelay";

    public static final String AHC_VERSION;

    static {
        try (InputStream is = AsyncHttpClientConfigDefaults.class.getResourceAsStream("ahc-version.properties")) {
            Properties prop = new Properties();
            prop.load(is);
            AHC_VERSION = prop.getProperty("ahc.version", "UNKNOWN");
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private AsyncHttpClientConfigDefaults() {
    }

    public static String defaultThreadPoolName() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getString(ASYNC_CLIENT_CONFIG_ROOT + THREAD_POOL_NAME_CONFIG);
    }

    public static int defaultMaxConnections() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getInt(ASYNC_CLIENT_CONFIG_ROOT + MAX_CONNECTIONS_CONFIG);
    }

    public static int defaultMaxConnectionsPerHost() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getInt(ASYNC_CLIENT_CONFIG_ROOT + MAX_CONNECTIONS_PER_HOST_CONFIG);
    }

    public static int defaultAcquireFreeChannelTimeout() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getInt(ASYNC_CLIENT_CONFIG_ROOT + ACQUIRE_FREE_CHANNEL_TIMEOUT);
    }

    public static Duration defaultConnectTimeout() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getDuration(ASYNC_CLIENT_CONFIG_ROOT + CONNECTION_TIMEOUT_CONFIG);
    }

    public static Duration defaultPooledConnectionIdleTimeout() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getDuration(ASYNC_CLIENT_CONFIG_ROOT + POOLED_CONNECTION_IDLE_TIMEOUT_CONFIG);
    }

    public static Duration defaultConnectionPoolCleanerPeriod() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getDuration(ASYNC_CLIENT_CONFIG_ROOT + CONNECTION_POOL_CLEANER_PERIOD_CONFIG);
    }

    public static Duration defaultReadTimeout() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getDuration(ASYNC_CLIENT_CONFIG_ROOT + READ_TIMEOUT_CONFIG);
    }

    public static Duration defaultRequestTimeout() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getDuration(ASYNC_CLIENT_CONFIG_ROOT + REQUEST_TIMEOUT_CONFIG);
    }

    public static Duration defaultConnectionTtl() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getDuration(ASYNC_CLIENT_CONFIG_ROOT + CONNECTION_TTL_CONFIG);
    }

    public static boolean defaultFollowRedirect() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + FOLLOW_REDIRECT_CONFIG);
    }

    public static int defaultMaxRedirects() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getInt(ASYNC_CLIENT_CONFIG_ROOT + MAX_REDIRECTS_CONFIG);
    }

    public static boolean defaultCompressionEnforced() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + COMPRESSION_ENFORCED_CONFIG);
    }

    public static boolean defaultEnableAutomaticDecompression() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + ENABLE_AUTOMATIC_DECOMPRESSION_CONFIG);
    }

    public static String defaultUserAgent() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getString(ASYNC_CLIENT_CONFIG_ROOT + USER_AGENT_CONFIG);
    }

    public static @Nullable String[] defaultEnabledProtocols() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getStringArray(ASYNC_CLIENT_CONFIG_ROOT + ENABLED_PROTOCOLS_CONFIG);
    }

    public static @Nullable String[] defaultEnabledCipherSuites() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getStringArray(ASYNC_CLIENT_CONFIG_ROOT + ENABLED_CIPHER_SUITES_CONFIG);
    }

    public static boolean defaultFilterInsecureCipherSuites() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + FILTER_INSECURE_CIPHER_SUITES_CONFIG);
    }

    public static boolean defaultUseProxySelector() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + USE_PROXY_SELECTOR_CONFIG);
    }

    public static boolean defaultUseProxyProperties() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + USE_PROXY_PROPERTIES_CONFIG);
    }

    public static boolean defaultValidateResponseHeaders() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + VALIDATE_RESPONSE_HEADERS_CONFIG);
    }

    public static boolean defaultAggregateWebSocketFrameFragments() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + AGGREGATE_WEBSOCKET_FRAME_FRAGMENTS_CONFIG);
    }

    public static boolean defaultEnableWebSocketCompression() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + ENABLE_WEBSOCKET_COMPRESSION_CONFIG);
    }

    public static boolean defaultStrict302Handling() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + STRICT_302_HANDLING_CONFIG);
    }

    public static boolean defaultKeepAlive() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + KEEP_ALIVE_CONFIG);
    }

    public static int defaultMaxRequestRetry() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getInt(ASYNC_CLIENT_CONFIG_ROOT + MAX_REQUEST_RETRY_CONFIG);
    }

    public static boolean defaultDisableUrlEncodingForBoundRequests() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + DISABLE_URL_ENCODING_FOR_BOUND_REQUESTS_CONFIG);
    }

    public static boolean defaultUseLaxCookieEncoder() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + USE_LAX_COOKIE_ENCODER_CONFIG);
    }

    public static boolean defaultUseOpenSsl() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + USE_OPEN_SSL_CONFIG);
    }

    public static boolean defaultUseInsecureTrustManager() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + USE_INSECURE_TRUST_MANAGER_CONFIG);
    }

    public static boolean defaultDisableHttpsEndpointIdentificationAlgorithm() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + DISABLE_HTTPS_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG);
    }

    public static int defaultSslSessionCacheSize() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getInt(ASYNC_CLIENT_CONFIG_ROOT + SSL_SESSION_CACHE_SIZE_CONFIG);
    }

    public static int defaultSslSessionTimeout() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getInt(ASYNC_CLIENT_CONFIG_ROOT + SSL_SESSION_TIMEOUT_CONFIG);
    }

    public static boolean defaultTcpNoDelay() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + TCP_NO_DELAY_CONFIG);
    }

    public static boolean defaultSoReuseAddress() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + SO_REUSE_ADDRESS_CONFIG);
    }

    public static boolean defaultSoKeepAlive() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + SO_KEEP_ALIVE_CONFIG);
    }

    public static int defaultSoLinger() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getInt(ASYNC_CLIENT_CONFIG_ROOT + SO_LINGER_CONFIG);
    }

    public static int defaultSoSndBuf() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getInt(ASYNC_CLIENT_CONFIG_ROOT + SO_SND_BUF_CONFIG);
    }

    public static int defaultSoRcvBuf() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getInt(ASYNC_CLIENT_CONFIG_ROOT + SO_RCV_BUF_CONFIG);
    }

    public static int defaultHttpClientCodecMaxInitialLineLength() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getInt(ASYNC_CLIENT_CONFIG_ROOT + HTTP_CLIENT_CODEC_MAX_INITIAL_LINE_LENGTH_CONFIG);
    }

    public static int defaultHttpClientCodecMaxHeaderSize() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getInt(ASYNC_CLIENT_CONFIG_ROOT + HTTP_CLIENT_CODEC_MAX_HEADER_SIZE_CONFIG);
    }

    public static int defaultHttpClientCodecMaxChunkSize() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getInt(ASYNC_CLIENT_CONFIG_ROOT + HTTP_CLIENT_CODEC_MAX_CHUNK_SIZE_CONFIG);
    }

    public static int defaultHttpClientCodecInitialBufferSize() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getInt(ASYNC_CLIENT_CONFIG_ROOT + HTTP_CLIENT_CODEC_INITIAL_BUFFER_SIZE_CONFIG);
    }

    public static boolean defaultHttpClientCodecParseHttpAfterConnectRequest() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + HTTP_CLIENT_CODEC_PARSE_HTTP_AFTER_CONNECT_REQUEST);
    }

    public static boolean defaultHttpClientCodecAllowDuplicateContentLengths() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + HTTP_CLIENT_CODEC_ALLOW_DUPLICATE_CONTENT_LENGTHS);
    }

    public static boolean defaultDisableZeroCopy() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + DISABLE_ZERO_COPY_CONFIG);
    }

    public static int defaultHandshakeTimeout() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getInt(ASYNC_CLIENT_CONFIG_ROOT + HANDSHAKE_TIMEOUT_CONFIG);
    }

    public static int defaultChunkedFileChunkSize() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getInt(ASYNC_CLIENT_CONFIG_ROOT + CHUNKED_FILE_CHUNK_SIZE_CONFIG);
    }

    public static int defaultWebSocketMaxBufferSize() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getInt(ASYNC_CLIENT_CONFIG_ROOT + WEBSOCKET_MAX_BUFFER_SIZE_CONFIG);
    }

    public static int defaultWebSocketMaxFrameSize() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getInt(ASYNC_CLIENT_CONFIG_ROOT + WEBSOCKET_MAX_FRAME_SIZE_CONFIG);
    }

    public static boolean defaultKeepEncodingHeader() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + KEEP_ENCODING_HEADER_CONFIG);
    }

    public static Duration defaultShutdownQuietPeriod() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getDuration(ASYNC_CLIENT_CONFIG_ROOT + SHUTDOWN_QUIET_PERIOD_CONFIG);
    }

    public static Duration defaultShutdownTimeout() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getDuration(ASYNC_CLIENT_CONFIG_ROOT + SHUTDOWN_TIMEOUT_CONFIG);
    }

    public static boolean defaultUseNativeTransport() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + USE_NATIVE_TRANSPORT_CONFIG);
    }

    public static boolean defaultUseOnlyEpollNativeTransport() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean(ASYNC_CLIENT_CONFIG_ROOT + USE_ONLY_EPOLL_NATIVE_TRANSPORT);
    }

    public static int defaultIoThreadsCount() {
        int threads = AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getInt(ASYNC_CLIENT_CONFIG_ROOT + IO_THREADS_COUNT_CONFIG);

        // If threads value is -1 then we will automatically pick number of available processors.
        if (threads == -1) {
            threads = Runtime.getRuntime().availableProcessors();
        }
        return threads;
    }

    public static int defaultHashedWheelTimerTickDuration() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getInt(ASYNC_CLIENT_CONFIG_ROOT + HASHED_WHEEL_TIMER_TICK_DURATION);
    }

    public static int defaultHashedWheelTimerSize() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getInt(ASYNC_CLIENT_CONFIG_ROOT + HASHED_WHEEL_TIMER_SIZE);
    }

    public static int defaultExpiredCookieEvictionDelay() {
        return AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getInt(ASYNC_CLIENT_CONFIG_ROOT + EXPIRED_COOKIE_EVICTION_DELAY);
    }
}
