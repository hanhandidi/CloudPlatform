package com.neu.account.service;

import com.neu.account.dao.UserDao;
import com.neu.account.entity.Message;
import com.neu.account.entity.TUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    public Message checkPassword(String userId, String userPassword) {
        TUser user = userDao.getUserById(userId);
        if (null == user)
            return new Message(201, "登录失败", new TUser());
        if (!userPassword.equals(user.getUserPasswd()))
            return new Message(202, "登录失败", new TUser());
        return new Message(200, "登录成功", user);
    }

    public Message getUserInfo(String userId) {
        TUser user = userDao.getUserById(userId);
        if (null == user)
            return new Message(201, "获取用户信息失败", new TUser());
        return new Message(200, "获取用户信息成功", user);
    }

    @Override
    public Message setUserInfo(TUser user) {

        return null;
    }

    @Override
    public Message updateUserInfo(TUser user) {
        if (userDao.getUserByName(user.getUserName()) != null)
            return new Message(201, "用户名已存在", null);
        userDao.updateUserInfo(user);
        return new Message(200, "修改用户成功", user);
    }
}
