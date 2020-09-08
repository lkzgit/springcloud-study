package com.provider.consul.controller;

import com.api.common.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping
@RestController
public class ProConsulController {

    @GetMapping("test")
    public CommonResult get(){

        return new CommonResult(0000,"consul支付模块8084"+ UUID.randomUUID().toString());
    }
}
