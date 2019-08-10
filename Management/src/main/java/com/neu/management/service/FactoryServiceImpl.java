package com.neu.management.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.management.dao.FactoryDao;
import com.neu.management.model.TFactory;
import com.neu.management.util.Define;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryServiceImpl implements FactoryService{

    private final FactoryDao factoryDao;

    @Autowired
    public FactoryServiceImpl(FactoryDao factoryDao) {
        this.factoryDao = factoryDao;
    }

    @Override
    public TFactory addFactory(TFactory tFactory) {
        // 工厂的名称和地址不能同时重复
        if(factoryDao.selectByNameAndAddr(tFactory) != null){
            return null;
        }else {
            factoryDao.insert(tFactory);
            return tFactory;
        }
    }

    @Override
    @CacheEvict(value="TFactory",key="T(String).valueOf('TFactory').concat('-').concat(#id)")
    public void deleteFactory(Integer id) {
        factoryDao.deleteById(id);
    }

    @Override
    @CachePut(value="TFactory",key="T(String).valueOf('TFactory').concat('-').concat(#tFactory.id)")
    public TFactory updateFactory(TFactory tFactory) {
        // 工厂的名称和地址不能同时重复
        if(factoryDao.selectByNameAndAddr(tFactory) != null){
            return null;
        }else {
            factoryDao.update(tFactory);
            return tFactory;
        }
    }

    @Override
    @Cacheable(value="TFactory",key="T(String).valueOf('TFactory').concat('-').concat(#id)",unless="#result == null")
    public TFactory getFactory(Integer id) {
        return factoryDao.selectById(id);
    }

    @Override
    public List<TFactory> getAllFactory() {
        return factoryDao.getAll();
    }

    @Override
    public PageInfo<TFactory> listFactory(Integer currPage, TFactory tFactory) {
        if(currPage == null)
            currPage = 1;
        //设置从第几页查询N条
        PageHelper.startPage(currPage, Define.PAGE_SIZE);
        //分页查询
        return new PageInfo<>(factoryDao.selectAll(tFactory));
    }
}
