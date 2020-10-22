package com.fish.shardingjdbc;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author hh
 * @description
 * @date 2020/10/22  0:13
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
public class ShardingJdbcApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcApplication.class, args);
    }
}