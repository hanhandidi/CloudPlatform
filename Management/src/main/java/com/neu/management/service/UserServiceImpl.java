package com.neu.management.service;

import com.neu.management.dao.TUserDao;
import com.neu.management.model.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TUserDao tUserMapper;
    @Override
    public TUser selectById(long id) {
        if (id==0)
            return null;
        return (TUser) tUserMapper.selectById((long) id);
    }

    @Override
    public TUser selectForLogin(TUser tUser) {
//        if(tUser=null||tUser.getUserName()==null||tUser.getUserPasswd()==null)
////        {
////
////        }
        return null;
    }
}
