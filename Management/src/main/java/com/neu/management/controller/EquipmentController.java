package com.neu.management.controller;

import com.neu.management.model.Message;
import com.neu.management.model.TEquipment;
import com.neu.management.modelVO.EquipmentAddVO;
import com.neu.management.modelVO.EquipmentSelectVO;
import com.neu.management.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    // 添加设备信息 ok
    @ResponseBody
    @PostMapping("add")
    public Message addEquipment(@RequestBody TEquipment tEquipment) {
        Message addEquipmentMessage = new Message();
        tEquipment.setCreateTime(new Timestamp(new Date().getTime()));
        tEquipment.setUpdateTime(new Timestamp(new Date().getTime()));
        TEquipment tEquipment1 = equipmentService.addEquipment(tEquipment);
        if (tEquipment1 == null){
            // 序列号重复
            addEquipmentMessage.setCode(202);
            addEquipmentMessage.setMessage("添加设备失败，设备序列号已存在，请重试！");
        }else {
            // 借助get方法添加缓存
            System.out.println(tEquipment1.getId());
            equipmentService.getEquipment((int) tEquipment1.getId());
            addEquipmentMessage.setCode(200);
            addEquipmentMessage.setMessage("添加设备成功！");
            addEquipmentMessage.setData(tEquipment1);
        }
        return addEquipmentMessage;
    }

    // 添加设备信息 带多个关联产品一起添加 ok
    @ResponseBody
    @PostMapping("addVO")
    public Message addEquipment(@RequestBody EquipmentAddVO equipmentAddVO) {
        Message addEquipmentMessage = new Message();
        Integer result = equipmentService.addEquipment(equipmentAddVO);
        if (result == -1){
            // 序列号重复
            addEquipmentMessage.setCode(202);
            addEquipmentMessage.setMessage("添加设备失败，设备序列号已存在，请重试！");
        } else if(result == 0){
            addEquipmentMessage.setCode(202);
            addEquipmentMessage.setMessage("添加产能信息失败，一个设备所关联的产品产能唯一，请重试！");
        } else {
            // 成功返回id
            TEquipment tEquipment = equipmentService.getEquipment(result);
            addEquipmentMessage.setCode(200);
            addEquipmentMessage.setMessage("添加设备成功！");
            addEquipmentMessage.setData(tEquipment);
        }
        return addEquipmentMessage;
    }

    // 根据设备id删除设备信息 ok 要求已关联启动工单的设备不可删除
    @RequestMapping("delete/{id}")
    public Message deleteEquipment(@PathVariable  Integer id,@RequestBody Integer userId){
        Message deleteEquipmentMessage = new Message();
        if(equipmentService.deleteEquipment(id,userId) == 1){
            deleteEquipmentMessage.setCode(200);
            deleteEquipmentMessage.setMessage("删除设备成功！");
        }else {
            deleteEquipmentMessage.setCode(202);
            deleteEquipmentMessage.setMessage("删除设备失败，改设备正在生产产品！");
        }
        return deleteEquipmentMessage;
    }

    // 批量删除 ok
    @RequestMapping("deleteList")
    public Message deleteEquipmentList(@RequestBody List<Integer> ids){
        equipmentService.deleteEquipmentList(ids);
        Message deleteEquipmentListMessage = new Message();
        deleteEquipmentListMessage.setCode(200);
        deleteEquipmentListMessage.setMessage("批量删除设备成功！");
        return deleteEquipmentListMessage;
    }

    // 更新设备信息 ok
    @RequestMapping("update")
    public Message updateEquipment(@RequestBody TEquipment tEquipment){
        Message updateEquipmentMessage = new Message();
        tEquipment.setUpdateTime(new Timestamp(new Date().getTime()));
        if (equipmentService.updateEquipment(tEquipment) == null){
            // 序列号重复
            updateEquipmentMessage.setCode(202);
            updateEquipmentMessage.setMessage("更新设备失败，设备序列号已存在，请重试！");
        }else {
            updateEquipmentMessage.setCode(200);
            updateEquipmentMessage.setMessage("更新设备成功！");
            updateEquipmentMessage.setData(tEquipment);
        }
        return updateEquipmentMessage;
    }

    // 更新设备状态信息 ok
    @RequestMapping("updateState")
    public Message updateEquipmentState(@RequestBody TEquipment tEquipment){
        Message updateEquipmentMessage = new Message();
        tEquipment.setUpdateTime(new Timestamp(new Date().getTime()));
        TEquipment tEquipment1 = equipmentService.updateEquipmentState(tEquipment);
        if ( tEquipment1 == null){
            updateEquipmentMessage.setCode(202);
            updateEquipmentMessage.setMessage("更新设备状态失败！");
        }else {
            updateEquipmentMessage.setCode(200);
            updateEquipmentMessage.setMessage("更新设备状态成功！");
            updateEquipmentMessage.setData(tEquipment1);
        }
        return updateEquipmentMessage;
    }

    // 根据设备id获取设备信息 ok
    @GetMapping("get/{id}")
    public Message getEquipment(@PathVariable Integer id){
        Message getEquipmentMessage = new Message();
        if (equipmentService.getEquipment(id) != null){
            getEquipmentMessage.setCode(200);
            getEquipmentMessage.setMessage("获取设备成功！");
            getEquipmentMessage.setData(equipmentService.getEquipment(id));
        }else {
            getEquipmentMessage.setCode(202);
            getEquipmentMessage.setMessage("获取设备失败！");
        }
        return getEquipmentMessage;
    }

    // 获取 List 不分页
    @GetMapping("getAll")
    public Message getAllEquipment(@RequestBody TEquipment tEquipment){
        equipmentService.allEquipment(tEquipment);
        return null;
    }

    // 获取全部数据、分页、简易查找 ok
    @RequestMapping("list/{currPage}")
    public Message listEquipment(@PathVariable Integer currPage, @RequestBody TEquipment tEquipment){
        Message listEquipmentMessage = new Message();
        if (equipmentService.listEquipment(currPage,tEquipment) != null){
            listEquipmentMessage.setCode(200);
            listEquipmentMessage.setMessage("分页查询成功！");
            listEquipmentMessage.setData(equipmentService.listEquipment(currPage,tEquipment));
        }else {
            listEquipmentMessage.setCode(202);
            listEquipmentMessage.setMessage("分页查询失败！");
        }
        return listEquipmentMessage;
    }

    // 查询：根据产品名称、设备名称查询当前工厂所有相关设备内容列表
    @RequestMapping("select/{currPage}")
    public Message selectEquipment(@PathVariable Integer currPage, @RequestBody EquipmentSelectVO equipmentSelectVO){
        Message selectEquipmentMessage = new Message();
        if (equipmentService.selectEquipment(currPage,equipmentSelectVO) != null){
            selectEquipmentMessage.setCode(200);
            selectEquipmentMessage.setMessage("分页查询成功！");
            selectEquipmentMessage.setData(equipmentService.selectEquipment(currPage,equipmentSelectVO));
        }else {
            selectEquipmentMessage.setCode(202);
            selectEquipmentMessage.setMessage("分页查询失败！");
        }
        return selectEquipmentMessage;
    }

    // ES 检索 根据设备名称进行检索 ok
    @RequestMapping("search/{currPage}")
    public Message search(@PathVariable Integer currPage, @RequestBody String equipmentName){
        Message searchEquipmentMessage = new Message();
        if (equipmentService.search(currPage,equipmentName) != null){
            searchEquipmentMessage.setCode(200);
            searchEquipmentMessage.setMessage("文件检索成功！");
            searchEquipmentMessage.setData(equipmentService.search(currPage,equipmentName));
        }else {
            searchEquipmentMessage.setCode(202);
            searchEquipmentMessage.setMessage("文件检索成功！");
        }
        return searchEquipmentMessage;
    }

    // ES 检索 高亮 根据设备名称进行检索 ok
    @RequestMapping("searchHighlight/{currPage}")
    public Message searchHighlight(@PathVariable Integer currPage,@RequestBody String equipmentName){
        Message searchHighlightEquipmentMessage = new Message();
        if (equipmentService.searchHighlight(currPage,equipmentName) != null){
            searchHighlightEquipmentMessage.setCode(200);
            searchHighlightEquipmentMessage.setMessage("文件检索成功(高亮)！");
            searchHighlightEquipmentMessage.setData(equipmentService.searchHighlight(currPage,equipmentName));
        }else {
            searchHighlightEquipmentMessage.setCode(202);
            searchHighlightEquipmentMessage.setMessage("文件检索成功(高亮)！");
        }
        return searchHighlightEquipmentMessage;
    }
}
