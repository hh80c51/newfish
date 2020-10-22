package com.fish.shardingjdbc;

import com.fish.shardingjdbc.dao.OrderDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @author hh
 * @description
 * @date 2020/10/22  0:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingJdbcApplication.class)
public class OrderDaoTest {

    @Autowired
    OrderDao orderDao;

    @Test
    public void testInsertOrder(){
        for (int i = 1; i < 20; i++) {
            orderDao.insertOrder(new BigDecimal(11),1L, "SUCCESS");
        }
    }
}
