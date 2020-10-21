package com.fish.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fish.user.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Reference
    private UserService userService;

    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam String name) {
        return userService.sayHello(name);
    }
}
