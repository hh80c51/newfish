package com.fish.elasticsearch.highapi;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class HighLevelClient {

    private static RestHighLevelClient client = null;

    public static RestHighLevelClient getInstance() {
        if (client == null) {
            client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost("127.0.0.1", 9200, "http")));
        }
        return client;
    }

    public static void close(){
        if (client != null) {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
