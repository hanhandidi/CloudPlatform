package com.neu.account.service;

import com.neu.account.entity.Message;

public interface LoginService {
    // 验证用户密码
    Message checkPassword(String userId, String userPassword);
    // 获取用户信息
    Message getUserInfo(String userId);
}
