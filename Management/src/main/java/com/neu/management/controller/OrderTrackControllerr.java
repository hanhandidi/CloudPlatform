package com.neu.management.controller;

import com.github.pagehelper.PageInfo;
import com.neu.management.model.Message;
import com.neu.management.model.TOrderTrack;
import com.neu.management.modelVO.DailyWorkVO;
import com.neu.management.modelVO.OrderTrackVO;
import com.neu.management.service.OrderTrackService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/orderTrack")
public class OrderTrackControllerr {

    private final OrderTrackService orderTrackService;

    @Autowired
    public OrderTrackControllerr(OrderTrackService orderTrackService) {
        this.orderTrackService = orderTrackService;
    }

    // 添加生产跟踪信息 ok
    @ApiOperation("添加生产跟踪信息")
    @ApiImplicitParam(name="tOrderTrack",value="生产跟踪实体类",dataType="TOrderTrack")
    @PostMapping("add")
    public Message addOrderTrack(@RequestBody TOrderTrack tOrderTrack) {
        Message addOrderTrackMessage = new Message();
        tOrderTrack.setUpdateUserid(tOrderTrack.getCreateUserid());
        tOrderTrack.setCreateTime(new Timestamp(new Date().getTime()));
        tOrderTrack.setUpdateTime(new Timestamp(new Date().getTime()));
        TOrderTrack tOrderTrack1 = orderTrackService.addOrderTrack(tOrderTrack);
        if (tOrderTrack1 == null){
            addOrderTrackMessage.setCode(202);
            addOrderTrackMessage.setMessage("创建生产跟踪信息失败，已启动的生产计划才能创建工单！");
        }else {
            orderTrackService.selectById((int) tOrderTrack1.getId());
            addOrderTrackMessage.setCode(200);
            addOrderTrackMessage.setMessage("创建生产跟踪信息成功！");
            addOrderTrackMessage.setData(tOrderTrack1);
        }
        return addOrderTrackMessage;
    }

    // 根据id删除生产跟踪信息
    @ApiOperation("根据id删除生产跟踪信息")
    @ApiImplicitParam(name="id",value="id",dataType="Integer")
    @DeleteMapping("delete/{id}")
    public Message deleteOrderTrack(@PathVariable Integer id){
        Message deleteOrderTrackMessage = new Message();
        if(orderTrackService.deleteById(id) == 1){
            deleteOrderTrackMessage.setCode(200);
            deleteOrderTrackMessage.setMessage("删除生产跟踪信息成功！");
        }else {
            deleteOrderTrackMessage.setCode(202);
            deleteOrderTrackMessage.setMessage("删除生产跟踪信息失败，未开始调度才可以删除！");
        }
        return deleteOrderTrackMessage;
    }

    // 更新生产跟踪信息 ok
    @ApiOperation("更新生产跟踪信息")
    @ApiImplicitParam(name="orderTrackVO",value="生产跟踪信息接收类",dataType="OrderTrackVO")
    @PutMapping("update")
    public Message updateOrderTrack(@RequestBody OrderTrackVO orderTrackVO){
        Message updateOrderTrackMessage = new Message();
        orderTrackVO.setUpdateTime(new Timestamp(new Date().getTime()));
        if (orderTrackService.updateOrderTrack(orderTrackVO) == null){
            updateOrderTrackMessage.setCode(202);
            updateOrderTrackMessage.setMessage("更新生产跟踪信息失败！");
        }else {
            updateOrderTrackMessage.setCode(200);
            updateOrderTrackMessage.setMessage("更新生产跟踪信息成功！");
            updateOrderTrackMessage.setData(orderTrackService.selectById((int) orderTrackVO.getId()));
        }
        return updateOrderTrackMessage;
    }

    // 根据id获取生产跟踪信息 ok
    @ApiOperation("根据id获取生产跟踪信息")
    @ApiImplicitParam(name="id",value="id",dataType="Integer")
    @GetMapping("get/{id}")
    public Message getOrderTrack(@PathVariable Integer id){
        Message getOrderTrackMessage = new Message();
        if ( orderTrackService.selectById(id) != null){
            getOrderTrackMessage.setCode(200);
            getOrderTrackMessage.setMessage("获取生产跟踪信息成功！");
            getOrderTrackMessage.setData(orderTrackService.selectById(id));
        }else {
            getOrderTrackMessage.setCode(202);
            getOrderTrackMessage.setMessage("获取生产跟踪信息失败！");
        }
        return getOrderTrackMessage;
    }

    // 获取全部生产跟踪信息 ok
    @ApiOperation("获取全部生产跟踪信息")
    @ApiImplicitParam(name="tOrderTrack",value="生产跟踪实体类",dataType="TOrderTrack")
    @PostMapping("getAll")
    public Message listOrderTrack(@RequestBody TOrderTrack tOrderTrack){
        Message listOrderTrackMessage = new Message();
        List<TOrderTrack> tOrderTracks = orderTrackService.allOrderTracks(tOrderTrack);
        if ( tOrderTracks != null){
            listOrderTrackMessage.setCode(200);
            listOrderTrackMessage.setMessage("获取生产跟踪信息成功！");
            listOrderTrackMessage.setData(tOrderTracks);
        }else {
            listOrderTrackMessage.setCode(202);
            listOrderTrackMessage.setMessage("获取生产跟踪信息失败！");
        }
        return listOrderTrackMessage;
    }

    // 获取全部生产跟踪信息
    @ApiOperation("获取全部生产跟踪信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="currPage",value="当前页",dataType="Integer"),
            @ApiImplicitParam(name="tOrderTrack",value="生产跟踪实体类",dataType="TOrderTrack")
    })
    @PostMapping("listPage/{currPage}")
    public Message listPageOrderTrack(@PathVariable Integer currPage, @RequestBody TOrderTrack tOrderTrack){
        Message listPageProductScheduleMessage = new Message();
        PageInfo<TOrderTrack> pageInfo = orderTrackService.listOrderTracks(currPage,tOrderTrack);
        if ( pageInfo != null){
            listPageProductScheduleMessage.setCode(200);
            listPageProductScheduleMessage.setMessage("分页获取生产跟踪信息成功！");
            listPageProductScheduleMessage.setData(pageInfo);
        }else {
            listPageProductScheduleMessage.setCode(202);
            listPageProductScheduleMessage.setMessage("分页获取生产跟踪信息失败！");
        }
        return listPageProductScheduleMessage;
    }

    // 报工
    @ApiOperation("报工")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="id",dataType="Integer"),
            @ApiImplicitParam(name="dailyWorkVO",value="报工数据接收类",dataType="DailyWorkVO")
    })
    @PostMapping("jobBook/{id}")
    public Message jobBook(@PathVariable Integer id, @RequestBody DailyWorkVO dailyWorkVO){
        Message jobBookMessage = new Message();
        if(orderTrackService.jobBook(id,dailyWorkVO) == 1){
            jobBookMessage.setCode(200);
            jobBookMessage.setMessage("报工操作成功！");
            jobBookMessage.setData(orderTrackService.selectById(id));
        }else {
            jobBookMessage.setCode(202);
            jobBookMessage.setMessage("报工操作失败！");
        }
        return jobBookMessage;
    }

    // 报工完成
    @ApiOperation("报工完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name="orderTrackId",value="生产跟踪ID",dataType="Integer"),
            @ApiImplicitParam(name="userId",value="用户ID",dataType="Integer")
    })
    @PostMapping("finishJobBook/{orderTrackId}")
    public Message finishJobBook(@PathVariable Integer orderTrackId,@RequestBody Integer userId){
        Message finishJobBookMessage = new Message();
        orderTrackService.finishJobBook(orderTrackId,userId);
        finishJobBookMessage.setCode(200);
        finishJobBookMessage.setMessage("报工完成！");
        return finishJobBookMessage;
    }
}
