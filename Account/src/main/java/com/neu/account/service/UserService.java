package com.neu.account.service;

import com.neu.account.entity.Message;
import com.neu.account.entity.TUser;

public interface UserService {
    // 验证用户密码
    Message checkPassword(String userId, String userPassword);

    // 获取用户信息
    Message getUserInfo(String userId);

    // 注册用户信息
    Message setUserInfo(TUser user);

    // 修改用户信息
    Message updateUserInfo(TUser user);
}
