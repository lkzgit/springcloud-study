package com.consumer.consul.controller;


import com.api.common.CommonResult;
import com.api.common.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("order")
@Slf4j
public class ConsulController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public CommonResult get(){

        return new CommonResult(0000,"测试成功");
    }

    public static final String PAYMENT_URL = "http://cloud-consul-provider";




    @GetMapping("/payment")
    public CommonResult<Payment> getPayment()
    {
        return restTemplate.getForObject(PAYMENT_URL+"/test/",CommonResult.class);
    }

}
