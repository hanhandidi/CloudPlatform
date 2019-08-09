package com.neu.management.service;

import com.neu.management.dao.TUserDao;
import com.neu.management.model.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TUserDao tUserDao;
    @Override
    public TUser selectById(Long id) {
        if (id==null)
            return null;
        return (TUser) tUserDao.selectById(id);
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
