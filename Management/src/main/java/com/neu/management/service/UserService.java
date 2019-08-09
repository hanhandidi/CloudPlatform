package com.neu.management.service;

import com.neu.management.model.TUser;

public interface UserService {
    public TUser selectById(Long id);
    public TUser selectForLogin(TUser tUser);
}
