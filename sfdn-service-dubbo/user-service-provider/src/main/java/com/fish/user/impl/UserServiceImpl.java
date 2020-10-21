package com.fish.user.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fish.user.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name + " (from Spring Boot)";
    }
}
