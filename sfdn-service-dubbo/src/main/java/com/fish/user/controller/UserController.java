package com.fish.user.controller;

import com.fish.user.bean.User;
import com.fish.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String login(){
        User user = new User();
        user.setName("hh");
        user.setPassword("123456");
        user.setPhone("13161518747");
        userService.addUser(user);
        List<User> userList = userService.selectAll();
        System.out.printf("用户数量"+userList.size());
        return "login";
    }
}
