package com.neu.account.service;

import com.neu.account.entity.Message;
import com.neu.account.entity.TFactory;

public interface FactoryService {
    // 创建工厂，并返回生成的管理员ID和工厂ID
    Message createFactory(TFactory factory);
}
