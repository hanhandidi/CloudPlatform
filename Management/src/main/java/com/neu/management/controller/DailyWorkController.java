package com.neu.management.controller;

import com.github.pagehelper.PageInfo;
import com.neu.management.model.Message;
import com.neu.management.model.TDailyWork;
import com.neu.management.service.DailyWorkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
