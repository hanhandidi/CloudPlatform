package com.neu.management.service;

import com.github.pagehelper.PageInfo;
import com.neu.management.model.TProductPlan;

import java.util.List;

public interface ProductPlanService {
    // 添加生产计划
    TProductPlan addProductPlan(TProductPlan tProductPlan);

    int deleteById(Integer id,Integer userId);

    TProductPlan updateProductPlan(TProductPlan tProductPlan);

    // 开始生成计划
    int startProductPlan(Integer id);

    // 生产计划是否完成
    int isFinished(Integer id);

    // 根据ID获取
    TProductPlan selectById(Integer id);

    // 简易列表、查询 所有的该工厂所有的生产计划信息
    PageInfo<TProductPlan> listProductPlans(Integer currPage, TProductPlan tProductPlan);

    List<TProductPlan> allProductSchedule(TProductPlan tProductPlan);
}
