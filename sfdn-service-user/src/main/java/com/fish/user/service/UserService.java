package com.fish.user.service;

import com.fish.user.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hh
 * @description
 * @date 2020/7/16  15:16
 */
public interface UserService {
    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    User selectById(int userId);

    /**
     * 查询所有用户
     * @return
     */
    List<User> selectAll();

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);

    /**
     * 根据id删除用户
     * @param userId
     */
    void deleteUser(int userId);


}
