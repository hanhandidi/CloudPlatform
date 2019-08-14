package com.neu.management.service;

import com.github.pagehelper.PageInfo;
import com.neu.management.model.TDailyWork;

import java.util.List;

public interface DailyWorkService {
    // 添加报工信息
    TDailyWork addDailyWork(TDailyWork tDailyWork);

    int deleteById(Integer id);

    TDailyWork updateDailyWork(TDailyWork tDailyWork);

    // 根据ID获取
    TDailyWork selectById(Integer id);

    // 简易列表、查询 所有的该工厂所有的报工信息
    PageInfo<TDailyWork> listDailyWork(Integer currPage, TDailyWork tDailyWork);

    List<TDailyWork> allDailyWork(TDailyWork tDailyWork);
}
