package com.lxh.demo.service.impl;

import com.lxh.demo.dao.UserMapper;
import com.lxh.demo.entity.User;
import com.lxh.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LXH
 * @Date: 2020/2/25 17:08
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

}
