import com.fish.elasticsearch.EsClient;
import com.fish.elasticsearch.essql.SqlParser;
import com.fish.elasticsearch.pool.ElasticSearchPool;
import com.fish.elasticsearch.service.EsCrudService;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName EsTest
 * @Description TODO
 * @Author 86131
 * @Date 2020/11/5 17:37
 * @Version 1.0
 **/
public class EsTest {

    private EsCrudService esCrudService;

    @Test
    public void connectClientTest() throws IOException {
        long start = System.currentTimeMillis();
        ElasticSearchPool pool = EsClient.restHighLevelPoolClient();
        for(int i=0;i<1000;i++){
            //耗时(ms)：6829
//            RestHighLevelClient client = EsClient.restHighLevelClient();
//            client.close();

            //耗时(ms)：3146
//            JestClient client = EsClient.jestClient();
//            client.close();

            //耗时(ms)：2035
//            RestHighLevelClient client = pool.getResource();
//            pool.returnResource(client);

        }
        long end = System.currentTimeMillis();
        System.out.println("耗时(ms)："+(end-start));
    }

    @Test
    public void queryAll() throws Exception {
        String sql = "select * from user where name = 'Su' order by role_id";
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        try {
            searchSourceBuilder = new SqlParser(searchSourceBuilder).parse(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(searchSourceBuilder);

    }

}
