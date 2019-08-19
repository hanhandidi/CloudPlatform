package com.neu.authority.controller;

import com.neu.authority.entity.Message;
import com.neu.authority.entity.TPermit;
import com.neu.authority.service.PermitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/permit")
public class PermitController {

    @Autowired
    PermitServiceImpl menuService;

    // 所有菜单 含结构
    @RequestMapping(value = "/menu/list")
    @ResponseBody
    Message getAllPermits() {
        return new Message(200, "获取结构权限成功", menuService.getAllPermits());
    }

    // 所有菜单 单列表
    @RequestMapping(value = "list")
    @ResponseBody
    Message getAllPermitList() {
        return new Message(200, "获取列表权限成功", menuService.getAllPermitList());
    }
}
