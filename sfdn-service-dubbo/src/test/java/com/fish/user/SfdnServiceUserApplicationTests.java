package com.fish.user;

import com.fish.user.bean.User;
import com.fish.user.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SfdnServiceUserApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        User user = userService.selectById(1);
        System.out.println(user.toString());
    }

}
