package com.neu.account.service;

import com.neu.account.dao.FactoryDao;
import com.neu.account.dao.UserDao;
import com.neu.account.entity.Message;
import com.neu.account.entity.TFactory;
import com.neu.account.entity.TUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.UUID;

@Service
public class FactoryServiceImpl implements FactoryService {

    @Resource
    FactoryDao factoryDao;
    @Resource
    UserDao userDao;

    @Override
    public Message createFactory(TFactory factory) {
        if (factoryDao.getByName(factory.getFactoryName()) != null)
            return new Message(201, "工厂名已存在", null);

        // 通过UUID生成随机信息
//        String randomInfo = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);

        // 默认注册一位管理员，等待后续修改
        TUser user = new TUser();
        userDao.setUserInfo(user);
        // 将该userId作为工厂创建人ID置入
        factory.setFlag(0); // 有效工厂
        factory.setCreateUserid(user.getId());
        factory.setUpdateUserid(user.getId());
        factory.setFactoryStatus(0);
        factoryDao.addFactory(factory);
        // 返回预注册的用户ID和工厂ID
        HashMap<String, Long> hashMap = new HashMap<>();
        hashMap.put("userId", user.getId());
        hashMap.put("factoryId", factory.getId());
        return new Message(200, "预注册工厂成功", hashMap);
    }
}
