package com.fish.shardingjdbc.simple;

import com.fish.shardingjdbc.simple.dao.OrderDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @Description:
 * @Auther: Administrator
 * @Date: 2020/10/2 15:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ShardingJdbcSimpleBootstrap.class})
public class OrderDaoTest {

    @Autowired
    OrderDao orderDao;

    @Test
    public void testInsertOrder(){
        orderDao.insertOrder(new BigDecimal(11), 1L, "SUCCESS");
    }
}
