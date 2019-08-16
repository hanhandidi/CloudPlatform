package com.neu.management.controller;

import com.github.pagehelper.PageInfo;
import com.neu.management.model.Message;
import com.neu.management.model.TDailyWork;
import com.neu.management.model.TEquipment;
import com.neu.management.service.DailyWorkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Api("报工信息管理")
@RestController
@RequestMapping(value = "/dailyWork")
public class DailyWorkController {

    private final DailyWorkService dailyWorkService;

    @Autowired
    public DailyWorkController(DailyWorkService dailyWorkService) {
        this.dailyWorkService = dailyWorkService;
    }

    // 添加报工信息
    @ApiOperation("添加报工信息")
    @ApiImplicitParam(name="tDailyWork",value="报工实体类",dataType="TDailyWork")
    @PostMapping("add")
    public Message addDailyWork(@RequestBody TDailyWork tDailyWork) {
        Message addDailyWorkMessage = new Message();
        tDailyWork.setCreateTime(new Timestamp(new Date().getTime()));
        tDailyWork.setUpdateTime(new Timestamp(new Date().getTime()));
        TDailyWork tDailyWork1 = dailyWorkService.addDailyWork(tDailyWork);
        if ( tDailyWork1 != null ){
            dailyWorkService.selectById((int) tDailyWork1.getId());
            addDailyWorkMessage.setCode(200);
            addDailyWorkMessage.setMessage("添加报工信息成功！");
            addDailyWorkMessage.setData(tDailyWork1);
        }else {
            addDailyWorkMessage.setCode(202);
            addDailyWorkMessage.setMessage("添加报工信息失败！");
        }
        return addDailyWorkMessage;
    }

    // 根据id删除报工信息
    @ApiOperation("根据id删除报工信息")
    @ApiImplicitParam(name="id",value="id",dataType="Integer")
    @DeleteMapping("delete/{id}")
    public Message deleteDailyWork(@PathVariable Integer id){
        Message deleteDailyWorkMessage = new Message();
        dailyWorkService.deleteById(id);
        deleteDailyWorkMessage.setCode(200);
        deleteDailyWorkMessage.setMessage("删除生产跟踪信息成功！");
        return deleteDailyWorkMessage;
    }

    // 修改报工信息
    @ApiOperation("修改报工信息")
    @ApiImplicitParam(name="tDailyWork",value="报工实体类",dataType="TDailyWork")
    @PutMapping("update")
    public Message updateDailyWork(@RequestBody TDailyWork tDailyWork) {
        Message updateWorkMessage = new Message();
        tDailyWork.setUpdateTime(new Timestamp(new Date().getTime()));
        TDailyWork tDailyWork1 = dailyWorkService.updateDailyWork(tDailyWork);
        if ( tDailyWork1 != null ){
            updateWorkMessage.setCode(200);
            updateWorkMessage.setMessage("修改报工信息成功！");
            updateWorkMessage.setData(tDailyWork1);
        }else {
            updateWorkMessage.setCode(202);
            updateWorkMessage.setMessage("修改报工信息失败！");
        }
        return updateWorkMessage;
    }

    // 获取报工信息并分页
    @ApiOperation("获取报工信息并分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name="currPage",value="当前页",dataType="Integer"),
            @ApiImplicitParam(name="tDailyWork",value="报工实体类",dataType="TDailyWork")
    })
    @PostMapping("listPage/{currPage}")
    public Message listDailyWork(@PathVariable Integer currPage, @RequestBody TDailyWork tDailyWork){
        Message listDailyWorkMessage = new Message();
        PageInfo<TDailyWork> pageInfo = dailyWorkService.listDailyWork(currPage,tDailyWork);
        if ( pageInfo != null){
            listDailyWorkMessage.setCode(200);
            listDailyWorkMessage.setMessage("分页获取报工信息成功！");
            listDailyWorkMessage.setData(pageInfo);
        }else {
            listDailyWorkMessage.setCode(202);
            listDailyWorkMessage.setMessage("分页获取报工信息失败！");
        }
        return listDailyWorkMessage;
    }

    // 获取报工信息 不分页
    @ApiOperation("获取报工信息 不分页")
    @ApiImplicitParam(name="tDailyWork",value="报工实体类",dataType="TDailyWork")
    @PostMapping("list")
    public Message listDailyWork(@RequestBody TDailyWork tDailyWork){
        Message listDailyWorkMessage = new Message();
        List<TDailyWork> tDailyWorks = dailyWorkService.allDailyWork(tDailyWork);
        if ( tDailyWorks != null){
            listDailyWorkMessage.setCode(200);
            listDailyWorkMessage.setMessage("获取报工信息成功！");
            listDailyWorkMessage.setData(tDailyWorks);
        }else {
            listDailyWorkMessage.setCode(202);
            listDailyWorkMessage.setMessage("获取报工信息失败！");
        }
        return listDailyWorkMessage;
    }
}
