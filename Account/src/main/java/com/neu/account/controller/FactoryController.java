package com.neu.account.controller;

import com.neu.account.entity.Message;
import com.neu.account.entity.TFactory;
import com.neu.account.entity.TUser;
import com.neu.account.service.FactoryService;
import com.neu.account.service.FactoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/factory")
public class FactoryController {

    @Autowired
    FactoryServiceImpl factoryService;

    @RequestMapping(value = "create")
    @ResponseBody
    public Message createFactory(@RequestBody TFactory factory) {
        System.out.println(factory.getFactoryName());
        return factoryService.createFactory(factory);
    }
}
