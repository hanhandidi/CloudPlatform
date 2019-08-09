package com.neu.management.controller;
import com.neu.management.model.Message;
import com.neu.management.model.TEquipment;
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

    // 根据id删除设备信息 ok
    @RequestMapping("delete/{id}")
    public Message deleteEquipment(@PathVariable  Integer id){
        equipmentService.deleteEquipment(id);
        Message deleteEquipmentMessage = new Message();
        deleteEquipmentMessage.setCode(200);
        deleteEquipmentMessage.setMessage("删除设备成功！");
        return deleteEquipmentMessage;
    }

    // 批量删除 ok
    @RequestMapping("deleteList")
    public Message deleteEquipmentList(@RequestBody List<Integer> ids){
        System.out.println(ids.get(0));
        equipmentService.deleteEquipmentList(ids);
        Message deleteEquipmentListMessage = new Message();
        deleteEquipmentListMessage.setCode(200);
        deleteEquipmentListMessage.setMessage("批量删除设备成功！");
        return deleteEquipmentListMessage;
    }

    // 根据id获取设备信息 ok
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

    // 根据序列号获取设备信息 ok
    @RequestMapping("getBySeq")
    public Message getEquipmentBySeq(@RequestBody String equipmentSeq){
        Message getEquipmentMessage = new Message();
        if (equipmentService.getEquipmentBySeq(equipmentSeq) != null){
            TEquipment tEquipment = equipmentService.getEquipmentBySeq(equipmentSeq);
            getEquipmentMessage.setCode(200);
            getEquipmentMessage.setMessage("获取设备成功！");
            getEquipmentMessage.setData(tEquipment);
        }else {
            getEquipmentMessage.setCode(202);
            getEquipmentMessage.setMessage("获取设备失败！");
        }
        return getEquipmentMessage;
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
