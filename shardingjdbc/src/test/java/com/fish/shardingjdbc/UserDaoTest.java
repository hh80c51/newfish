package com.fish.shardingjdbc;

import com.fish.shardingjdbc.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hh
 * @description
 * @date 2020/10/19  22:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingjdbcStart.class)
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Test
    public void testInsertUser(){
        userDao.insertUser("hehang", "a12345", "13161518747");
    }
}
