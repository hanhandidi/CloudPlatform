package com.neu.authority.controller;


import com.neu.authority.entity.Message;
import com.neu.authority.entity.TUser;
import com.neu.authority.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;


    // 验证用户密码
    @RequestMapping(value = "login")
    @ResponseBody
    Message checkPassword(TUser user) {
        return userService.checkPassword(user);
    }

    // 获取用户信息
    @GetMapping(value = "")
    @ResponseBody
    Message selectUser(TUser user) {
        return userService.selectUser(user);
    }

    // 注册用户信息
    @PostMapping(value = "")
    @ResponseBody
    Message insertUser(TUser user) {
        return userService.insertUser(user);
    }

    // 修改用户信息
    @PutMapping(value = "")
    @ResponseBody
    Message updateUser(TUser user) {
        return userService.updateUser(user);
    }

    // 删除用户信息
    @DeleteMapping(value = "")
    @ResponseBody
    Message deleteUser(TUser user) {
        return userService.deleteUser(user);
    }

    // 获取用户列表（工厂ID）：factoryId
    @RequestMapping(value = "list")
    @ResponseBody
    Message selectUsersInFactory(TUser user) {
        return new Message(200, "获取工厂用户列表成功", userService.selectUsersInFactory(user));
    }
}
