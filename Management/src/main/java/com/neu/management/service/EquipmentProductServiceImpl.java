package com.neu.management.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.management.dao.EquipmentProductDao;
import com.neu.management.model.TEquipmentProduct;
import com.neu.management.util.Define;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentProductServiceImpl implements EquipmentProductService{

    private final EquipmentProductDao equipmentProductDao;

    @Autowired
    public EquipmentProductServiceImpl(EquipmentProductDao equipmentProductDao) {
        this.equipmentProductDao = equipmentProductDao;
    }

    @Override
    public TEquipmentProduct addEquipmentProduct(TEquipmentProduct tEquipmentProduct) {
        // 一个设备生产一个产品的产能唯一
        if(equipmentProductDao.selectByEquipmentIdAndProductId(tEquipmentProduct) != null){
            return null;
        }else {
            equipmentProductDao.insert(tEquipmentProduct);
            return tEquipmentProduct;
        }
    }

    @Override
    @CacheEvict(value="TEquipmentProduct",key="T(String).valueOf('TEquipmentProduct').concat('-').concat(#id)")
    public void deleteEquipmentProduct(Integer id) {
        equipmentProductDao.deleteById(id);
    }

    @Override
    @CachePut(value="TEquipmentProduct",key="T(String).valueOf('TEquipmentProduct').concat('-').concat(#tEquipmentProduct.id)")
    public TEquipmentProduct updateEquipmentProduct(TEquipmentProduct tEquipmentProduct) {
        // 一个设备生产一个产品的产能唯一
        if(equipmentProductDao.selectByEquipmentIdAndProductId(tEquipmentProduct) != null){
            return null;
        }else {
            equipmentProductDao.update(tEquipmentProduct);
            return tEquipmentProduct;
        }
    }

    @Override
    @Cacheable(value="TEquipmentProduct",key="T(String).valueOf('TEquipmentProduct').concat('-').concat(#id)",unless="#result == null")
    public TEquipmentProduct getEquipmentProduct(Integer id) {
        return equipmentProductDao.selectById(id);
    }

    @Override
    public List<TEquipmentProduct> listByEquipmentId(Integer id) {
        return equipmentProductDao.selectByEquipmentId(id);
    }

    @Override
    public List<TEquipmentProduct> listByProductId(Integer id) {
        return equipmentProductDao.selectByProductId(id);
    }

    @Override
    public List<TEquipmentProduct> listByFactoryId(Integer id) {
        return equipmentProductDao.selectByFactoryId(id);
    }

    @Override
    public PageInfo<TEquipmentProduct> listEquipmentProductByEquipmentId(Integer currPage, Integer id) {
        if(currPage == null)
            currPage = 1;
        //设置从第几页查询N条
        PageHelper.startPage(currPage, Define.PAGE_SIZE);
        //分页查询
        return new PageInfo<>(equipmentProductDao.selectByEquipmentId(id));
    }

    @Override
    public PageInfo<TEquipmentProduct> listEquipmentProductByProductId(Integer currPage, Integer id) {
        if(currPage == null)
            currPage = 1;
        //设置从第几页查询N条
        PageHelper.startPage(currPage, Define.PAGE_SIZE);
        //分页查询
        return new PageInfo<>(equipmentProductDao.selectByProductId(id));
    }

    @Override
    public PageInfo<TEquipmentProduct> listEquipmentProductByFactoryId(Integer currPage, Integer id) {
        if(currPage == null)
            currPage = 1;
        //设置从第几页查询N条
        PageHelper.startPage(currPage, Define.PAGE_SIZE);
        //分页查询
        return new PageInfo<>(equipmentProductDao.selectByFactoryId(id));
    }
}
