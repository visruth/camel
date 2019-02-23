/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.atomix.client.set.springboot;

import java.util.List;
import javax.annotation.Generated;
import io.atomix.catalyst.transport.Address;
import org.apache.camel.component.atomix.client.set.AtomixSet.Action;
import org.apache.camel.spring.boot.ComponentConfigurationPropertiesCommon;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The atomix-set component is used to access Atomix's distributed set.
 * 
 * Generated by camel-package-maven-plugin - do not edit this file!
 */
@Generated("org.apache.camel.maven.packaging.SpringBootAutoConfigurationMojo")
@ConfigurationProperties(prefix = "camel.component.atomix-set")
public class AtomixSetComponentConfiguration
        extends
            ComponentConfigurationPropertiesCommon {

    /**
     * Whether to enable auto configuration of the atomix-set component. This is
     * enabled by default.
     */
    private Boolean enabled;
    /**
     * The shared component configuration
     */
    private AtomixSetConfigurationNestedConfiguration configuration;
    /**
     * The shared AtomixClient instance. The option is a io.atomix.AtomixClient
     * type.
     */
    private String atomix;
    /**
     * The nodes the AtomixClient should connect to
     */
    private List<Address> nodes;
    /**
     * The path to the AtomixClient configuration
     */
    private String configurationUri;
    /**
     * Whether the component should resolve property placeholders on itself when
     * starting. Only properties which are of String type can use property
     * placeholders.
     */
    private Boolean resolvePropertyPlaceholders = true;

    public AtomixSetConfigurationNestedConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(
            AtomixSetConfigurationNestedConfiguration configuration) {
        this.configuration = configuration;
    }

    public String getAtomix() {
        return atomix;
    }

    public void setAtomix(String atomix) {
        this.atomix = atomix;
    }

    public List<Address> getNodes() {
        return nodes;
    }

    public void setNodes(List<Address> nodes) {
        this.nodes = nodes;
    }

    public String getConfigurationUri() {
        return configurationUri;
    }

    public void setConfigurationUri(String configurationUri) {
        this.configurationUri = configurationUri;
    }

    public Boolean getResolvePropertyPlaceholders() {
        return resolvePropertyPlaceholders;
    }

    public void setResolvePropertyPlaceholders(
            Boolean resolvePropertyPlaceholders) {
        this.resolvePropertyPlaceholders = resolvePropertyPlaceholders;
    }

    public static class AtomixSetConfigurationNestedConfiguration {
        public static final Class CAMEL_NESTED_CLASS = org.apache.camel.component.atomix.client.set.AtomixSetConfiguration.class;
        /**
         * The default action.
         */
        private Action defaultAction = Action.ADD;
        /**
         * The resource ttl.
         */
        private Long ttl;
        /**
         * The header that wil carry the result.
         */
        private String resultHeader;

        public Action getDefaultAction() {
            return defaultAction;
        }

        public void setDefaultAction(Action defaultAction) {
            this.defaultAction = defaultAction;
        }

        public Long getTtl() {
            return ttl;
        }

        public void setTtl(Long ttl) {
            this.ttl = ttl;
        }

        public String getResultHeader() {
            return resultHeader;
        }

        public void setResultHeader(String resultHeader) {
            this.resultHeader = resultHeader;
        }
    }
}