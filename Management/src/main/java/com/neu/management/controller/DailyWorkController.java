package com.neu.management.controller;

import com.github.pagehelper.PageInfo;
import com.neu.management.model.Message;
import com.neu.management.model.TDailyWork;
import com.neu.management.service.DailyWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/dailyWork")
public class DailyWorkController {

    private final DailyWorkService dailyWorkService;

    @Autowired
    public DailyWorkController(DailyWorkService dailyWorkService) {
        this.dailyWorkService = dailyWorkService;
    }

    // 根据id删除报工信息
    @RequestMapping("delete/{id}")
    public Message deleteDailyWork(@PathVariable Integer id){
        Message deleteDailyWorkMessage = new Message();
        dailyWorkService.deleteById(id);
        deleteDailyWorkMessage.setCode(200);
        deleteDailyWorkMessage.setMessage("删除生产跟踪信息成功！");
        return deleteDailyWorkMessage;
    }

    // 获取报工信息并分页
    @RequestMapping("listPage/{currPage}")
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
    @RequestMapping("list")
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
