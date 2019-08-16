package com.neu.management.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.management.dao.DailyWorkDao;
import com.neu.management.dao.OrderTrackDao;
import com.neu.management.model.TDailyWork;
import com.neu.management.model.TOrderTrack;
import com.neu.management.util.Define;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyWorkServiceImpl implements DailyWorkService {

    private final OrderTrackDao orderTrackDao;
    private final DailyWorkDao dailyWorkDao;

    @Autowired
    public DailyWorkServiceImpl(DailyWorkDao dailyWorkDao,OrderTrackDao orderTrackDao) {
        this.dailyWorkDao = dailyWorkDao;
        this.orderTrackDao = orderTrackDao;
    }

    @Override
    public TDailyWork addDailyWork(TDailyWork tDailyWork) {
        TOrderTrack tOrderTrack = orderTrackDao.selectById((int)tDailyWork.getOrderTrackId());
        tDailyWork.setScheduleId(tOrderTrack.getScheduleId());
        tDailyWork.setEquipmentId(tOrderTrack.gettProductSchedule().getEquipmentId());
        tDailyWork.setEquipmentSeq(tOrderTrack.gettProductSchedule().gettEquipment().getEquipmentSeq());
        if ( tDailyWork.getEndTime().getTime() > tDailyWork.getStartTime().getTime() && tDailyWork.getWorkingCount() > tDailyWork.getQualifiedCount()){
            tDailyWork.setUnqualifiedCout(tDailyWork.getWorkingCount() - tDailyWork.getQualifiedCount());
            dailyWorkDao.insert(tDailyWork);
            return tDailyWork;
        }
        return null;
    }

    @Override
    @CacheEvict(value="TDailyWork",key="T(String).valueOf('TDailyWork').concat('-').concat(#id)")
    public int deleteById(Integer id) {
        dailyWorkDao.deleteById(id);
        return 1;
    }

    @Override
    @CachePut(value="TDailyWork",key="T(String).valueOf('TDailyWork').concat('-').concat(#tDailyWork.id)")
    public TDailyWork updateDailyWork(TDailyWork tDailyWork) {
        TOrderTrack tOrderTrack = orderTrackDao.selectById((int)tDailyWork.getOrderTrackId());
        tDailyWork.setScheduleId(tOrderTrack.getScheduleId());
        tDailyWork.setEquipmentId(tOrderTrack.gettProductSchedule().getEquipmentId());
        tDailyWork.setEquipmentSeq(tOrderTrack.gettProductSchedule().gettEquipment().getEquipmentSeq());
        if ( tDailyWork.getEndTime().getTime() > tDailyWork.getStartTime().getTime() && tDailyWork.getWorkingCount() > tDailyWork.getQualifiedCount()){
            tDailyWork.setUnqualifiedCout(tDailyWork.getWorkingCount() - tDailyWork.getQualifiedCount());
            dailyWorkDao.update(tDailyWork);
            return dailyWorkDao.selectById((int)tDailyWork.getId());
        }
        return null;
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
