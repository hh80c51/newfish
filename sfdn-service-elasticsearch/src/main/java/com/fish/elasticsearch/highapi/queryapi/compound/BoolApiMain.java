package com.fish.elasticsearch.highapi.queryapi.compound;


import com.fish.elasticsearch.highapi.HighLevelClient;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

/**
 * @author:admin
 * @date:2018/7/12
 * @description
 */
public class BoolApiMain {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = HighLevelClient.getInstance();
        try{
            BoolQueryBuilder matchQueryBuilder = QueryBuilders.boolQuery();
            //term query 分词精确查询，_name 分词后包含EricE的term的文档
            System.out.println("结果："+QueryBuilders.termQuery("_role_id",1));
            matchQueryBuilder.must(QueryBuilders.termQuery("_role_id",1));
            matchQueryBuilder.must(QueryBuilders.termQuery("_role_name","administrator"));
            //terms Query 多term查询，查询hotelName 包含 EricE 或Cindy 中的任何一个或多个的文档
//            matchQueryBuilder.should(QueryBuilders.termsQuery("_name","EricE","Cindy"));
//            matchQueryBuilder.mustNot(QueryBuilders.termQuery("_role_name","operator"));
            matchQueryBuilder.should(QueryBuilders.rangeQuery("_role_id")
                    .from(0,true)                 //包括下界
                            .to(100,true));           //包括上界
//            matchQueryBuilder.minimumShouldMatch(1);    //最小匹配度,表示命中1个才会返回，也可以写50%
//            //匹配分词前缀 如果字段没分词，就匹配整个字段前缀
//            QueryBuilders.prefixQuery("_name","E");
//            //通配符查询，*任意字符串;?任意一个字符
//            QueryBuilders.wildcardQuery("_name","Eri*");
//            //fuzziness 的含义是检索的term 前后增加或减少n个单词的匹配查询，hotelName 为 Eri ric Eric前或后加一个字母的term的 文档
//            QueryBuilders.fuzzyQuery("_name", "Eric").fuzziness(Fuzziness.ONE);
//            //idx Query 根据ID查询
//            QueryBuilders.idsQuery().addIds("1","2");

            //query 上下文的条件是用来给文档打分的，匹配越好 _score 越高
            //filter 的条件只产生两种结果：符合与不符合，后者被过滤掉。
            matchQueryBuilder.filter();

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(matchQueryBuilder);
            searchSourceBuilder.from(0);
            searchSourceBuilder.size(3);

            SearchRequest searchRequest = new SearchRequest("mytest_user");//限定index
            searchRequest.types("_doc");//限定type
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest);
            System.out.println("查询结果：" + searchResponse);

            //遍历查询结果输出相关度分值和文档内容
            SearchHits searchHits =  searchResponse.getHits();
            for(SearchHit searchHit : searchHits){
                System.out.println("命中分数："+searchHit.getScore());
                System.out.println("命中结果："+searchHit.getSourceAsString());
            }
        }finally{
            HighLevelClient.close();
        }
    }
}
