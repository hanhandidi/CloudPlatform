package com.neu.management.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.management.dao.*;
import com.neu.management.model.*;
import com.neu.management.modelVO.ProductScheduleVO;
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
public class ProductScheduleServiceImpl implements ProductScheduleService{

    private final ProductScheduleDao productScheduleDao;
    private final ProductPlanDao productPlanDao;
    private final EquipmentProductDao equipmentProductDao;
    private final OrderTrackDao orderTrackDao;

    @Autowired
    public ProductScheduleServiceImpl(ProductScheduleDao productScheduleDao,ProductPlanDao productPlanDao,EquipmentProductDao equipmentProductDao,OrderTrackDao orderTrackDao) {
        this.productScheduleDao = productScheduleDao;
        this.productPlanDao = productPlanDao;
        this.equipmentProductDao = equipmentProductDao;
        this.orderTrackDao = orderTrackDao;
    }

    @Override
    public TProductSchedule addProductSchedule(TProductSchedule tProductSchedule) {
        TProductPlan tProductPlan = productPlanDao.selectById((int)tProductSchedule.getPlanId());
        // 已启动的生产计划才能创建工单
        if ( tProductPlan.getPlanStatus() == 20 ){
            productScheduleDao.insert(tProductSchedule);
            // 生成生产跟踪记录
            TOrderTrack tOrderTrack = new TOrderTrack();
            tOrderTrack.setFlag(0);
            tOrderTrack.setCreateTime(new Timestamp(new Date().getTime()));
            tOrderTrack.setCreateUserid(tProductSchedule.getUpdateUserid());
            tOrderTrack.setUpdateTime(new Timestamp(new Date().getTime()));
            tOrderTrack.setUpdateUserid(tProductSchedule.getUpdateUserid());
            tOrderTrack.setScheduleId(tProductSchedule.getId());
            tOrderTrack.setPlanId(tProductSchedule.getPlanId());
            tOrderTrack.setProductId(tProductSchedule.getProductId());
            tOrderTrack.setFactoryId(tProductSchedule.getFactoryId());
            // 添加生产跟踪记录
            orderTrackDao.insert(tOrderTrack);
            return tProductSchedule;
        }
        return null;
    }

    @Override
    @CacheEvict(value="TProductSchedule",key="T(String).valueOf('TProductSchedule').concat('-').concat(#id)")
    public int deleteById(Integer id,Integer userId) {
        // 未开始调度可以删除
        if ( productScheduleDao.selectById(id).getScheduleStatus() == 10 ){
            productPlanDao.deleteById(id, userId);
            return 1;
        }
        return 0;
    }

    @Override
    @CachePut(value="TProductSchedule",key="T(String).valueOf('TProductSchedule').concat('-').concat(#productScheduleVO.id)")
    public TProductSchedule setEquipmentProductSchedule(ProductScheduleVO productScheduleVO) {
        productScheduleVO.setUpdateTime(new Timestamp(new Date().getTime()));
        // 根据ID获取生产调度
        TProductSchedule tProductSchedule = productScheduleDao.selectById((int)productScheduleVO.getId());
        // 检验设备是否能够生产该产品
        TEquipmentProduct tEquipmentProduct = new TEquipmentProduct();
        tEquipmentProduct.setEquipmentId(productScheduleVO.getEquipmentId());
        tEquipmentProduct.setProductId(tProductSchedule.getProductId());
        TEquipmentProduct tEquipmentProduct1 = equipmentProductDao.selectByEquipmentIdAndProductId(tEquipmentProduct);
        // 检验设备产能是否满足
        // 计算从当前日期到截止时间的天数
        long daysBetween = (productScheduleVO.getEndDate().getTime() - productScheduleVO.getStartDate().getTime() + 1000000)/(60*60*24*1000);
        if ( tEquipmentProduct1 != null ){
            int capacity = 0;
            switch ((int) tEquipmentProduct1.getUnit()){
                case 10:
                    capacity = (int) (daysBetween * tEquipmentProduct1.getYield());
                    break;
                case 20:
                    capacity = (int) (daysBetween * (tEquipmentProduct1.getYield() / 30));
                    break;
                case 30:
                    capacity = (int) (daysBetween * (tEquipmentProduct1.getYield() / 365));
                    break;
                case 40:
                    capacity = (int) (daysBetween * (tEquipmentProduct1.getYield() * 12));
                    break;
                default:
                    break;
            }
            if (capacity >= productScheduleVO.getScheduleCount()){
                productScheduleDao.update(productScheduleVO);
                return productScheduleDao.selectById((int)productScheduleVO.getId());
            }
        }
        return null;
    }

    @Override
    @CachePut(value="TProductSchedule",key="T(String).valueOf('TProductSchedule').concat('-').concat(#tProductSchedule.id)")
    public void startProductSchedule(TProductSchedule tProductSchedule) {
        tProductSchedule.setUpdateTime(new Timestamp(new Date().getTime()));
        productScheduleDao.updateState(tProductSchedule);
    }

    @Override
    @Cacheable(value="TProductSchedule",key="T(String).valueOf('TProductSchedule').concat('-').concat(#id)",unless="#result == null")
    public TProductSchedule selectById(Integer id) {
        return productScheduleDao.selectById(id);
    }

    @Override
    public PageInfo<TProductSchedule> listProductSchedules(Integer currPage, TProductSchedule tProductSchedule) {
        if(currPage == null)
            currPage = 1;
        //设置从第几页查询N条
        PageHelper.startPage(currPage, Define.PAGE_SIZE);
        //分页查询
        return new PageInfo<>(productScheduleDao.getAll(tProductSchedule));
    }

    @Override
    public List<TProductSchedule> allProductSchedule(TProductSchedule tProductSchedule) {
        return productScheduleDao.getAll(tProductSchedule);
    }
}
