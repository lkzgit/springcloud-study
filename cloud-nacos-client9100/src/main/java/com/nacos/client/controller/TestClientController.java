package com.nacos.client.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //支持Nacos的动态刷新功能
public class TestClientController {


    @Value("${config.info}")
    private String configInfo;


    @GetMapping("test1")
    public String test1(){

        return "TestClientController:"+configInfo;
    }
}
