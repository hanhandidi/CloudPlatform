package com.neu.management.controller;

import com.neu.management.model.Message;
import com.neu.management.model.TProductSchedule;
import com.neu.management.modelVO.ProductScheduleVO;
import com.neu.management.service.ProductScheduleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping(value = "/productSchedule")
public class ProductScheduleController {

    private final ProductScheduleService productScheduleService;

    @Autowired
    public ProductScheduleController(ProductScheduleService productScheduleService) {
        this.productScheduleService = productScheduleService;
    }

    // 添加工单信息 ok
    @ApiOperation("添加工单信息")
    @ApiImplicitParam(name="tProductSchedule",value="工单实体类",dataType="TProductSchedule")
    @PostMapping("add")
    public Message addProductSchedule(@RequestBody TProductSchedule tProductSchedule) {
        Message addProductScheduleMessage = new Message();
        tProductSchedule.setCreateTime(new Timestamp(new Date().getTime()));
        tProductSchedule.setUpdateTime(new Timestamp(new Date().getTime()));
        tProductSchedule.setScheduleSeq("S" + new Timestamp(new Date().getTime()).getTime());
        tProductSchedule.setScheduleStatus(10);
        tProductSchedule.setUpdateUserid(tProductSchedule.getCreateUserid());
        TProductSchedule tProductSchedule1 = productScheduleService.addProductSchedule(tProductSchedule);
        if (tProductSchedule1 == null){
            addProductScheduleMessage.setCode(202);
            addProductScheduleMessage.setMessage("创建工单信息失败，已启动的生产计划才能创建工单！");
        }else {
            productScheduleService.selectById((int) tProductSchedule1.getId());
            addProductScheduleMessage.setCode(200);
            addProductScheduleMessage.setMessage("创建工单信息成功！");
            addProductScheduleMessage.setData(tProductSchedule1);
        }
        return addProductScheduleMessage;
    }

    // 根据id删除工单信息
    @ApiOperation("根据id删除工单信息")
    @ApiImplicitParam(name="id",value="id",dataType="Integer")
    @DeleteMapping("delete/{id}")
    public Message deleteProductSchedule(@PathVariable Integer id){
        Message deleteProductPlanMessage = new Message();
        if(productScheduleService.deleteById(id) == 1){
            deleteProductPlanMessage.setCode(200);
            deleteProductPlanMessage.setMessage("删除工单信息成功！");
        }else {
            deleteProductPlanMessage.setCode(202);
            deleteProductPlanMessage.setMessage("删除工单信息失败，未开始调度才可以删除！");
        }
        return deleteProductPlanMessage;
    }

    // 更新工单信息 ok
    @ApiOperation("更新工单信息")
    @ApiImplicitParam(name="productScheduleVO",value="工单数据接收类",dataType="ProductScheduleVO")
    @PutMapping("update")
    public Message updateProductSchedule(@RequestBody ProductScheduleVO productScheduleVO){
        Message updateProductOrderMessage = new Message();
        productScheduleVO.setUpdateTime(new Timestamp(new Date().getTime()));
        if (productScheduleService.setEquipmentProductSchedule(productScheduleVO) == null){
            updateProductOrderMessage.setCode(202);
            updateProductOrderMessage.setMessage("工单配置生产设备失败！");
        }else {
            updateProductOrderMessage.setCode(200);
            updateProductOrderMessage.setMessage("工单配置生产设备成功！");
            updateProductOrderMessage.setData(productScheduleService.selectById((int) productScheduleVO.getId()));
        }
        return updateProductOrderMessage;
    }

    // 根据id获取工单信息 ok
    @ApiOperation("根据id获取工单信息")
    @ApiImplicitParam(name="id",value="id",dataType="Integer")
    @GetMapping("get/{id}")
    public Message getProductSchedule(@PathVariable Integer id){
        Message getProductPlanMessage = new Message();
        if (productScheduleService.selectById(id) != null){
            getProductPlanMessage.setCode(200);
            getProductPlanMessage.setMessage("获取工单信息成功！");
            getProductPlanMessage.setData(productScheduleService.selectById(id));
        }else {
            getProductPlanMessage.setCode(202);
            getProductPlanMessage.setMessage("获取工单信息失败！");
        }
        return getProductPlanMessage;
    }

    // 获取全部工单信息
    @ApiOperation("根据id获取工单信息")
    @ApiImplicitParam(name="tProductSchedule",value="工单实体类",dataType="TProductSchedule")
    @PostMapping("getAll")
    public Message listProductSchedule(@RequestBody TProductSchedule tProductSchedule){
        Message listProductScheduleMessage = new Message();
        if (productScheduleService.allProductSchedule(tProductSchedule) != null){
            listProductScheduleMessage.setCode(200);
            listProductScheduleMessage.setMessage("获取工单信息成功！");
            listProductScheduleMessage.setData(productScheduleService.allProductSchedule(tProductSchedule));
        }else {
            listProductScheduleMessage.setCode(202);
            listProductScheduleMessage.setMessage("获取工单信息失败！");
        }
        return listProductScheduleMessage;
    }

    // 获取全部工单信息
    @ApiOperation("分页 获取全部工单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="currPage",value="当前页",dataType="Integer"),
            @ApiImplicitParam(name="tProductSchedule",value="工单实体类",dataType="TProductSchedule")
    })
    @PostMapping("listPage/{currPage}")
    public Message listPageProductSchedule(@PathVariable Integer currPage, @RequestBody TProductSchedule tProductSchedule){
        Message listPageProductScheduleMessage = new Message();
        if (productScheduleService.listProductSchedules(currPage,tProductSchedule) != null){
            listPageProductScheduleMessage.setCode(200);
            listPageProductScheduleMessage.setMessage("分页获取工单信息成功！");
            listPageProductScheduleMessage.setData(productScheduleService.listProductSchedules(currPage,tProductSchedule));
        }else {
            listPageProductScheduleMessage.setCode(202);
            listPageProductScheduleMessage.setMessage("分页获取工单信息失败！");
        }
        return listPageProductScheduleMessage;
    }

    // 启动工单
    @ApiOperation("启动工单")
    @ApiImplicitParam(name="id",value="id",dataType="Integer")
    @PostMapping("start/{id}")
    public Message startProductSchedule(@PathVariable Integer id){
        TProductSchedule tProductSchedule = productScheduleService.selectById(id);
        tProductSchedule.setScheduleStatus(20);
        productScheduleService.startProductSchedule(tProductSchedule);
        Message startProductScheduleMessage = new Message();
        startProductScheduleMessage.setCode(200);
        startProductScheduleMessage.setMessage("启动工单成功！");
        startProductScheduleMessage.setData(tProductSchedule);
        return startProductScheduleMessage;
    }
}
