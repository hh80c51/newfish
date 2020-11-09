package com.fish.elasticsearch.highapi.documentapi.deleteapi;


import com.fish.elasticsearch.highapi.HighLevelClient;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author:admin
 * @date:2018/7/16
 * @description
 */
public class DeleteApiMain {
    public static void main(String[] args) throws IOException {
        try{
            RestHighLevelClient client = HighLevelClient.getInstance();
            DeleteRequest deleteRequest = new DeleteRequest("mytest_user", "_doc", "2");
//            deleteRequest.version(2);//指定源文档版本，防止误删除
//            deleteRequest.timeout(TimeValue.timeValueSeconds(5));//设置请求超时时间

            DeleteResponse deleteResponse = client.delete(deleteRequest);
            System.out.println(deleteResponse);
        }finally{
            HighLevelClient.close();
        }
    }
}
