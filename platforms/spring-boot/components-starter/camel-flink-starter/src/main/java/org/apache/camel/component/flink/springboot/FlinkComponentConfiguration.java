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
package org.apache.camel.component.flink.springboot;

import javax.annotation.Generated;
import org.apache.camel.spring.boot.ComponentConfigurationPropertiesCommon;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The flink component can be used to send DataSet jobs to Apache Flink cluster.
 * 
 * Generated by camel-package-maven-plugin - do not edit this file!
 */
@Generated("org.apache.camel.maven.packaging.SpringBootAutoConfigurationMojo")
@ConfigurationProperties(prefix = "camel.component.flink")
public class FlinkComponentConfiguration
        extends
            ComponentConfigurationPropertiesCommon {

    /**
     * Whether to enable auto configuration of the flink component. This is
     * enabled by default.
     */
    private Boolean enabled;
    /**
     * DataSet to compute against. The option is a
     * org.apache.flink.api.java.DataSet type.
     */
    private String dataSet;
    /**
     * DataStream to compute against. The option is a
     * org.apache.flink.streaming.api.datastream.DataStream type.
     */
    private String dataStream;
    /**
     * Function performing action against a DataSet. The option is a
     * org.apache.camel.component.flink.DataSetCallback type.
     */
    private String dataSetCallback;
    /**
     * Function performing action against a DataStream. The option is a
     * org.apache.camel.component.flink.DataStreamCallback type.
     */
    private String dataStreamCallback;
    /**
     * Whether the component should resolve property placeholders on itself when
     * starting. Only properties which are of String type can use property
     * placeholders.
     */
    private Boolean resolvePropertyPlaceholders = true;

    public String getDataSet() {
        return dataSet;
    }

    public void setDataSet(String dataSet) {
        this.dataSet = dataSet;
    }

    public String getDataStream() {
        return dataStream;
    }

    public void setDataStream(String dataStream) {
        this.dataStream = dataStream;
    }

    public String getDataSetCallback() {
        return dataSetCallback;
    }

    public void setDataSetCallback(String dataSetCallback) {
        this.dataSetCallback = dataSetCallback;
    }

    public String getDataStreamCallback() {
        return dataStreamCallback;
    }

    public void setDataStreamCallback(String dataStreamCallback) {
        this.dataStreamCallback = dataStreamCallback;
    }

    public Boolean getResolvePropertyPlaceholders() {
        return resolvePropertyPlaceholders;
    }

    public void setResolvePropertyPlaceholders(
            Boolean resolvePropertyPlaceholders) {
        this.resolvePropertyPlaceholders = resolvePropertyPlaceholders;
    }
}