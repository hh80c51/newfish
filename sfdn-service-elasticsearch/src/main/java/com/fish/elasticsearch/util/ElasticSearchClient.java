package com.fish.elasticsearch.util;

import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * client初始化
 * @author hh
 * @description
 * @date 2020/11/3  23:54
 */
public class ElasticSearchClient {
    private String[] hostsAndPorts;

    public ElasticSearchClient(String[] hostsAndPorts) {
        this.hostsAndPorts = hostsAndPorts;
    }
    public RestHighLevelClient getClient() {
        RestHighLevelClient client = null;
        List<HttpHost> httpHosts = new ArrayList<HttpHost>();
        if (hostsAndPorts.length > 0) {
            for (String hostsAndPort : hostsAndPorts) {
                String[] hp = hostsAndPort.split(":");
                httpHosts.add(new HttpHost(hp[0], Integer.valueOf(hp[1]), "http"));
            }
            client = new RestHighLevelClient(
                    RestClient.builder(httpHosts.toArray(new HttpHost[0])));
        } else {
            client = new RestHighLevelClient(
                    RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));
        }
        return client;
    }

    private IndexRequest getIndexRequest(String index, String indexType, String docId, Map<String, Object> dataMap) {
        IndexRequest indexRequest = null;
        if (null == index || null == indexType) {
            throw new ElasticsearchException("index or indexType must not be null");
        }
        if (null == docId) {
            indexRequest = new IndexRequest(index, indexType);
        } else {
            indexRequest = new IndexRequest(index, indexType, docId);
        }
        return indexRequest;
    }

    /**
     * 同步执行索引
     *
     * @param index
     * @param indexType
     * @param docId
     * @param dataMap
     * @throws IOException
     */
    public IndexResponse execIndex(String index, String indexType, String docId, Map<String, Object> dataMap) throws IOException {
        return getClient().index(getIndexRequest(index, indexType, docId, dataMap).source(dataMap));
    }

    /**
     * 异步执行
     *
     * @param index
     * @param indexType
     * @param docId
     * @param dataMap
     * @param indexResponseActionListener
     * @throws IOException
     */
    public void asyncExecIndex(String index, String indexType, String docId, Map<String, Object> dataMap, ActionListener<IndexResponse> indexResponseActionListener) throws IOException {
        getClient().indexAsync(getIndexRequest(index, indexType, docId, dataMap).source(dataMap), indexResponseActionListener);
    }

    public static void main(String[] args) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("user", "kimchy");
            builder.timeField("postDate", new Date());
            builder.field("message", "trying out Elasticsearch");
        }
        builder.endObject();
        IndexRequest indexRequest = new IndexRequest("posts", "doc", "1")
                .source(builder);

    }
}