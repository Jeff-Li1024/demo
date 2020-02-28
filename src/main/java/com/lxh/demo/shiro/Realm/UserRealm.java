package com.lxh.demo.shiro.Realm;

import com.lxh.demo.dao.UserMapper;
import com.lxh.demo.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: LXH
 * @Date: 2020/2/28 14:58
 * @Description:
 */
public class UserRealm extends AuthorizingRealm{

    @Autowired
    private UserMapper userMapper;

    //授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //给资源授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        User findUserById = userMapper.findUserById(user.getId());

        info.addStringPermission(findUserById.getPerms());

        return info;
    }

    //认证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //编写shiro判断逻辑
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        User user = userMapper.findUserByName(token.getUsername());

        if(user == null){
            //用户名不存在
            return null;//shiro底层会抛出UnKnowAccountException
        }

        //2.判断密码
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
