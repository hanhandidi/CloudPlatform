package com.neu.account.service;

import com.neu.account.entity.Message;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

    // 添加token信息
    Message addAuthInfo(HttpServletRequest request);

    // 获取token信息
    Message getAuthInfo(String authToken, String userId, boolean isScan);

    // 设置token状态
    Message setAuthState(String authToken, String userId);
}
