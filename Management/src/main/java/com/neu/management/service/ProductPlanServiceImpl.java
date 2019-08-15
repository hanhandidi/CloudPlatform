package com.neu.management.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.management.dao.ProductOrderDao;
import com.neu.management.dao.ProductPlanDao;
import com.neu.management.dao.ProductScheduleDao;
import com.neu.management.model.TProductOrder;
import com.neu.management.model.TProductPlan;
import com.neu.management.model.TProductSchedule;
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
public class ProductPlanServiceImpl implements ProductPlanService {

    private final ProductPlanDao productPlanDao;
    private final ProductOrderDao productOrderDao;
    private final ProductScheduleDao productScheduleDao;

    @Autowired
    public ProductPlanServiceImpl(ProductPlanDao productPlanDao, ProductOrderDao productOrderDao,ProductScheduleDao productScheduleDao) {
        this.productPlanDao = productPlanDao;
        this.productOrderDao = productOrderDao;
        this.productScheduleDao = productScheduleDao;
    }


    @Override
    public TProductPlan addProductPlan(TProductPlan tProductPlan) {
        // 已接单的订单创建新的生产计划
        TProductOrder tProductOrder = productOrderDao.selectById((int)tProductPlan.getOrderId());
        if ( tProductOrder.getOrderStatus() == 20 ){
            productPlanDao.insert(tProductPlan);
            return tProductPlan;
        }
        return null;
    }

    @Override
    @CacheEvict(value="TProductPlan",key="T(String).valueOf('TProductPlan').concat('-').concat(#id)")
    public int deleteById(Integer id) {
        // 未启动的计划才能删除
        if ( productPlanDao.selectById(id).getPlanStatus() == 10 ){
            productPlanDao.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    @CachePut(value="TProductPlan",key="T(String).valueOf('TProductPlan').concat('-').concat(#tProductPlan.id)")
    public TProductPlan updateProductPlan(TProductPlan tProductPlan) {
        TProductOrder tProductOrder = productOrderDao.selectById((int)tProductPlan.getOrderId());
        if ( tProductOrder.getOrderStatus() == 20 ){
            productPlanDao.update(tProductPlan);
            return productPlanDao.selectById((int) tProductPlan.getId());
        }
        return null;
    }

    @Override
    public int startProductPlan(Integer id) {
        if ( productPlanDao.selectById(id) != null ){
            TProductPlan tProductPlan = productPlanDao.selectById(id);
            tProductPlan.setPlanStatus(20);
            productPlanDao.update(tProductPlan);
            // 生成一条未开始的工单
            TProductSchedule tProductSchedule = new TProductSchedule();
            tProductSchedule.setFlag(0);
            tProductSchedule.setCreateTime(new Timestamp(new Date().getTime()));
            tProductSchedule.setCreateUserid(tProductPlan.getUpdateUserid());
            tProductSchedule.setUpdateTime(new Timestamp(new Date().getTime()));
            tProductSchedule.setUpdateUserid(tProductPlan.getUpdateUserid());
            tProductSchedule.setScheduleSeq("S" + new Timestamp(new Date().getTime()).getTime());
            tProductSchedule.setScheduleStatus(10);
            tProductSchedule.setPlanId(tProductPlan.getId());
            tProductSchedule.setProductId(tProductPlan.getProductId());
            tProductSchedule.setFactoryId(tProductPlan.getFactoryId());
            // 生成一条未开始的工单，对应设备为空
            productScheduleDao.insert(tProductSchedule);
            // 生产计划开始，订单状态设置为生产中
            TProductOrder tProductOrder = productOrderDao.selectById((int) tProductPlan.getOrderId());
            tProductOrder.setUpdateTime(new Timestamp(new Date().getTime()));
            tProductOrder.setUpdateUserid(tProductPlan.getUpdateUserid());
            tProductOrder.setOrderStatus(40);
            productPlanDao.update(tProductPlan);
            return 1;
        }
        return 0;
    }

    @Override
    public int isFinished(Integer id) {
        // 通过计划id获取所有的生产调度 所有的生产调度都完成了计划就完成了
        List<TProductSchedule> tProductSchedules = productScheduleDao.selectByPlanId(id);
        for (TProductSchedule tProductSchedule : tProductSchedules){
            if ( tProductSchedule.getScheduleStatus() != 30){
                return 0;
            }
        }
        // 将该计划状态设置为已完成
        TProductPlan tProductPlan = productPlanDao.selectById(id);
        tProductPlan.setUpdateTime(new Timestamp(new Date().getTime()));
        tProductPlan.setPlanStatus(30);
        productPlanDao.update(tProductPlan);
        return 1;
    }

    @Override
    @Cacheable(value="TProductPlan",key="T(String).valueOf('TProductPlan').concat('-').concat(#id)",unless="#result == null")
    public TProductPlan selectById(Integer id) {
        return productPlanDao.selectById(id);
    }

    @Override
    public PageInfo<TProductPlan> listProductPlans(Integer currPage, TProductPlan tProductPlan) {
        if(currPage == null)
            currPage = 1;
        //设置从第几页查询N条
        PageHelper.startPage(currPage, Define.PAGE_SIZE);
        //分页查询
        return new PageInfo<>(productPlanDao.getAll(tProductPlan));
    }

    @Override
    public List<TProductPlan> allProductSchedule(TProductPlan tProductPlan) {
        return productPlanDao.getAll(tProductPlan);
    }
}
