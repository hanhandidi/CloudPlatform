package com.neu.management.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.management.dao.DailyWorkDao;
import com.neu.management.dao.EquipmentDao;
import com.neu.management.dao.OrderTrackDao;
import com.neu.management.dao.ProductScheduleDao;
import com.neu.management.model.TDailyWork;
import com.neu.management.model.TEquipment;
import com.neu.management.model.TOrderTrack;
import com.neu.management.model.TProductSchedule;
import com.neu.management.modelVO.DailyWorkVO;
import com.neu.management.modelVO.OrderTrackVO;
import com.neu.management.util.Define;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class OrderTrackServiceImpl implements OrderTrackService {

    private final OrderTrackDao orderTrackDao;
    private final ProductScheduleDao productScheduleDao;
    private final DailyWorkDao dailyWorkDao;

    @Autowired
    public OrderTrackServiceImpl(OrderTrackDao orderTrackDao,ProductScheduleDao productScheduleDao,DailyWorkDao dailyWorkDao) {
        this.orderTrackDao = orderTrackDao;
        this.productScheduleDao = productScheduleDao;
        this.dailyWorkDao = dailyWorkDao;
    }

    @Override
    public TOrderTrack addOrderTrack(TOrderTrack tOrderTrack) {
        orderTrackDao.insert(tOrderTrack);
        return tOrderTrack;
    }

    @Override
    @CacheEvict(value="TOrderTrack",key="T(String).valueOf('TOrderTrack').concat('-').concat(#id)")
    public int deleteById(Integer id,Integer userId) {
        orderTrackDao.deleteById(id,userId);
        return 1;
    }

    @Override
    @CachePut(value="TOrderTrack",key="T(String).valueOf('TOrderTrack').concat('-').concat(#orderTrackVO.id)")
    public TOrderTrack updateOrderTrack(OrderTrackVO orderTrackVO) {
        orderTrackVO.setUpdateTime(new Timestamp(new Date().getTime()));
        if ( orderTrackVO.getWorkingCount() >= orderTrackVO.getQualifiedCount() ){
            orderTrackDao.update(orderTrackVO);
            return orderTrackDao.selectById((int)orderTrackVO.getId());
        }
        return null;
    }

    @Override
    public int jobBook(Integer id, DailyWorkVO dailyWorkVO) {
        if ( dailyWorkVO.getStartTime().getTime() < dailyWorkVO.getEndTime().getTime() && dailyWorkVO.getQualifiedCount() <= dailyWorkVO.getWorkingCount() ){
            // 根据id获取订单跟踪信息
            TOrderTrack tOrderTrack = orderTrackDao.selectById(id);
            // 根据调度id获取调度信息
            TProductSchedule tProductSchedule = tOrderTrack.gettProductSchedule();
            // 根据设备id获取设备信息
            TEquipment tEquipment = tProductSchedule.gettEquipment();
            // 生成报工信息
            TDailyWork tDailyWork = new TDailyWork();
            tDailyWork.setFlag(0);
            tDailyWork.setCreateTime(new Timestamp(new Date().getTime()));
            tDailyWork.setCreateUserid(dailyWorkVO.getCreateUserid());
            tDailyWork.setUpdateTime(new Timestamp(new Date().getTime()));
            tDailyWork.setUpdateUserid(dailyWorkVO.getCreateUserid());
            tDailyWork.setScheduleId(tOrderTrack.getScheduleId());
            tDailyWork.setEquipmentId(tEquipment.getId());
            tDailyWork.setEquipmentSeq(tEquipment.getEquipmentSeq());
            tDailyWork.setStartTime(dailyWorkVO.getStartTime());
            tDailyWork.setEndTime(dailyWorkVO.getEndTime());
            tDailyWork.setWorkingCount(dailyWorkVO.getWorkingCount());
            tDailyWork.setQualifiedCount(dailyWorkVO.getQualifiedCount());
            tDailyWork.setUnqualifiedCout(dailyWorkVO.getWorkingCount() - dailyWorkVO.getQualifiedCount());
            tDailyWork.setCompleteFlag(1);
            tDailyWork.setFactoryId(tDailyWork.getFactoryId());
            tDailyWork.setBak(dailyWorkVO.getBak());
            // 添加报工信息
            dailyWorkDao.insert(tDailyWork);
            // 更新订单追踪信息
            OrderTrackVO orderTrackVO = new OrderTrackVO();
            orderTrackVO.setId(id);
            orderTrackVO.setUpdateUserid(dailyWorkVO.getCreateUserid());
            orderTrackVO.setWorkingCount(dailyWorkVO.getWorkingCount());
            orderTrackVO.setQualifiedCount(dailyWorkVO.getQualifiedCount());
            orderTrackDao.update(orderTrackVO);
            return 1;
        }
        return 0;
    }

    @Override
    public void finishJobBook(Integer orderTrackId,Integer userId) {
        // 报工表结束报工标识 -》0
        TDailyWork tDailyWork = new TDailyWork();
        tDailyWork.setUpdateTime(new Timestamp(new Date().getTime()));
        tDailyWork.setUpdateUserid(userId);
        tDailyWork.setCompleteFlag(0);
        dailyWorkDao.update(tDailyWork);
        // 当前工单置成已完成状态
        TOrderTrack tOrderTrack = orderTrackDao.selectById(orderTrackId);
        TProductSchedule tProductSchedule = productScheduleDao.selectById((int)tOrderTrack.getScheduleId());
        tProductSchedule.setUpdateTime(new Timestamp(new Date().getTime()));
        tProductSchedule.setUpdateUserid(orderTrackId);
        tProductSchedule.setScheduleStatus(30);
        productScheduleDao.updateState(tProductSchedule);
    }


    @Override
    @Cacheable(value="TOrderTrack",key="T(String).valueOf('TOrderTrack').concat('-').concat(#id)",unless="#result == null")
    public TOrderTrack selectById(Integer id) {
        return orderTrackDao.selectById(id);
    }

    @Override
    public PageInfo<TOrderTrack> listOrderTracks(Integer currPage, TOrderTrack tOrderTrack) {
        if(currPage == null)
            currPage = 1;
        //设置从第几页查询N条
        PageHelper.startPage(currPage, Define.PAGE_SIZE);
        //分页查询
        return new PageInfo<>(orderTrackDao.getAll(tOrderTrack));
    }

    @Override
    public List<TOrderTrack> allOrderTracks(TOrderTrack tOrderTrack) {
        return orderTrackDao.getAll(tOrderTrack);
    }
}
