package com.fish.user;

import com.fish.user.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(scanBasePackages={"com.fish.user.*"})
@MapperScan("com.fish.user")
@Configuration
public class SfdnServiceUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SfdnServiceUserApplication.class, args);
    }

}
