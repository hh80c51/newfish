package com.fish.elasticsearch.pool;

import org.elasticsearch.client.RestHighLevelClient;

import java.util.Set;

/**
 * @author hh
 * @description 创建ES的连接池
 * @date 2020/10/26  23:21
 */
public class ElasticSearchPool extends Pool<RestHighLevelClient> {

    private String clusterName;
    private Set<HostAndPort> clusterNodes;

    public ElasticSearchPool(ElasticSearchPoolConfig config){
        super(config, new ElasticSearchClientFactory(config.getClusterName(), config.getNodes()));
        this.clusterName = clusterName;
        this.clusterNodes = clusterNodes;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public Set<HostAndPort> getClusterNodes() {
        return clusterNodes;
    }

    public void setClusterNodes(Set<HostAndPort> clusterNodes) {
        this.clusterNodes = clusterNodes;
    }



}
