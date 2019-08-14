package com.neu.authority.service;


import com.neu.authority.entity.Message;
import com.neu.authority.entity.TUser;

public interface UserService {

    // 获取用户信息
    Message getUserInfo(String userId);

    // 注册用户信息
    Message setUserInfo(TUser user);

    // 修改用户信息
    Message updateUserInfo(TUser user);
}
