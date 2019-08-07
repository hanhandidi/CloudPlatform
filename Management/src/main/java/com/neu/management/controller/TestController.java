package com.neu.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class TestController {

    @RequestMapping(value = "")
    @ResponseBody
    public String main() {
        return "This is CloudPlatform";
    }
}
