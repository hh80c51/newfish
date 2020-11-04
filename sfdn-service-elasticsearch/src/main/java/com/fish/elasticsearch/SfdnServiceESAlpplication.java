package com.fish.elasticsearch;


import com.fish.elasticsearch.pool.ElasticSearchPool;
import com.fish.elasticsearch.pool.ElasticSearchPoolConfig;
import com.fish.elasticsearch.pool.HostAndPort;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.util.HashSet;
import java.util.Set;

/**
 * @author hh
 * @description
 * @date 2020/11/3  23:27
 */
public class SfdnServiceESAlpplication {

    public static void main(String[] args) throws Exception {
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        nodes.add(new HostAndPort("127.0.0.1:9200","127.0.0.1",9200,"http"));
        ElasticSearchPoolConfig config = new ElasticSearchPoolConfig();
        config.setConnectTimeMillis(8000);
        config.setMaxTotal(100);
        config.setClusterName("elasticsearch");
        config.setNodes(nodes);
        ElasticSearchPool pool = new ElasticSearchPool(config);

        long start = System.currentTimeMillis();
        for(int i=0;i<1000;i++){
            RestHighLevelClient client = pool.getResource();
//            boolean response = client.ping();
//            System.out.println("ping response: " + response);
            pool.returnResource(client);

//            RestHighLevelClient client = new RestHighLevelClient(
//                    RestClient.builder(
//                            new HttpHost("127.0.0.1", 9200, "http")));

        }
        long end = System.currentTimeMillis();
        System.out.println("耗时(ms)："+(end-start));

    }

}
