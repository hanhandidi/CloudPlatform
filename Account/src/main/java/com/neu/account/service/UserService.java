package com.neu.account.service;

import com.neu.account.entity.Message;
import com.neu.account.entity.TUser;

import java.util.List;

public interface UserService {
    // 验证用户密码
    Message checkPassword(TUser user);

    // 获取用户信息
    Message selectUser(TUser user);

    // 注册用户信息
    Message insertUser(TUser user);

    // 修改用户信息
    Message updateUser(TUser user);

    // 删除用户信息
    Message deleteUser(TUser user);

    // 获取用户列表（工厂ID）
    List<TUser> selectUsersInFactory(TUser user);
}
