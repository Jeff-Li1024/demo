package com.lxh.demo.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.lxh.demo.shiro.Realm.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: LXH
 * @Date: 2020/2/28 14:54
 * @Description:
 */
@Configuration
public class ShiroConfig {

    //创建ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //1.设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        /** 2.添加Shiro内置过滤器，实现权限拦截
         * 常用过滤器：
         *       anon: 无需认证（登录）可以访问
         *       authc: 必须认证才可以访问
         *       user: 如果使用rememberMe的功能可以直接访问
         *       perms： 该资源必须得到资源权限才可以访问
         *       role: 该资源必须得到角色权限才可以访问
         */
        Map<String ,String> filterMap = new LinkedHashMap<String, String>();
        filterMap.put("/shiro/add","authc");
        filterMap.put("/shiro/update","authc");

        //授权过滤器：
        //注意：当前授权拦截后，shiro会自动跳转到未授权页面
        filterMap.put("/shiro/add","perms[user:add]");

        //设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/shiro/noAuth");


        //修改拦截跳转的页面
        shiroFilterFactoryBean.setLoginUrl("/");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    //创建DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(getRealm());

        return securityManager;
    }

    //创建Realm
    @Bean
    public UserRealm getRealm(){
        return new UserRealm();
    }

    //配置ShiroDialect，用于thymeleaf的标签级授权
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }


}
