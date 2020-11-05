import com.fish.elasticsearch.EsClient;
import com.fish.elasticsearch.pool.ElasticSearchPool;
import com.fish.elasticsearch.pool.ElasticSearchPoolConfig;
import com.fish.elasticsearch.pool.HostAndPort;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName EsTest
 * @Description TODO
 * @Author 86131
 * @Date 2020/11/5 17:37
 * @Version 1.0
 **/
public class EsTest {

    @Test
    public void connectClientTest(){
        long start = System.currentTimeMillis();
        ElasticSearchPool pool = EsClient.restHighLevelPoolClient();
        for(int i=0;i<1000;i++){
            //耗时(ms)：6829
//            EsClient.restHighLevelClient();
            //耗时(ms)：3146
//            EsClient.jestClient();
            //耗时(ms)：2035
            RestHighLevelClient client = pool.getResource();
            pool.returnResource(client);

        }
        long end = System.currentTimeMillis();
        System.out.println("耗时(ms)："+(end-start));
    }
}
