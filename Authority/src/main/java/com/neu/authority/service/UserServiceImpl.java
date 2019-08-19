package com.neu.authority.service;


import com.neu.authority.dao.UserDao;
import com.neu.authority.entity.Message;
import com.neu.authority.entity.TUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public Message checkPassword(TUser user) {
        TUser userInDB = userDao.selectUserById(user);
        System.out.println("========" + user.getId());
        if (null == userInDB)
            return new Message(201, "登录失败", new TUser());
        if (!userInDB.getUserPasswd().equals(user.getUserPasswd()))
            return new Message(202, "登录失败", new TUser());
        return new Message(200, "登录成功", userInDB);
    }

    @Override
    public Message selectUser(TUser user) {
        TUser userInDB;
        userInDB = userDao.selectUserById(user);
        if (userInDB != null)
            return new Message(200, "通过用户ID获取用户成功", userInDB);
        userInDB = userDao.selectUserByName(user);
        if (userInDB != null)
            return new Message(200, "通过用户名获取用户成功", userInDB);
        return new Message(201, "获取用户信息失败", userInDB);
    }

    @Override
    public Message insertUser(TUser user) {
        if (userDao.selectUserByName(user) != null)
            return new Message(201, "用户名已存在", null);
        if (userDao.insertUser(user) == 1) {
            return new Message(200, "增加用户成功", user);
        }
        return new Message(202, "增加用户失败", user);
    }

    @Override
    public Message updateUser(TUser user) {
        TUser userInDB = userDao.selectUserByName(user);
        if (userInDB != null && userInDB.getId() != user.getId())
            return new Message(201, "用户名已存在", null);
        if (userDao.updateUser(user) == 1)
            return new Message(200, "修改用户成功", user);
        return new Message(202, "修改用户失败", user);
    }

    @Override
    public Message deleteUser(TUser user) {
        System.out.println("===" + user.getId());
        if (userDao.deleteUser(user) == 1)
            return new Message(200, "删除用户成功", null);
        return new Message(201, "删除用户失败", null);
    }

    @Override
    public List<TUser> selectUsersInFactory(TUser user) {
        return userDao.selectUsersInFactory(user);
    }
}
