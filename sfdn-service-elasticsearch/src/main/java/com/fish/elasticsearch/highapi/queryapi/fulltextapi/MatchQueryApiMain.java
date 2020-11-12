package com.fish.elasticsearch.highapi.queryapi.fulltextapi;


import com.fish.elasticsearch.highapi.HighLevelClient;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class MatchQueryApiMain {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = HighLevelClient.getInstance();
        try{
            QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("status","success");

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(matchQueryBuilder);
            searchSourceBuilder.from(0);
            searchSourceBuilder.size(5);

            SearchRequest searchRequest = new SearchRequest("mytest_user");//限定index
            searchRequest.types("log");//限定type
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest);
            System.out.println(searchResponse);


        }finally{
            HighLevelClient.close();
        }
    }
}
