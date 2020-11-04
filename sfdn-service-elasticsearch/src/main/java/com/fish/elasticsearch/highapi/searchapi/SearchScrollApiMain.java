package com.fish.elasticsearch.highapi.searchapi;


import com.fish.elasticsearch.highapi.HighLevelClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author:admin
 * @date:2018/7/16
 * @description
 */
public class SearchScrollApiMain {
    public static void main(String[] args) throws IOException {
        searchApi();
    }

    public static void searchApi() throws IOException {

        RestHighLevelClient client = HighLevelClient.getInstance();

        try {


        }finally {
            HighLevelClient.close();
        }

    }
}
