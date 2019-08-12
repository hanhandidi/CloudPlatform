package com.neu.account.controller;

import com.neu.account.entity.Message;
import com.neu.account.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    LoginServiceImpl loginServiceImpl;

    // 登录，成功返回用户信息
    @RequestMapping(value = "")
    @ResponseBody
    public Message loginById(String userId, String userPassword) {

        return loginServiceImpl.checkPassword(userId, userPassword);
    }

    // 获取用户信息
    @RequestMapping(value = "getUser")
    @ResponseBody
    public Message getUserInfo(String userId) {
        return loginServiceImpl.getUserInfo(userId);
    }
}
