/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.fluss.plugin;

import com.alibaba.fluss.config.Configuration;
import com.alibaba.fluss.exception.FlussRuntimeException;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

/** Utility functions for the plugin mechanism. */
public final class PluginUtils {

    private PluginUtils() {
        throw new AssertionError("Singleton class.");
    }

    public static PluginManager createPluginManagerFromRootFolder(Configuration configuration) {
        return createPluginManagerFromRootFolder(PluginConfig.fromConfiguration(configuration));
    }

    private static PluginManager createPluginManagerFromRootFolder(PluginConfig pluginConfig) {
        if (pluginConfig.getPluginsPath().isPresent()) {
            try {
                Collection<PluginDescriptor> pluginDescriptors =
                        new DirectoryBasedPluginFinder(pluginConfig.getPluginsPath().get())
                                .findPlugins();
                return new DefaultPluginManager(
                        pluginDescriptors, pluginConfig.getAlwaysParentFirstPatterns());
            } catch (IOException e) {
                throw new FlussRuntimeException(
                        "Exception when trying to initialize plugin system.", e);
            }
        } else {
            return new DefaultPluginManager(
                    Collections.emptyList(), pluginConfig.getAlwaysParentFirstPatterns());
        }
    }
}
