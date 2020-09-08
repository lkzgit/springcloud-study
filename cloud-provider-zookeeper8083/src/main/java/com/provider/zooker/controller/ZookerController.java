package com.provider.zooker.controller;


import com.api.common.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping
public class ZookerController {

    @GetMapping("test")
    public CommonResult get(){

        return new CommonResult(0000,"zooker支付模块8083"+UUID.randomUUID().toString());
    }
}
