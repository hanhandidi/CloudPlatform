package com.neu.authority.service;

import com.neu.authority.dao.UserDao;
import com.neu.authority.entity.Message;
import com.neu.authority.entity.TUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

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
