package com.neu.authority.service;

import com.neu.authority.entity.TPermit;

import java.util.List;

public interface PermitService {
    //获取所有菜单
    List<TPermit> getAllPermits();

    //获取所有菜单列表
    List<TPermit> getAllPermitList();
}
