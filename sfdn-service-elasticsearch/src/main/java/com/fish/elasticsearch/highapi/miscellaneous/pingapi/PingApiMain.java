package com.fish.elasticsearch.highapi.miscellaneous.pingapi;


import com.fish.elasticsearch.highapi.HighLevelClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class PingApiMain {

    public static void main(String[] args) throws IOException {
        ping();
    }

    public static void ping() throws IOException {
        try{
            RestHighLevelClient client = HighLevelClient.getInstance();

            boolean response = client.ping();

            System.out.println("ping response: " + response);

        }finally{
            HighLevelClient.close();
        }
    }
}
