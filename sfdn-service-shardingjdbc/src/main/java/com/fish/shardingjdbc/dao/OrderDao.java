package com.fish.shardingjdbc.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author hh
 * @description
 * @date 2020/10/22  0:17
 */
@Mapper
@Component
public interface OrderDao {

    @Insert("insert into t_order(price,user_id,status)values(#{price},#{userId},#{status})")
    int insertOrder(@Param("price")BigDecimal price, @Param("userId")Long userId,@Param("status")String status);
}
