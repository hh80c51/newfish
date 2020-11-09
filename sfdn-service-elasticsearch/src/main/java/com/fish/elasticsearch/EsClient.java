package com.fish.elasticsearch;

import com.fish.elasticsearch.pool.ElasticSearchPool;
import com.fish.elasticsearch.pool.ElasticSearchPoolConfig;
import com.fish.elasticsearch.pool.HostAndPort;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.mapping.PutMapping;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.util.*;

/**
 * client初始化
 * @author hh
 * @description
 * @date 2020/11/3  23:54
 */
public class EsClient {

    public static void main(String[] args) throws IOException {
        JestClient client = jestClient();
        //创建一个索引
//        CreateIndex index = new CreateIndex.Builder("articles").build();
//        client.execute(index);
        //创建一个映射
        PutMapping putMapping = new PutMapping.Builder(
                "articles",
                "my_type",
                "{ \"my_type\" : { \"properties\" : { \"message\" : {\"type\" : \"string\", \"store\" : \"yes\"} } } }"
        ).build();
        client.execute(putMapping);
    }

    /**
     * @description Jest连接实例
     * @param
     * @return io.searchbox.client.JestClient
     * @date 2020/11/4 22:50
     * @author hh
     */
    public static JestClient jestClient(){
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://localhost:9200")
                .multiThreaded(true)
                //Per default this implementation will create no more than 2 concurrent connections per given route
                .defaultMaxTotalConnectionPerRoute(2)
                // and no more 20 connections in total
                .maxTotalConnection(20)
                .build());
        return factory.getObject();
    }

    /**
     * @description RestHigh连接实例
     * @param
     * @return org.elasticsearch.client.RestHighLevelClient
     * @date 2020/11/9 17:01
     * @author hehang
     */
    public static RestHighLevelClient restHighLevelClient(){
//        return new RestHighLevelClient(
//        RestClient.builder(
//                new HttpHost("127.0.0.1", 9200, "http")));

        //需要用户名和密码的认证
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "Ab2yVX3y2MQmTg=="));
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("192.168.0.61", 9200, "http"))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                        return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                });
        return new RestHighLevelClient(restClientBuilder);
    }

    /**
     * @description RestHigh连接实例池化
     * @param
     * @return com.fish.elasticsearch.pool.ElasticSearchPool
     * @date 2020/11/9 17:01
     * @author hehang
     */
    public static ElasticSearchPool restHighLevelPoolClient(){
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        nodes.add(new HostAndPort("127.0.0.1:9200","127.0.0.1",9200,"http"));
        ElasticSearchPoolConfig config = new ElasticSearchPoolConfig();
        config.setConnectTimeMillis(8000);
        config.setMaxTotal(100);
        config.setClusterName("elasticsearch");
        config.setNodes(nodes);
        ElasticSearchPool pool = new ElasticSearchPool(config);
        return pool;
    }
}