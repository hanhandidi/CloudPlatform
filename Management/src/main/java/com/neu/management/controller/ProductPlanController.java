package com.neu.management.controller;

import com.neu.management.model.Message;
import com.neu.management.model.TProductPlan;
import com.neu.management.service.ProductPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping(value = "/productPlan")
public class ProductPlanController {

    private final ProductPlanService productPlanService;

    @Autowired
    public ProductPlanController(ProductPlanService productPlanService) {
        this.productPlanService = productPlanService;
    }

    // 添加生产计划
    @ResponseBody
    @PostMapping("add")
    public Message addProductPlan(@RequestBody TProductPlan tProductPlan) {
        Message addProductPlanMessage = new Message();
        tProductPlan.setCreateTime(new Timestamp(new Date().getTime()));
        tProductPlan.setUpdateTime(new Timestamp(new Date().getTime()));
        tProductPlan.setPlanSeq("P" + new Timestamp(new Date().getTime()));
        tProductPlan.setPlanStatus(10);
        TProductPlan tProductPlan1 = productPlanService.addProductPlan(tProductPlan);
        if (tProductPlan1 == null){
            addProductPlanMessage.setCode(202);
            addProductPlanMessage.setMessage("创建生产计划失败，该订单不处于已接单状态！");
        }else {
            productPlanService.selectById((int) tProductPlan1.getId());
            addProductPlanMessage.setCode(200);
            addProductPlanMessage.setMessage("创建生产计划成功！");
            addProductPlanMessage.setData(tProductPlan1);
        }
        return addProductPlanMessage;
    }

    // 根据id删除计划信息 未接单的订单才能删除
    @RequestMapping("delete/{id}")
    public Message deleteProductPlan(@PathVariable Integer id,@RequestBody Integer userId){
        Message deleteProductPlanMessage = new Message();
        if(productPlanService.deleteById(id,userId) == 1){
            deleteProductPlanMessage.setCode(200);
            deleteProductPlanMessage.setMessage("删除计划成功！");
        }else {
            deleteProductPlanMessage.setCode(202);
            deleteProductPlanMessage.setMessage("删除计划失败，订单不属于未接单状态不能生产计划！");
        }
        return deleteProductPlanMessage;
    }

    // 更新计划信息 ok
    @RequestMapping("update")
    public Message updateProductPlan(@RequestBody TProductPlan tProductPlan){
        Message updateProductOrderMessage = new Message();
        tProductPlan.setUpdateTime(new Timestamp(new Date().getTime()));
        if (productPlanService.updateProductPlan(tProductPlan) == null){
            // 序列号重复
            updateProductOrderMessage.setCode(202);
            updateProductOrderMessage.setMessage("更新计划失败，请重试！");
        }else {
            updateProductOrderMessage.setCode(200);
            updateProductOrderMessage.setMessage("更新计划成功！");
            updateProductOrderMessage.setData(tProductPlan);
        }
        return updateProductOrderMessage;
    }

    // 根据id获取计划信息 ok
    @GetMapping("get/{id}")
    public Message getProductPlan(@PathVariable Integer id){
        Message getProductPlanMessage = new Message();
        if (productPlanService.selectById(id) != null){
            getProductPlanMessage.setCode(200);
            getProductPlanMessage.setMessage("获取计划信息成功！");
            getProductPlanMessage.setData(productPlanService.selectById(id));
        }else {
            getProductPlanMessage.setCode(202);
            getProductPlanMessage.setMessage("获取计划信息失败！");
        }
        return getProductPlanMessage;
    }

    // 获取全部计划数据 工厂id、计划状态
    @RequestMapping("getAll")
    public Message listProductPlan(@RequestBody TProductPlan tProductPlan){
        Message listProductPlanMessage = new Message();
        if (productPlanService.allProductSchedule(tProductPlan) != null){
            listProductPlanMessage.setCode(200);
            listProductPlanMessage.setMessage("获取计划信息成功！");
            listProductPlanMessage.setData(productPlanService.allProductSchedule(tProductPlan));
        }else {
            listProductPlanMessage.setCode(202);
            listProductPlanMessage.setMessage("获取计划信息失败！");
        }
        return listProductPlanMessage;
    }

    // 获取全部计划数据、工厂id、计划状态
    @RequestMapping("listPage/{currPage}")
    public Message listPageProductPlan(@PathVariable Integer currPage, @RequestBody TProductPlan tProductPlan){
        Message listProductPlanMessage = new Message();
        if (productPlanService.listProductPlans(currPage,tProductPlan) != null){
            listProductPlanMessage.setCode(200);
            listProductPlanMessage.setMessage("分页获取计划信息成功！");
            listProductPlanMessage.setData(productPlanService.listProductPlans(currPage,tProductPlan));
        }else {
            listProductPlanMessage.setCode(202);
            listProductPlanMessage.setMessage("分页获取计划信息失败！");
        }
        return listProductPlanMessage;
    }

    // 开始生成计划
    @RequestMapping("start/{id}")
    public Message startProductPlan(@PathVariable Integer id){
        Message startProductPlanMessage = new Message();
        if (productPlanService.startProductPlan(id) == 1){
            startProductPlanMessage.setCode(200);
            startProductPlanMessage.setMessage("生产计划启动成功！");
        }else {
            startProductPlanMessage.setCode(202);
            startProductPlanMessage.setMessage("生产计划启动失败！");
        }
        return startProductPlanMessage;
    }

    // 完成计划
    @RequestMapping("finish/{id}")
    public Message finishProductPlan(@PathVariable Integer id){
        Message finishProductPlanMessage = new Message();
        if (productPlanService.isFinished(id) == 1){
            finishProductPlanMessage.setCode(200);
            finishProductPlanMessage.setMessage("计划完成！");
        }else {
            finishProductPlanMessage.setCode(202);
            finishProductPlanMessage.setMessage("计划未完成！");
        }
        return finishProductPlanMessage;
    }
}
