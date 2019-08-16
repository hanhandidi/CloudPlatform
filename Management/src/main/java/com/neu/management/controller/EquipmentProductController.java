package com.neu.management.controller;

import com.neu.management.model.Message;
import com.neu.management.model.TEquipmentProduct;
import com.neu.management.service.EquipmentProductService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/equipmentProduct")
public class EquipmentProductController {

    private final EquipmentProductService equipmentProductService;

    @Autowired
    public EquipmentProductController(EquipmentProductService equipmentProductService) {
        this.equipmentProductService = equipmentProductService;
    }

    // 添加产能信息 ok 对同一产品只能添加一条产能信息
    @ApiOperation("添加产能信息 对同一产品只能添加一条产能信息")
    @ApiImplicitParam(name="tEquipmentProduct",value="产能实体类",dataType="TEquipmentProduct")
    @PostMapping("add")
    public Message addEquipmentProduct(@RequestBody TEquipmentProduct tEquipmentProduct) {
        Message addEquipmentProductMessage = new Message();
        TEquipmentProduct tEquipmentProduct1 = equipmentProductService.addEquipmentProduct(tEquipmentProduct);
        if (tEquipmentProduct1 == null){
            // 序列号重复
            addEquipmentProductMessage.setCode(202);
            addEquipmentProductMessage.setMessage("添加产能信息失败，一个设备所关联的产品产能唯一，请重试！");
        }else {
            // 借助get方法添加缓存
            equipmentProductService.getEquipmentProduct((int) tEquipmentProduct1.getId());
            addEquipmentProductMessage.setCode(200);
            addEquipmentProductMessage.setMessage("添加产能信息成功！");
            addEquipmentProductMessage.setData(tEquipmentProduct1);
        }
        return addEquipmentProductMessage;
    }

    // 根据ID删除产能信息 ok 已关联启动工单的产能信息不可删除
    @ApiOperation("根据ID删除产能信息 已关联启动工单的产能信息不可删除")
    @ApiImplicitParam(name="id",value="id",dataType="Integer")
    @DeleteMapping("delete/{id}")
    public Message deleteEquipmentProduct(@PathVariable Integer id){
        Message deleteEquipmentProductMessage = new Message();
        if ( equipmentProductService.deleteEquipmentProduct(id) == 1){
            deleteEquipmentProductMessage.setCode(200);
            deleteEquipmentProductMessage.setMessage("删除产能信息成功！");
        }else {
            deleteEquipmentProductMessage.setCode(202);
            deleteEquipmentProductMessage.setMessage("删除产能信息失败，已关联启动工单的产能信息不可删除！");
        }
        return deleteEquipmentProductMessage;
    }

    // 根据ID获取产能信息 ok
    @ApiOperation("根据ID获取产能信息")
    @ApiImplicitParam(name="id",value="id",dataType="Integer")
    @GetMapping("get/{id}")
    public Message getEquipmentProduct(@PathVariable Integer id){
        Message getEquipmentProductMessage = new Message();
        if (equipmentProductService.getEquipmentProduct(id) != null){
            getEquipmentProductMessage.setCode(200);
            getEquipmentProductMessage.setMessage("获取产能信息成功！");
            getEquipmentProductMessage.setData(equipmentProductService.getEquipmentProduct(id));
        }else {
            getEquipmentProductMessage.setCode(202);
            getEquipmentProductMessage.setMessage("获取产能信息失败！");
        }
        return getEquipmentProductMessage;
    }

    // 更新产能信息 ok
    @ApiOperation("更新产能信息")
    @ApiImplicitParam(name="tEquipmentProduct",value="id",dataType="TEquipmentProduct")
    @PutMapping("update")
    public Message updateEquipmentProduct(@RequestBody TEquipmentProduct tEquipmentProduct){
        Message updateEquipmentProductMessage = new Message();
        if (equipmentProductService.updateEquipmentProduct(tEquipmentProduct) == null){
            // 序列号重复
            updateEquipmentProductMessage.setCode(202);
            updateEquipmentProductMessage.setMessage("更新产能信息失败，请重试！");
        }else {
            updateEquipmentProductMessage.setCode(200);
            updateEquipmentProductMessage.setMessage("更新产能信息成功！");
            updateEquipmentProductMessage.setData(tEquipmentProduct);
        }
        return updateEquipmentProductMessage;
    }

    // 根据设备ID获取该设备所关联的所有产品的产能信息 ok
    @ApiOperation("根据设备ID获取该设备所关联的所有产品的产能信息")
    @ApiImplicitParam(name="id",value="id",dataType="Integer")
    @PostMapping("getAllByEquipmentId/{id}")
    public Message getAllEquipmentProductByEquipmentId(@PathVariable Integer id){
        Message getAllEquipmentProductMessage = new Message();
        if (equipmentProductService.listByEquipmentId(id) != null){
            getAllEquipmentProductMessage.setCode(200);
            getAllEquipmentProductMessage.setMessage("获取设备关联产品产能信息成功！");
            getAllEquipmentProductMessage.setData(equipmentProductService.listByEquipmentId(id));
        }else {
            getAllEquipmentProductMessage.setCode(202);
            getAllEquipmentProductMessage.setMessage("获取设备关联产品产能信息失败！");
        }
        return getAllEquipmentProductMessage;
    }

    // 根据工厂ID获取该工厂所有设备所关联的所有产品的产能信息 ok
    @ApiOperation("根据工厂ID获取该工厂所有设备所关联的所有产品的产能信息")
    @ApiImplicitParam(name="id",value="id",dataType="Integer")
    @PostMapping("getAllByFactoryId/{id}")
    public Message getAllEquipmentProductByFactoryId(@PathVariable Integer id){
        Message getAllEquipmentProductMessage = new Message();
        if (equipmentProductService.listByFactoryId(id) != null){
            getAllEquipmentProductMessage.setCode(200);
            getAllEquipmentProductMessage.setMessage("获取工厂所有设备产能信息成功！");
            getAllEquipmentProductMessage.setData(equipmentProductService.listByFactoryId(id));
        }else {
            getAllEquipmentProductMessage.setCode(202);
            getAllEquipmentProductMessage.setMessage("获取工厂所有设备产能信息失败！");
        }
        return getAllEquipmentProductMessage;
    }

    // 根据工厂ID获取该工厂所有设备所关联的所有产品的产能信息 ok
    @ApiOperation("根据产品ID获取该工厂所有设备所关联的所有产品的产能信息")
    @ApiImplicitParam(name="id",value="id",dataType="Integer")
    @PostMapping("getAllByProductId/{id}")
    public Message getAllEquipmentProductProductId(@PathVariable Integer id){
        Message getAllEquipmentProductMessage = new Message();
        if (equipmentProductService.listByFactoryId(id) != null){
            getAllEquipmentProductMessage.setCode(200);
            getAllEquipmentProductMessage.setMessage("获取产品所能设备产能信息成功！");
            getAllEquipmentProductMessage.setData(equipmentProductService.listByProductId(id));
        }else {
            getAllEquipmentProductMessage.setCode(202);
            getAllEquipmentProductMessage.setMessage("获取产品所能设备产能信息失败！");
        }
        return getAllEquipmentProductMessage;
    }
}
