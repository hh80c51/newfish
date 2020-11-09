package com.fish.elasticsearch.highapi.documentapi.bulkapi;


import com.fish.elasticsearch.highapi.HighLevelClient;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:admin
 * @date:2018/7/16
 * @description
 */
public class BulkApiMain {
    public static void main(String[] args) throws IOException {
        try{
            RestHighLevelClient client = HighLevelClient.getInstance();
            BulkRequest bulkRequest = new BulkRequest();
            for(int i=1;i<4;i++) {
                bulkRequest.add(new IndexRequest("mytest_user", "_doc", String.valueOf(i)).source(buildIndexData()));
            }
            bulkRequest.add(new DeleteRequest("mytest_user", "_doc", "1"));
            bulkRequest.add(new UpdateRequest("mytest_user", "_doc", "2").doc(XContentType.JSON,"name","马靖2"));

            BulkResponse bulkResponse = client.bulk(bulkRequest);
            System.out.println(bulkResponse);

        }finally{
            HighLevelClient.close();
        }
    }


    private static Map<String,Object> buildIndexData(){
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("name","马靖");
        data.put("age",31);
        data.put("interests","当兵，跳舞");
        return data;
    }
}
