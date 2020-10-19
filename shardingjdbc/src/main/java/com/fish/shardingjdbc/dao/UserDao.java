package com.fish.shardingjdbc.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author hh
 * @description
 * @date 2020/10/19  22:56
 */
@Mapper
@Component
public interface UserDao {

    @Insert("insert into user(name, password, phone)values(#{name},#{password},#{phone})")
    int insertUser(@Param("name")String name, @Param("password")String password, @Param("phone")String phone);
}
