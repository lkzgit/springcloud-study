package com.nacos.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lkz
 * @date 2021/10/11 20:11
 * @description
 */
@RestController
@RefreshScope //自动刷新
public class DemoConfigController {

    @Autowired
    MyProperties myProperties;

    @Value("${user.name}")
    private String name;
    @Value("${user.age}")
     private String age;

    @GetMapping("demo")
    public String demo(){
        return "姓名:"+name+"---年龄:"+age;
    }

    @GetMapping("demo2")
    public String demo2(){
        return myProperties.getName()+"--姓名和年龄--"+myProperties.getAge();
    }

}
