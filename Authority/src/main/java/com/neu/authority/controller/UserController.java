package com.neu.authority.controller;


import com.neu.authority.entity.Message;
import com.neu.authority.entity.TUser;
import com.neu.authority.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;


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
