package com.fish.front.controller;

import com.fish.user.bean.User;
import com.fish.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hh
 * @description
 * @date 2020/7/16  16:21
 */
@Controller
//@RestController
public class UserController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
