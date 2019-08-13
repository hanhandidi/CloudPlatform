package com.neu.account.controller;

import com.neu.account.entity.Message;
import com.neu.account.entity.TUser;
import com.neu.account.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    // 登录，成功返回用户信息
    @RequestMapping(value = "login")
    @ResponseBody
    public Message loginById(String userId, String userPassword) {
        System.out.println(userId+"  "+userPassword);
        return userService.checkPassword(userId, userPassword);
    }

    // 注册用户信息
    @RequestMapping(value = "register")
    @ResponseBody
    public Message setUserInfo(TUser user) {
        return userService.setUserInfo(user);
    }

    // 获取用户信息
    @RequestMapping(value = "get")
    @ResponseBody
    public Message getUserInfo(String userId) {
        return userService.getUserInfo(userId);
    }

    // 修改用户信息
    @RequestMapping(value = "update")
    @ResponseBody
    public Message updateUserInfo(@RequestBody TUser user) {
        return userService.updateUserInfo(user);
    }
}
