package com.lxh.demo.controller;

import com.lxh.demo.entity.User;
import com.lxh.demo.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LXH
 * @Date: 2020/2/25 17:40
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/save")
    public void saveUser(User user){
        userService.saveUser(user);
    }

}
