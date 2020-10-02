package com.fish.shardingjdbc.simple.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @Description:
 * @Auther: Administrator
 * @Date: 2020/10/2 15:34
 */
@Mapper
@Component
public interface OrderDao {
    @Insert("insert info t_order(price, user_id, status) values (#{price},#{userId},#{status})")
    int insertOrder(@Param("price")BigDecimal price, @Param("userId")Long userId, @Param("status")String status);
}
