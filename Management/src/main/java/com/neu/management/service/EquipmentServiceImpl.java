package com.neu.management.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.management.dao.EquipmentDao;
import com.neu.management.model.TEquipment;
import com.neu.management.util.Define;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentServiceImpl implements EquipmentService{
    private final EquipmentDao equipmentDao;

    @Autowired
    public EquipmentServiceImpl(EquipmentDao equipmentDao) {
        this.equipmentDao = equipmentDao;
    }

    @Override
    public int addEquipment(TEquipment tEquipment) {
        // 设备序列号
        String seq = tEquipment.getEquipmentSeq();
        // 根据序列号查询（设备序列号不能重复）
        if (equipmentDao.selectBySeq(seq) != null){
            return 1;
        }else {
            equipmentDao.insert(tEquipment);
            return 0;
        }
    }

    @Override
    public void deleteEquipment(Integer id) {
        equipmentDao.deleteById(id);
    }

    @Override
    public int updateEquipment(TEquipment tEquipment) {
        // 设备序列号
        String seq = tEquipment.getEquipmentSeq();
        // 根据序列号查询（设备序列号不能重复）
        if (equipmentDao.selectBySeq(seq) != null){
            return 1;
        }else {
            equipmentDao.update(tEquipment);
            return 0;
        }
    }

    @Override
    public TEquipment getEquipment(Integer id) {
        return equipmentDao.selectById(id);
    }

    @Override
    public TEquipment getEquipmentBySeq(String equipmentSeq) {
        return equipmentDao.selectBySeq(equipmentSeq);
    }

    @Override
    public PageInfo<TEquipment> listEquipment(Integer currPage, TEquipment tEquipment) {
        if(currPage == null)
            currPage = 1;
        //设置从第几页查询N条
        PageHelper.startPage(currPage, Define.PAGE_SIZE);
        //分页查询
        return new PageInfo<>(equipmentDao.selectAll(tEquipment));
    }
}
