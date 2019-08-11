package com.neu.management.service;

import com.github.pagehelper.PageInfo;
import com.neu.management.model.TEquipment;
import com.neu.management.modelVO.EquipmentSelectVO;
import org.springframework.data.domain.Page;
import java.util.List;

public interface EquipmentService {
    // 添加
    TEquipment addEquipment(TEquipment tEquipment);
    // 根据ID删除
    int deleteEquipment(Integer id);
    // 批量删从
    void deleteEquipmentList(List<Integer> ids);
    // 更新
    TEquipment updateEquipment(TEquipment tEquipment);
    // 根据ID获取
    TEquipment getEquipment(Integer id);
    // 简易列表、查询(所有数据)
    PageInfo<TEquipment> listEquipment(Integer currPage,TEquipment tEquipment);
    // 简易列表(工厂设备)
    PageInfo<TEquipment> listEquipment(Integer currPage,Integer id);
    // 查询：根据产品名称、设备名称查询当前工厂所有相关设备内容列表
    PageInfo<TEquipment> selectEquipment(Integer currPage,EquipmentSelectVO equipmentSelectVO);
    // 文件检索
    Page<TEquipment> search(Integer currPage, String equipmentName);
    // 文件检索 高亮
    PageInfo<TEquipment> searchHighlight(Integer currPage, String equipmentName);
}
