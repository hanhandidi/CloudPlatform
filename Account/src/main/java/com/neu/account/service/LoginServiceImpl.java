package com.neu.account.service;

import com.neu.account.dao.UserDao;
import com.neu.account.entity.Message;
import com.neu.account.entity.TUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    UserDao userDao;

    public Message checkPassword(String userId, String userPassword) {
        TUser user = userDao.getUserInfo(userId);
        if (null == userPassword)
            return new Message(201, "登录失败", new TUser());
        if (null == user)
            return new Message(202, "登录失败", new TUser());
        if (!userPassword.equals(user.getUserPasswd()))
            return new Message(203, "登录失败", new TUser());
        return new Message(200, "登录成功", user);
    }

    public Message getUserInfo(String userId) {
        TUser user = userDao.getUserInfo(userId);
        if (null == user)
            return new Message(201, "获取用户信息失败", new TUser());
        return new Message(200, "获取用户信息成功", user);
    }
}
