package com.neu.management.service;

import com.github.pagehelper.PageInfo;
import com.neu.management.model.TEquipment;

import java.awt.print.Book;
import java.util.List;

public interface EquipmentService {
    int addEquipment(TEquipment tEquipment);

    void deleteEquipment(Integer id);

    void deleteEquipmentList(List<Integer> ids);

    int updateEquipment(TEquipment tEquipment);

    TEquipment getEquipment(Integer id);

    TEquipment getEquipmentBySeq(String equipmentSeq);

    PageInfo<TEquipment> listEquipment(Integer currPage,TEquipment tEquipment);
}
