package com.neu.management.service;

import com.github.pagehelper.PageInfo;
import com.neu.management.model.TProductOrder;
import com.neu.management.model.TProductPlan;
import com.neu.management.modelVO.ProductPlanVO;

import java.util.List;

public interface ProductOrderService {
    // 添加订单
    TProductOrder addProductOrder(TProductOrder tProductOrder);

    int deleteById(Integer id);

    TProductOrder updateProductOrder(TProductOrder tProductOrder);

    // 修改订单状态
    TProductOrder updateProductOrderState(TProductOrder tProductOrder);

    // 接受订单
    int acceptProductOrder(Integer id);

    // 拒绝订单
    int refuseProductOrder(Integer id,String bak);

    // 转成生产计划
    TProductPlan planProductOrder(Integer id, ProductPlanVO productPlanVO);

    // 完成订单
    int finishProductOrder(Integer id);

    // 简易查询
    List<TProductOrder> listProductOrder(TProductOrder tProductOrder);

    List<TProductOrder> listProductOrder(Integer id);

    // 根据ID获取
    TProductOrder selectById(Integer id);

    // 简易列表、查询 所有的该工厂所有的订单信息
    PageInfo<TProductOrder> listProductOrders(Integer currPage, TProductOrder tProductOrder);
}
