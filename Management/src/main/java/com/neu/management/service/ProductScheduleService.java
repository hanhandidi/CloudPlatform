package com.neu.management.service;

import com.github.pagehelper.PageInfo;
import com.neu.management.model.TProductSchedule;
import com.neu.management.modelVO.ProductScheduleVO;

import java.util.List;

public interface ProductScheduleService {
    // 添加生产调度信息
    TProductSchedule addProductSchedule(TProductSchedule tProductSchedule);

    int deleteById(Integer id,Integer userId);

    // 为工单指定生产设备
    TProductSchedule setEquipmentProductSchedule(ProductScheduleVO productScheduleVO);

    // 启动工单
    void startProductSchedule(TProductSchedule tProductSchedule);

    // 根据ID获取
    TProductSchedule selectById(Integer id);

    // 简易列表、查询 所有的该工厂所有的工单信息
    PageInfo<TProductSchedule> listProductSchedules(Integer currPage, TProductSchedule tProductSchedule);

    // 不分页
    List<TProductSchedule> allProductSchedule(TProductSchedule tProductSchedule);
}
