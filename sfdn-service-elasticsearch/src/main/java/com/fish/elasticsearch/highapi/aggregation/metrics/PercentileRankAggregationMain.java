package com.fish.elasticsearch.highapi.aggregation.metrics;


import com.fish.elasticsearch.highapi.HighLevelClient;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.percentiles.PercentileRanksAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

/**
 * @author:admin
 * @date:2018/7/12
 * @description
 */
public class PercentileRankAggregationMain {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = HighLevelClient.getInstance();
        try{
            QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("cmd", "weather_hourforcast");
            PercentileRanksAggregationBuilder aggregationBuilder = AggregationBuilders.percentileRanks("utm_ranks",new double[]{200,500,1000,3000,8000}).field("utm").keyed(false);

            SearchRequest searchRequest = new SearchRequest("serverlog_20180710");//限定index
            searchRequest.types("log");//限定type

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(matchQueryBuilder);
            searchSourceBuilder.aggregation(aggregationBuilder);
            searchSourceBuilder.size(0);
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest);
            System.out.println(searchResponse);

        }finally{
            HighLevelClient.close();
        }
    }
}
