package com.neu.management.service;

import com.github.pagehelper.PageInfo;
import com.neu.management.model.TOrderTrack;
import com.neu.management.modelVO.DailyWorkVO;
import com.neu.management.modelVO.OrderTrackVO;

import java.util.List;

public interface OrderTrackService {
    // 添加生产跟踪信息
    TOrderTrack addOrderTrack(TOrderTrack tOrderTrack);

    int deleteById(Integer id,Integer userId);

    TOrderTrack updateOrderTrack(OrderTrackVO orderTrackVO);

    // 报工
    int jobBook(Integer id, DailyWorkVO dailyWorkVO);

    // 报工完成
    void finishJobBook(Integer orderTrackId,Integer userId);

    // 根据ID获取
    TOrderTrack selectById(Integer id);

    // 简易列表、查询 所有的该工厂所有的订单跟踪信息
    PageInfo<TOrderTrack> listOrderTracks(Integer currPage, TOrderTrack tOrderTrack);

    List<TOrderTrack> allOrderTracks(TOrderTrack tOrderTrack);
}
