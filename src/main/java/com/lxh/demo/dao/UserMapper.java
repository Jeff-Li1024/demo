package com.lxh.demo.dao;

import com.lxh.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: LXH
 * @Date: 2020/2/25 17:35
 * @Description:
 */
@Mapper
public interface UserMapper {

    void saveUser(User user);

}
