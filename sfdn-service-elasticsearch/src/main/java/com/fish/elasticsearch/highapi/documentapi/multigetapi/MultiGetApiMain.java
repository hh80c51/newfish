package com.fish.elasticsearch.highapi.documentapi.multigetapi;


import com.fish.elasticsearch.highapi.HighLevelClient;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author:admin
 * @date:2018/7/16
 * @description
 */
public class MultiGetApiMain {

    public static void main(String[] args) throws IOException {
        try{
            RestHighLevelClient client = HighLevelClient.getInstance();
            MultiGetRequest multiGetRequest = new MultiGetRequest();

            multiGetRequest.add(new MultiGetRequest.Item("jingma2_20180716","testlog","1"));
            multiGetRequest.add(new MultiGetRequest.Item("jingma2_20180716","testlog","2"));
            multiGetRequest.add(new MultiGetRequest.Item("jingma2_20180716","testlog","3"));

            MultiGetResponse multiGetResponse = client.multiGet(multiGetRequest);
            MultiGetItemResponse[] itemResponses = multiGetResponse.getResponses();
            for(int i=0;i<itemResponses.length;i++){
                System.out.println(itemResponses[i].getResponse());
            }
        }finally{
            HighLevelClient.close();
        }
    }
}
