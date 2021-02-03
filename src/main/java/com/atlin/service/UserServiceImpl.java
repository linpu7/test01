package com.atlin.service;

import com.atlin.pojo.LoginUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lin
 * @version v1.0
 * @program spring_security_demo
 * @description
 * @date 2021-01-31 19:21
 */
@Component
public class UserServiceImpl implements UserDetailsService {
    static Map<String, LoginUser> map = new HashMap<>();

    static {
        LoginUser user1 = new LoginUser();
        user1.setUsername("tom");
        user1.setPassword("$2a$10$rS7oRtvPbQW2ASYYhlu2yOupvJHsjreCirCa0IImcLz/uQlcCqJhm");
        user1.setTelephone("110");
        LoginUser user2 = new LoginUser();
        user2.setUsername("jack");
        user2.setPassword("123");
        user2.setTelephone("112");
        map.put(user1.getUsername(), user1);
        map.put(user1.getUsername(), user1);
    }

    /**
     * 权限框架自动调用该方法
     *
     * @param username
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //username 登录的用户名字
        System.out.println("login name" + username);
        //模拟根据用户名查询数据库
        LoginUser loginUser = map.get(username);
        if (loginUser == null) {
            //根据用户名没有查询到用户，抛出异常，表示登录名输入有误
            System.out.println("请先注册!!!");
            return null;
        }
        //模拟数据库中的密码，后期需要查询数据库
//        String password = "{noop}" + loginUser.getPassword();//不使用加密器
        String password =  loginUser.getPassword();
        //授权，后期需要改为查询数据库动态获得用户拥有的权限和角色
        //给登录的用户分配权限
        List<GrantedAuthority> lists = new ArrayList<>();
        lists.add(new SimpleGrantedAuthority("add"));
        lists.add(new SimpleGrantedAuthority("delete"));
        lists.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        //public User(String username, String password, Collection<? extends GrantedAuthority > authorities)
        //返回User，
        //参数一：存放登录名，
        //参数二：存放数据库查询的密码（数据库获取的密码，默认会和页面获取的密码进行比对，成功跳转到成功页面，失败回到登录页面，并抛出异常表示失败）
        //参数三：存放当前用户具有的权限集合
//注意：框架提供的User类：org.springframework.security.core.userdetails.User
        return new User(username,password,lists);
    }
}

