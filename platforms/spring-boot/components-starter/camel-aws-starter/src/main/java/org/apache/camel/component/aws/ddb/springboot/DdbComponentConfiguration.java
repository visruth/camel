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
package org.apache.camel.component.aws.ddb.springboot;

import javax.annotation.Generated;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.apache.camel.component.aws.ddb.DdbOperations;
import org.apache.camel.spring.boot.ComponentConfigurationPropertiesCommon;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The aws-ddb component is used for storing and retrieving data from Amazon's
 * DynamoDB service.
 * 
 * Generated by camel-package-maven-plugin - do not edit this file!
 */
@Generated("org.apache.camel.maven.packaging.SpringBootAutoConfigurationMojo")
@ConfigurationProperties(prefix = "camel.component.aws-ddb")
public class DdbComponentConfiguration
        extends
            ComponentConfigurationPropertiesCommon {

    /**
     * Whether to enable auto configuration of the aws-ddb component. This is
     * enabled by default.
     */
    private Boolean enabled;
    /**
     * The AWS DDB default configuration
     */
    private DdbConfigurationNestedConfiguration configuration;
    /**
     * Amazon AWS Access Key
     */
    private String accessKey;
    /**
     * Amazon AWS Secret Key
     */
    private String secretKey;
    /**
     * The region in which DDB client needs to work
     */
    private String region;
    /**
     * Whether the component should resolve property placeholders on itself when
     * starting. Only properties which are of String type can use property
     * placeholders.
     */
    private Boolean resolvePropertyPlaceholders = true;

    public DdbConfigurationNestedConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(
            DdbConfigurationNestedConfiguration configuration) {
        this.configuration = configuration;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Boolean getResolvePropertyPlaceholders() {
        return resolvePropertyPlaceholders;
    }

    public void setResolvePropertyPlaceholders(
            Boolean resolvePropertyPlaceholders) {
        this.resolvePropertyPlaceholders = resolvePropertyPlaceholders;
    }

    public static class DdbConfigurationNestedConfiguration {
        public static final Class CAMEL_NESTED_CLASS = org.apache.camel.component.aws.ddb.DdbConfiguration.class;
        /**
         * Amazon AWS Access Key
         */
        private String accessKey;
        /**
         * Amazon AWS Secret Key
         */
        private String secretKey;
        /**
         * To use the AmazonDynamoDB as the client
         */
        private AmazonDynamoDB amazonDDBClient;
        /**
         * The name of the table currently worked with.
         */
        private String tableName;
        /**
         * What operation to perform
         */
        private DdbOperations operation = DdbOperations.PutItem;
        /**
         * Determines whether or not strong consistency should be enforced when
         * data is read.
         */
        private Boolean consistentRead = false;
        /**
         * The provisioned throughput to reserve for reading resources from your
         * table
         */
        private Long readCapacity;
        /**
         * The provisioned throughput to reserved for writing resources to your
         * table
         */
        private Long writeCapacity;
        /**
         * Attribute name when creating table
         */
        private String keyAttributeName;
        /**
         * Attribute type when creating table
         */
        private String keyAttributeType;
        /**
         * To define a proxy host when instantiating the DDB client
         */
        private String proxyHost;
        /**
         * To define a proxy port when instantiating the DDB client
         */
        private Integer proxyPort;
        /**
         * The region in which DDB client needs to work
         */
        private String region;

        public String getAccessKey() {
            return accessKey;
        }

        public void setAccessKey(String accessKey) {
            this.accessKey = accessKey;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        public AmazonDynamoDB getAmazonDDBClient() {
            return amazonDDBClient;
        }

        public void setAmazonDDBClient(AmazonDynamoDB amazonDDBClient) {
            this.amazonDDBClient = amazonDDBClient;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public DdbOperations getOperation() {
            return operation;
        }

        public void setOperation(DdbOperations operation) {
            this.operation = operation;
        }

        public Boolean getConsistentRead() {
            return consistentRead;
        }

        public void setConsistentRead(Boolean consistentRead) {
            this.consistentRead = consistentRead;
        }

        public Long getReadCapacity() {
            return readCapacity;
        }

        public void setReadCapacity(Long readCapacity) {
            this.readCapacity = readCapacity;
        }

        public Long getWriteCapacity() {
            return writeCapacity;
        }

        public void setWriteCapacity(Long writeCapacity) {
            this.writeCapacity = writeCapacity;
        }

        public String getKeyAttributeName() {
            return keyAttributeName;
        }

        public void setKeyAttributeName(String keyAttributeName) {
            this.keyAttributeName = keyAttributeName;
        }

        public String getKeyAttributeType() {
            return keyAttributeType;
        }

        public void setKeyAttributeType(String keyAttributeType) {
            this.keyAttributeType = keyAttributeType;
        }

        public String getProxyHost() {
            return proxyHost;
        }

        public void setProxyHost(String proxyHost) {
            this.proxyHost = proxyHost;
        }

        public Integer getProxyPort() {
            return proxyPort;
        }

        public void setProxyPort(Integer proxyPort) {
            this.proxyPort = proxyPort;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }
    }
}