package com.neu.management.service;

import com.github.pagehelper.PageInfo;
import com.neu.management.model.TFactory;

import java.util.List;

public interface FactoryService {
    // 添加
    TFactory addFactory(TFactory tFactory);
    // 删除
    void deleteFactory(Integer id);
    // 更新
    TFactory updateFactory(TFactory tFactory);
    // 根据ID获取
    TFactory getFactory(Integer id);
    // 获取所有
    List<TFactory> getAllFactory();
    // 获取所有并分页
    PageInfo<TFactory> listFactory(Integer currPage, TFactory tFactory);
}
