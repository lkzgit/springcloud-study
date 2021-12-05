package com.oauth.consumer.controller;

import com.oauth.consumer.feign.ProFeignInface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SumerController {


    @Autowired
    ProFeignInface proFeignInface;

    @GetMapping("testCon")
    public String testCon(){
        return "consumer--服务";
    }

    @GetMapping("feignPro")
    public String getFeignPro(){
        return proFeignInface.getTestPro();
    }

}
