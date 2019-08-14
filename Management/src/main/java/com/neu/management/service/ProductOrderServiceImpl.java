package com.neu.management.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.management.dao.EquipmentProductDao;
import com.neu.management.dao.ProductOrderDao;
import com.neu.management.dao.ProductPlanDao;
import com.neu.management.model.TEquipmentProduct;
import com.neu.management.model.TProductOrder;
import com.neu.management.model.TProductPlan;
import com.neu.management.modelVO.ProductPlanVO;
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
public class ProductOrderServiceImpl implements ProductOrderService {

    private final ProductOrderDao productOrderDao;
    private final EquipmentProductDao equipmentProductDao;
    private final ProductPlanDao productPlanDao;

    @Autowired
    public ProductOrderServiceImpl(ProductOrderDao productOrderDao, EquipmentProductDao equipmentProductDao,ProductPlanDao productPlanDao) {
        this.productOrderDao = productOrderDao;
        this.equipmentProductDao = equipmentProductDao;
        this.productPlanDao = productPlanDao;
    }

    @Override
    public TProductOrder addProductOrder(TProductOrder tProductOrder) {
        // 只有当前工厂有产品才有订单处理能力、有这个产品才能创建订单
        // 检查该工厂是否能够生产该产品
        if ( tProductOrder.getEndDate() == null){
            return null;
        }
        if ( productOrderDao.haveThisProductInFactory(tProductOrder) != null ){
            productOrderDao.insert(tProductOrder);
            return tProductOrder;
        }
        return null;
    }

    @Override
    @CacheEvict(value="TProductOrder",key="T(String).valueOf('TProductOrder').concat('-').concat(#id)")
    public int deleteById(Integer id) {
        // 20：已接单  40：生产中  状态下不能删除
        if ( productOrderDao.isCanDelete(id) == null ){
            productOrderDao.deleteById(id);
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    @CachePut(value="TProductOrder",key="T(String).valueOf('TProductOrder').concat('-').concat(#tProductOrder.id)")
    public TProductOrder updateProductOrder(TProductOrder tProductOrder) {
        // 只有当前工厂有产品才有订单处理能力、有这个产品才能创建订单
        // 检查该工厂是否能够生产该产品
        if ( tProductOrder.getEndDate() == null){
            return null;
        }
        if ( productOrderDao.haveThisProductInFactory(tProductOrder) != null ){
            productOrderDao.update(tProductOrder);
            return productOrderDao.selectById((int) tProductOrder.getId());
        }
        return null;
    }

    @Override
    @CachePut(value="TProductOrder",key="T(String).valueOf('TProductOrder').concat('-').concat(#tProductOrder.id)")
    public TProductOrder updateProductOrderState(TProductOrder tProductOrder) {
        productOrderDao.updateState(tProductOrder);
        return productOrderDao.selectById((int)tProductOrder.getId());
    }

    @Override
    public int acceptProductOrder(Integer id) {
        // 检验是否能够接单
        // 根据id获取订单信息
        TProductOrder tProductOrder = productOrderDao.selectById(id);
        // 计算从当前日期到截止时间的天数
        long daysBetween = (tProductOrder.getEndDate().getTime() - new Date().getTime() + 1000000)/(60*60*24*1000);
        // System.out.println(daysBetween);
        List<TEquipmentProduct> tEquipmentProducts = equipmentProductDao.selectByFactoryIdAndProductId((int)tProductOrder.getFactoryId(),(int)tProductOrder.getProductId());
        int totalCapacity = 0;
        for (TEquipmentProduct tEquipmentProduct : tEquipmentProducts){
            // System.out.println(tEquipmentProduct);
            switch ((int) tEquipmentProduct.getUnit()){
                case 10:
                    totalCapacity += daysBetween * tEquipmentProduct.getYield();
                    break;
                case 20:
                    totalCapacity += daysBetween * (tEquipmentProduct.getYield() / 30);
                    break;
                case 30:
                    totalCapacity += daysBetween * (tEquipmentProduct.getYield() / 365);
                    break;
                case 40:
                    totalCapacity += daysBetween * (tEquipmentProduct.getYield() * 12);
                    break;
                default:
                    break;
            }
        }
        // System.out.println(totalCapacity);
        // 计算该工厂对该产品的产能是否大于订单数量
        if ( totalCapacity >= (int)tProductOrder.getProductCount()){
            // 可接单 状态更新为已接单状态
            tProductOrder.setOrderStatus(20);
            productOrderDao.updateState(tProductOrder);
            return 1;
        }
        return 0;
    }

    @Override
    public int refuseProductOrder(Integer id, String bak) {
        // 根据id获取订单信息
        if ( bak != null && !bak.equals("") ){
            TProductOrder tProductOrder = productOrderDao.selectById(id);
            tProductOrder.setOrderStatus(30);
            productOrderDao.updateState(tProductOrder);
            return 1;
        }
        return 0;
    }

    @Override
    public TProductPlan planProductOrder(Integer id, ProductPlanVO productPlanVO) {
        // 根据id获取订单信息
        TProductOrder tProductOrder = productOrderDao.selectById(id);
        // 已接单才可转为生产计划
        if ( tProductOrder.getOrderStatus() == 20 ){
            TProductPlan tProductPlan = new TProductPlan();
            tProductPlan.setFlag(0);
            tProductPlan.setCreateTime(new Timestamp(new Date().getTime()));
            tProductPlan.setCreateUserid(productPlanVO.getUserId());
            tProductPlan.setUpdateTime(new Timestamp(new Date().getTime()));
            tProductPlan.setUpdateUserid(productPlanVO.getUserId());
            tProductPlan.setPlanSeq("P" + new Timestamp(new Date().getTime()).getTime());
            tProductPlan.setOrderId(tProductOrder.getId());
            tProductPlan.setProductId(tProductOrder.getProductId());
            tProductPlan.setPlanCount(tProductOrder.getProductCount());
            tProductPlan.setDeliveryDate(tProductOrder.getEndDate());
            tProductPlan.setPlanStartDate(productPlanVO.getPlanStartDate());
            tProductPlan.setPlanEndDate(productPlanVO.getPlanEndDate());
            tProductPlan.setPlanStatus(10);
            tProductPlan.setFactoryId(tProductOrder.getFactoryId());
            // 添加生产计划
            productPlanDao.insert(tProductPlan);
            return tProductPlan;
        }
        return null;
    }

    @Override
    public int finishProductOrder(Integer id) {
        // 判断是否完成订单  看生产计划是否完成  所有的生产调度都已完成
        List<TProductPlan> tProductPlans = productPlanDao.selectByOrderId(id);
        for (TProductPlan tProductPlan:tProductPlans){
            if ( tProductPlan.getPlanStatus() != 30 ){
                return 0;
            }
        }
        // 该订单对应的所有生产计划都已完成
        TProductOrder tProductOrder = productOrderDao.selectById(id);
        tProductOrder.setUpdateTime(new Timestamp(new Date().getTime()));
        tProductOrder.setOrderStatus(50);
        productOrderDao.updateState(tProductOrder);
        return 1;
    }

    @Override
    public List<TProductOrder> listProductOrder(TProductOrder tProductOrder) {
        return productOrderDao.getAll(tProductOrder);
    }

    @Override
    @Cacheable(value="TProductOrder",key="T(String).valueOf('TProductOrder').concat('-').concat(#id)",unless="#result == null")
    public TProductOrder selectById(Integer id) {
        return productOrderDao.selectById(id);
    }

    @Override
    public PageInfo<TProductOrder> listProductOrders(Integer currPage, TProductOrder tProductOrder) {
        if(currPage == null)
            currPage = 1;
        //设置从第几页查询N条
        PageHelper.startPage(currPage, Define.PAGE_SIZE);
        //分页查询
        return new PageInfo<>(productOrderDao.getAll(tProductOrder));
    }
}
