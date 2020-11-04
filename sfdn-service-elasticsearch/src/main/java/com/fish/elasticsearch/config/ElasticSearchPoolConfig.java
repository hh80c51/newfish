package com.fish.elasticsearch.config;

import com.google.common.net.HostAndPort;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author hh
 * @description
 * @date 2020/11/4  23:03
 */
public class ElasticSearchPoolConfig extends GenericObjectPoolConfig {

    private long connectTimeMillis;

    private String clusterName;

    Set<HostAndPort> nodes = new HashSet<HostAndPort>();

    public long getConnectTimeMillis() {
        return connectTimeMillis;
    }

    public void setConnectTimeMillis(long connectTimeMillis) {
        this.connectTimeMillis = connectTimeMillis;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public Set<HostAndPort> getNodes() {
        return nodes;
    }

    public void setNodes(Set<HostAndPort> nodes) {
        this.nodes = nodes;
    }
}