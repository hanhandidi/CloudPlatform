package com.neu.management.service;

import com.github.pagehelper.PageInfo;
import com.neu.management.model.TEquipment;
import org.springframework.data.domain.Page;
import java.util.List;

public interface EquipmentService {
    TEquipment addEquipment(TEquipment tEquipment);

    void deleteEquipment(Integer id);

    void deleteEquipmentList(List<Integer> ids);

    TEquipment updateEquipment(TEquipment tEquipment);

    TEquipment getEquipment(Integer id);

    TEquipment getEquipmentBySeq(String equipmentSeq);

    PageInfo<TEquipment> listEquipment(Integer currPage,TEquipment tEquipment);

    Page<TEquipment> search(Integer currPage, String equipmentName);

    PageInfo<TEquipment> searchHighlight(Integer currPage, String equipmentName);
}
