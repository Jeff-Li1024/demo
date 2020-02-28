package com.lxh.demo.entity;

/**
 * @Author: LXH
 * @Date: 2020/2/25 17:07
 * @Description:
 */
public class User {
    private Integer id;
    private String name;
    private String password;
    private String perms;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Integer getId() {
        return id;

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
