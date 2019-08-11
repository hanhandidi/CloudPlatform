package com.neu.management.controller;

import com.neu.management.model.Message;
import com.neu.management.model.TEquipmentProduct;
import com.neu.management.service.EquipmentProductService;
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
    @ResponseBody
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

    // 根据ID删除产能信息 ok
    @RequestMapping("delete/{id}")
    public Message deleteEquipmentProduct(@PathVariable Integer id){
        equipmentProductService.deleteEquipmentProduct(id);
        Message deleteEquipmentProductMessage = new Message();
        deleteEquipmentProductMessage.setCode(200);
        deleteEquipmentProductMessage.setMessage("删除产能信息成功！");
        return deleteEquipmentProductMessage;
    }

    // 根据ID添加产能信息 ok
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
    @RequestMapping("update")
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
    @RequestMapping("getAllByEquipmentId")
    public Message getAllEquipmentProductByEquipmentId(@RequestBody Integer id){
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
    @RequestMapping("getAllByFactoryId")
    public Message getAllEquipmentProductByFactoryId(@RequestBody Integer id){
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
}