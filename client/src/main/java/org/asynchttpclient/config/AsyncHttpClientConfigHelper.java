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
package org.asynchttpclient.config;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public final class AsyncHttpClientConfigHelper {

    private static volatile @Nullable Config config;

    private AsyncHttpClientConfigHelper() {
    }

    public static Config getAsyncHttpClientConfig() {
        if (config == null) {
            config = new Config();
        }

        return config;
    }

    /**
     * This method invalidates the property caches. So if a system property has been changed and the effect of this change is to be seen then call reloadProperties() and then
     * getAsyncHttpClientConfig() to get the new property values.
     */
    public static void reloadProperties() {
        final Config localInstance = config;
        if (localInstance != null) {
            localInstance.reload();
        }
    }

    public static class Config {

        public static final String DEFAULT_AHC_PROPERTIES = "ahc-default.properties";
        public static final String CUSTOM_AHC_PROPERTIES = "ahc.properties";

        private final ConcurrentHashMap<String, String> propsCache = new ConcurrentHashMap<>();
        private final Properties defaultProperties = parsePropertiesFile(DEFAULT_AHC_PROPERTIES, true);
        private volatile Properties customProperties = parsePropertiesFile(CUSTOM_AHC_PROPERTIES, false);

        public void reload() {
            customProperties = parsePropertiesFile(CUSTOM_AHC_PROPERTIES, false);
            propsCache.clear();
        }

        private Properties parsePropertiesFile(String file, boolean required) {
            Properties props = new Properties();

            InputStream is = getClass().getResourceAsStream(file);
            if (is != null) {
                try {
                    props.load(is);
                } catch (IOException e) {
                    throw new IllegalArgumentException("Can't parse config file " + file, e);
                }
            } else if (required) {
                throw new IllegalArgumentException("Can't locate config file " + file);
            }

            return props;
        }

        public String getString(String key) {
            return propsCache.computeIfAbsent(key, k -> {
                String value = System.getProperty(k);
                if (value == null) {
                    value = customProperties.getProperty(k);
                }
                if (value == null) {
                    value = defaultProperties.getProperty(k);
                }
                return value;
            });
        }

        public @Nullable String[] getStringArray(String key) {
            String s = getString(key);
            s = s.trim();
            if (s.isEmpty()) {
                return null;
            }
            String[] rawArray = s.split(",");
            String[] array = new String[rawArray.length];
            for (int i = 0; i < rawArray.length; i++) {
                array[i] = rawArray[i].trim();
            }
            return array;
        }

        public int getInt(String key) {
            return Integer.parseInt(getString(key));
        }

        public boolean getBoolean(String key) {
            return Boolean.parseBoolean(getString(key));
        }

        public Duration getDuration(String key) {
            return Duration.parse(getString(key));
        }
    }
}
