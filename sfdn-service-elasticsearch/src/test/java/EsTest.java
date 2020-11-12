import com.fish.elasticsearch.EsClient;
import com.fish.elasticsearch.essql.SqlParser;
import com.fish.elasticsearch.highapi.HighLevelClient;
import com.fish.elasticsearch.pool.ElasticSearchPool;
import com.fish.elasticsearch.service.EsCrudService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

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
        String name = "'%unny'";
//        String sql = "select * from user where _name = " +name + " and ( _role_id = 1 or _role_id = 2 ) and _role_id < 2";
//        String sql = "select * from user where _role_id > 1 and _role_id <= 2";
        String sql = "select * from user where _c_time > '2020-11-11 11:47:57' and _c_time < '2020-11-11 11:47:57'";
//        String sql = "select * from user where _name like " +name;
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        try {
            searchSourceBuilder = new SqlParser(searchSourceBuilder).parse(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(searchSourceBuilder);

        RestHighLevelClient client = HighLevelClient.getInstance();
        SearchRequest searchRequest = new SearchRequest("mytest_user");//限定index
        searchRequest.types("_doc");//限定type
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest);
        System.out.println(searchResponse);

    }

}
