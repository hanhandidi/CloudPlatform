package com.neu.management.service;

import com.github.pagehelper.PageInfo;
import com.neu.management.model.TEquipmentProduct;

import java.util.List;

public interface EquipmentProductService {
    // 添加产能
    TEquipmentProduct addEquipmentProduct(TEquipmentProduct tEquipmentProduct);
    // 删除产能
    void deleteEquipmentProduct(Integer id);
    // 更新产能
    TEquipmentProduct updateEquipmentProduct(TEquipmentProduct tEquipmentProduct);
    // 根据ID获取产能
    TEquipmentProduct getEquipmentProduct(Integer id);
    // 简易列表(设备ID)
    List<TEquipmentProduct> listByEquipmentId(Integer id);
    // 简易列表(产品ID)
    List<TEquipmentProduct> listByProductId(Integer id);
    // 简易列表(工厂ID)
    List<TEquipmentProduct> listByFactoryId(Integer id);
    // 简易分页列表(设备ID)
    PageInfo<TEquipmentProduct> listEquipmentProductByEquipmentId(Integer currPage, Integer id);
    // 简易分页列表(产品ID)
    PageInfo<TEquipmentProduct> listEquipmentProductByProductId(Integer currPage, Integer id);
    // 简易分页列表(工厂ID)
    PageInfo<TEquipmentProduct> listEquipmentProductByFactoryId(Integer currPage, Integer id);
}
