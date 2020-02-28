package com.lxh.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LXH
 * @Date: 2020/2/28 15:27
 * @Description:
 */
@Controller
@RequestMapping("/shiro")
public class ShiroController {

    @RequestMapping("/index")
    public String shiroIndex(Model model){
        model.addAttribute("name","ShiroDemo");
        return "test";
    }

    @RequestMapping("/add")
    public String add(Model model){
        return "user/add";
    }

    @RequestMapping("/update")
    public String update(Model model){
        return "user/update";
    }

    @RequestMapping("/login")
    public String login(String name,String password,Model model){

        //使用shiro验证
        //1.获取subject
        Subject subject = SecurityUtils.getSubject();

        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);

        //3.执行登录方法
        try {
            //登录成功
            subject.login(token);
            return "user/success";
        }catch (UnknownAccountException e){
            //登录失败：用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "index";
        }catch (IncorrectCredentialsException e){
            //登录失败：密码错误
            model.addAttribute("msg","密码错误");
            return "index";
        }

    }

    @RequestMapping("/noAuth")
    public String noAuth(){
        return "user/noAuth";
    }

}
