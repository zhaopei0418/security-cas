package com.github.insurance.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController extends BaseController {

    private static Log logger = LogFactory.getLog(RootController.class);

    @RequestMapping("/")
    public String index() {
        return JSON.toJSONString(getCurrUser());
    }
}
