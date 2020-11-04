package com.fish.elasticsearch.util;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.mapping.PutMapping;
import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
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

    public static void main(String[] args) throws IOException {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://localhost:9200")
                .multiThreaded(true)
                //Per default this implementation will create no more than 2 concurrent connections per given route
                .defaultMaxTotalConnectionPerRoute(2)
                // and no more 20 connections in total
                .maxTotalConnection(20)
                        .build());
        //创建一个索引
        JestClient client = factory.getObject();
//        client.execute(new CreateIndex.Builder("articles").build());
        //创建一个映射
        PutMapping putMapping = new PutMapping.Builder(
                "articles",
                "my_type",
                "{ \"my_type\" : { \"properties\" : { \"message\" : {\"type\" : \"string\", \"store\" : \"yes\"} } } }"
        ).build();
        client.execute(putMapping);
    }
}