package com.fish.user.service.impl;

import com.fish.user.bean.User;
import com.fish.user.mapper.UserMapper;
import com.fish.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hh
 * @description
 * @date 2020/7/16  15:18
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired(required=false)
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User selectById(int userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public void deleteUser(int userId) {
        userMapper.deleteUser(userId);
    }
}