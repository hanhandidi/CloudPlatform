package com.neu.management.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.management.dao.DailyWorkDao;
import com.neu.management.model.TDailyWork;
import com.neu.management.util.Define;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyWorkServiceImpl implements DailyWorkService {

    private final DailyWorkDao dailyWorkDao;

    @Autowired
    public DailyWorkServiceImpl(DailyWorkDao dailyWorkDao) {
        this.dailyWorkDao = dailyWorkDao;
    }

    @Override
    public TDailyWork addDailyWork(TDailyWork tDailyWork) {
        dailyWorkDao.insert(tDailyWork);
        return tDailyWork;
    }

    @Override
    @CacheEvict(value="TDailyWork",key="T(String).valueOf('TDailyWork').concat('-').concat(#id)")
    public int deleteById(Integer id,Integer userId) {
        dailyWorkDao.deleteById(id,userId);
        return 1;
    }

    @Override
    @CachePut(value="TDailyWork",key="T(String).valueOf('TDailyWork').concat('-').concat(#tDailyWork.id)")
    public TDailyWork updateDailyWork(TDailyWork tDailyWork) {
        dailyWorkDao.update(tDailyWork);
        return dailyWorkDao.selectById((int)tDailyWork.getId());
    }

    @Override
    @Cacheable(value="TDailyWork",key="T(String).valueOf('TDailyWork').concat('-').concat(#id)",unless="#result == null")
    public TDailyWork selectById(Integer id) {
        return dailyWorkDao.selectById(id);
    }

    @Override
    public PageInfo<TDailyWork> listDailyWork(Integer currPage, TDailyWork tDailyWork) {
        if(currPage == null)
            currPage = 1;
        //设置从第几页查询N条
        PageHelper.startPage(currPage, Define.PAGE_SIZE);
        //分页查询
        return new PageInfo<>(dailyWorkDao.getAll(tDailyWork));
    }

    @Override
    public List<TDailyWork> allDailyWork(TDailyWork tDailyWork) {
        return dailyWorkDao.getAll(tDailyWork);
    }
}
