package com.neu.management.service;

import com.neu.management.model.TUser;

public interface UserService {
    TUser selectById(Integer id);
    TUser selectForLogin(TUser tUser);
}
