import com.fish.elasticsearch.EsClient;
import com.fish.elasticsearch.pool.ElasticSearchPool;
import com.fish.elasticsearch.service.EsCrudService;
import org.junit.Test;

import java.io.IOException;

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
    public void queryAll(){

    }

}
