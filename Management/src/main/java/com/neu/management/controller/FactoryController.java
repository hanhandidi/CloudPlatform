package com.neu.management.controller;

import com.neu.management.model.Message;
import com.neu.management.model.TFactory;
import com.neu.management.service.FactoryService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping(value = "/factory")
public class FactoryController {

    private final FactoryService factoryService;

    @Autowired
    public FactoryController(FactoryService factoryService) {
        this.factoryService = factoryService;
    }

    // 添加工厂信息 ok
    @PostMapping("add")
    @ApiOperation("添加工厂信息")
    @ApiImplicitParam(name="tFactory",value="工厂实体类",dataType="TFactory")
    public Message addFactory(@RequestBody TFactory tFactory) {
        Message addFactoryMessage = new Message();
        tFactory.setCreateTime(new Timestamp(new Date().getTime()));
        tFactory.setUpdateTime(new Timestamp(new Date().getTime()));
        TFactory tFactory1 = factoryService.addFactory(tFactory);
        if (tFactory1 == null){
            addFactoryMessage.setCode(202);
            addFactoryMessage.setMessage("添加工厂信息失败，请重试！");
        }else {
            // 借助get方法添加缓存
            // System.out.println(addFactoryMessage.getId());
            factoryService.getFactory((int) tFactory1.getId());
            addFactoryMessage.setCode(200);
            addFactoryMessage.setMessage("添加工厂信息成功！");
            addFactoryMessage.setData(tFactory1);
        }
        return addFactoryMessage;
    }

    // 根据ID删除工厂信息 ok
    @ApiOperation("根据ID删除工厂信息")
    @ApiImplicitParam(name="id",value="id",dataType="Integer")
    @DeleteMapping("delete/{id}")
    public Message deleteFactory(@PathVariable  Integer id){
        factoryService.deleteFactory(id);
        Message deleteFactoryMessage = new Message();
        deleteFactoryMessage.setCode(200);
        deleteFactoryMessage.setMessage("删除工厂信息成功！");
        return deleteFactoryMessage;
    }

    // 根据ID获取工厂信息 ok
    @GetMapping("get/{id}")
    @ApiOperation("根据ID获取工厂信息")
    @ApiImplicitParam(name="id",value="id",dataType="Integer")
    public Message getFactory(@PathVariable Integer id){
        Message getFactoryMessage = new Message();
        if (factoryService.getFactory(id) != null){
            getFactoryMessage.setCode(200);
            getFactoryMessage.setMessage("获取工厂信息成功！");
            getFactoryMessage.setData(factoryService.getFactory(id));
        }else {
            getFactoryMessage.setCode(202);
            getFactoryMessage.setMessage("获取工厂信息失败！");
        }
        return getFactoryMessage;
    }

    // 更新工厂信息 ok
    @PutMapping("update")
    @ApiOperation("更新工厂信息")
    @ApiImplicitParam(name="tFactory",value="工厂实体类",dataType="TFactory")
    public Message updateFactory(@RequestBody TFactory tFactory){
        Message updateFactoryMessage = new Message();
        tFactory.setUpdateTime(new Timestamp(new Date().getTime()));
        if (factoryService.updateFactory(tFactory) == null){
            // 序列号重复
            updateFactoryMessage.setCode(202);
            updateFactoryMessage.setMessage("更新工厂信息失败，请重试！");
        }else {
            updateFactoryMessage.setCode(200);
            updateFactoryMessage.setMessage("更新工厂信息成功！");
            updateFactoryMessage.setData(tFactory);
        }
        return updateFactoryMessage;
    }

    // 更新工厂状态信息 ok
    @PutMapping("updateState")
    @ApiOperation("更新工厂状态信息")
    @ApiImplicitParam(name="tFactory",value="工厂实体类",dataType="TFactory")
    public Message updateFactoryState(@RequestBody TFactory tFactory){
        Message updateFactoryMessage = new Message();
        tFactory.setUpdateTime(new Timestamp(new Date().getTime()));
        TFactory tFactory1 = factoryService.updateFactoryState(tFactory);
        if ( tFactory1 == null){
            updateFactoryMessage.setCode(202);
            updateFactoryMessage.setMessage("更新工厂状态失败");
        }else {
            updateFactoryMessage.setCode(200);
            updateFactoryMessage.setMessage("更新工厂状态成功！");
            updateFactoryMessage.setData(tFactory1);
        }
        return updateFactoryMessage;
    }

    // 获取所有工厂信息 ok
    @GetMapping("getAll")
    @ApiOperation("获取所有工厂信息")
    public Message getAllFactory(){
        Message getAllFactoryMessage = new Message();
        if (factoryService.getAllFactory() != null){
            getAllFactoryMessage.setCode(200);
            getAllFactoryMessage.setMessage("获取所有工厂信息成功！");
            getAllFactoryMessage.setData(factoryService.getAllFactory());
        }else {
            getAllFactoryMessage.setCode(202);
            getAllFactoryMessage.setMessage("获取所有工厂信息失败！");
        }
        return getAllFactoryMessage;
    }

    // 分页搜索 ok
    @ApiOperation("分页 获取所有工厂信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="currPage",value="当前页",dataType="Integer"),
            @ApiImplicitParam(name="tFactory",value="工厂实体类",dataType="TFactory")
    })
    @PostMapping("list/{currPage}")
    public Message listFactory(@PathVariable Integer currPage, @RequestBody TFactory tFactory){
        Message listFactoryMessage = new Message();
        if (factoryService.listFactory(currPage,tFactory) != null){
            listFactoryMessage.setCode(200);
            listFactoryMessage.setMessage("分页查询工厂信息成功！");
            listFactoryMessage.setData(factoryService.listFactory(currPage,tFactory));
        }else {
            listFactoryMessage.setCode(202);
            listFactoryMessage.setMessage("分页查询工厂信息失败！");
        }
        return listFactoryMessage;
    }
}
