package com.neu.management.controller;

import com.neu.management.model.Message;
import com.neu.management.model.TProductOrder;
import com.neu.management.model.TProductPlan;
import com.neu.management.modelVO.ProductPlanVO;
import com.neu.management.service.ProductOrderService;
import com.neu.management.service.ProductPlanService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping(value = "/productOrder")
public class ProductOrderController {

    private final ProductOrderService productOrderService;

    @Autowired
    public ProductOrderController(ProductOrderService productOrderService, ProductPlanService productPlanService) {
        this.productOrderService = productOrderService;
    }

    // 添加订单信息 ok
    @ResponseBody
    @PostMapping("add")
    public Message addProductOrder(@RequestBody TProductOrder tProductOrder) {
        Message addProductOrderMessage = new Message();
        tProductOrder.setCreateTime(new Timestamp(new Date().getTime()));
        tProductOrder.setUpdateTime(new Timestamp(new Date().getTime()));
        tProductOrder.setUpdateUserid(tProductOrder.getCreateUserid());
        tProductOrder.setFlag(0);
        tProductOrder.setOrderSeq("O" + new Timestamp(new Date().getTime()).getTime());
        tProductOrder.setOrderSource(0);
        TProductOrder tProductOrder1 = productOrderService.addProductOrder(tProductOrder);
        if (tProductOrder1 == null){
            addProductOrderMessage.setCode(202);
            addProductOrderMessage.setMessage("创建订单失败，工厂无法处理该订单！");
        }else {
            productOrderService.selectById((int) tProductOrder1.getId());
            addProductOrderMessage.setCode(200);
            addProductOrderMessage.setMessage("创建订单成功！");
            addProductOrderMessage.setData(tProductOrder1);
        }
        return addProductOrderMessage;
    }

    // 根据id删除订单信息 ok 要求已关联启动工单的设备不可删除
    @ApiOperation("根据id删除订单信息 要求已关联启动工单的设备不可删除")
    @ApiImplicitParam(name="id",value="id",dataType="Integer")
    @DeleteMapping("delete/{id}")
    public Message deleteProductOrder(@PathVariable Integer id){
        Message deleteProductOrderMessage = new Message();
        if(productOrderService.deleteById(id) == 1){
            deleteProductOrderMessage.setCode(200);
            deleteProductOrderMessage.setMessage("删除订单成功！");
        }else {
            deleteProductOrderMessage.setCode(202);
            deleteProductOrderMessage.setMessage("删除订单失败，改订单已被接受或者正在生产，不可删除！");
        }
        return deleteProductOrderMessage;
    }

    // 更新订单信息 ok
    @ApiOperation("更新订单信息")
    @ApiImplicitParam(name="tProductOrder",value="订单实体类",dataType="TProductOrder")
    @PutMapping("update")
    public Message updateProductOrder(@RequestBody TProductOrder tProductOrder){
        Message updateProductOrderMessage = new Message();
        tProductOrder.setUpdateTime(new Timestamp(new Date().getTime()));
        TProductOrder tProductOrder1 = productOrderService.updateProductOrder(tProductOrder);
        if ( tProductOrder1 == null){
            // 序列号重复
            updateProductOrderMessage.setCode(202);
            updateProductOrderMessage.setMessage("更新订单失败，请重试！");
        }else {
            updateProductOrderMessage.setCode(200);
            updateProductOrderMessage.setMessage("更新订单成功！");
            updateProductOrderMessage.setData(tProductOrder1);
        }
        return updateProductOrderMessage;
    }

    // 更新订单状态信息 ok
    @ApiOperation("更新订单状态信息")
    @ApiImplicitParam(name="tProductOrder",value="订单实体类",dataType="TProductOrder")
    @PutMapping("updateState")
    public Message updateProductOrderState(@RequestBody TProductOrder tProductOrder){
        Message updateProductOrderStateMessage = new Message();
        tProductOrder.setUpdateTime(new Timestamp(new Date().getTime()));
        TProductOrder tProductOrder1 = productOrderService.updateProductOrderState(tProductOrder);
        if ( tProductOrder1 == null){
            updateProductOrderStateMessage.setCode(202);
            updateProductOrderStateMessage.setMessage("更新订单状态失败！");
        }else {
            updateProductOrderStateMessage.setCode(200);
            updateProductOrderStateMessage.setMessage("更新订单状态成功！");
            updateProductOrderStateMessage.setData(tProductOrder1);
        }
        return updateProductOrderStateMessage;
    }

    // 根据id获取订单信息 ok
    @ApiOperation("根据id获取订单信息")
    @ApiImplicitParam(name="id",value="id",dataType="Integer")
    @GetMapping("get/{id}")
    public Message getProductOrder(@PathVariable Integer id){
        Message getProductOrderMessage = new Message();
        if (productOrderService.selectById(id) != null){
            getProductOrderMessage.setCode(200);
            getProductOrderMessage.setMessage("获取订单信息成功！");
            getProductOrderMessage.setData(productOrderService.selectById(id));
        }else {
            getProductOrderMessage.setCode(202);
            getProductOrderMessage.setMessage("获取订单信息失败！");
        }
        return getProductOrderMessage;
    }

    // 获取全部订单数据 状态 工厂id ok
    @ApiOperation("获取全部订单数据 状态 工厂id ")
    @ApiImplicitParam(name="tProductOrder",value="订单实体类",dataType="TProductOrder")
    @PostMapping("getAll")
    public Message listPageTProductOrder(@RequestBody TProductOrder tProductOrder){
        Message listTProductOrderMessage = new Message();
        if (productOrderService.listProductOrder(tProductOrder) != null){
            listTProductOrderMessage.setCode(200);
            listTProductOrderMessage.setMessage("查询订单信息成功！");
            listTProductOrderMessage.setData(productOrderService.listProductOrder(tProductOrder));
        }else {
            listTProductOrderMessage.setCode(202);
            listTProductOrderMessage.setMessage("查询订单信息失败！");
        }
        return listTProductOrderMessage;
    }

    // 获取全部订单数据、分页、简易查找 ok
    @ApiOperation("获取全部订单数据、分页、简易查找")
    @ApiImplicitParams({
            @ApiImplicitParam(name="currPage",value="当前页",dataType="Integer"),
            @ApiImplicitParam(name="tProductOrder",value="订单实体类",dataType="TProductOrder")
    })
    @PostMapping("listPage/{currPage}")
    public Message listPageTProductOrder(@PathVariable Integer currPage, @RequestBody TProductOrder tProductOrder){
        Message listTProductOrderMessage = new Message();
        if (productOrderService.listProductOrders(currPage,tProductOrder) != null){
            listTProductOrderMessage.setCode(200);
            listTProductOrderMessage.setMessage("分页查询订单信息成功！");
            listTProductOrderMessage.setData(productOrderService.listProductOrders(currPage,tProductOrder));
        }else {
            listTProductOrderMessage.setCode(202);
            listTProductOrderMessage.setMessage("分页查询订单信息失败！");
        }
        return listTProductOrderMessage;
    }

    // 接单 ok
    @ApiOperation("接单")
    @ApiImplicitParam(name="id",value="id",dataType="Integer")
    @PostMapping("accept/{id}")
    public Message acceptProductOrder(@PathVariable Integer id){
        Message acceptProductOrderMessage = new Message();
        if ( productOrderService.acceptProductOrder(id) == 1 ){
            acceptProductOrderMessage.setCode(200);
            acceptProductOrderMessage.setMessage("接单成功！");
        }else {
            acceptProductOrderMessage.setCode(202);
            acceptProductOrderMessage.setMessage("产能不足，接单失败！");
        }
        return acceptProductOrderMessage;
    }

    // 拒单 ok
    @ApiOperation("拒单")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="id",dataType="Integer"),
            @ApiImplicitParam(name="bak",value="备注",dataType="String")
    })
    @PostMapping("refuse/{id}")
    public Message refuseProductOrder(@PathVariable Integer id,@RequestBody String bak){
        Message refuseProductOrderMessage = new Message();
        if ( productOrderService.refuseProductOrder(id,bak) == 1 ){
            refuseProductOrderMessage.setCode(200);
            refuseProductOrderMessage.setMessage("拒单成功！");
        }else {
            refuseProductOrderMessage.setCode(202);
            refuseProductOrderMessage.setMessage("拒单失败！");
        }
        return refuseProductOrderMessage;
    }

    // 转成生产计划 ok
    @ApiOperation("转成生产计划")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="id",dataType="Integer"),
            @ApiImplicitParam(name="productPlanVO",value="生成计划数据接收类",dataType="ProductPlanVO")
    })
    @PostMapping("plan/{id}")
    public Message planProductOrder(@PathVariable Integer id, @RequestBody ProductPlanVO productPlanVO){
        Message planProductOrderMessage = new Message();
        TProductPlan tProductPlan = productOrderService.planProductOrder(id,productPlanVO);
        if ( tProductPlan != null ){
            planProductOrderMessage.setCode(200);
            planProductOrderMessage.setMessage("生成生产计划成功！");
            planProductOrderMessage.setData(tProductPlan);
        }else {
            planProductOrderMessage.setCode(202);
            planProductOrderMessage.setMessage("生成生产计划失败！");
        }
        return planProductOrderMessage;
    }

    // 订单完成 ok
    @ApiOperation("订单完成")
    @ApiImplicitParam(name="id",value="id",dataType="Integer")
    @PostMapping("finish/{id}")
    public Message finishProductOrder(@PathVariable Integer id){
        Message finishProductOrderMessage = new Message();
        if ( productOrderService.finishProductOrder(id) == 1 ){
            finishProductOrderMessage.setCode(200);
            finishProductOrderMessage.setMessage("订单完成！");
        }else {
            finishProductOrderMessage.setCode(200);
            finishProductOrderMessage.setMessage("订单尚未完成！");
        }
        return finishProductOrderMessage;
    }
}
