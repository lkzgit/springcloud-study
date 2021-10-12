package com.nacos.demo.controller;

import com.nacos.demo.feign.SentinelFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author lkz
 * @date 2021/10/12 18:38
 * @description 测试限流
 */
@RestController
public class SentinelController {


    @Autowired
    SentinelFeign sentinelFeign;


    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping("testSentinelFeign")
    public String testSentinelFeign(){
        return sentinelFeign.testSentinel();
    }



    @GetMapping("/app1")
    public String app1() {
        try {
            Thread.sleep(2005);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(111);
        return restTemplate.getForObject(serverURL+"/testSentinel", String.class);
    }

    @GetMapping("/app2")
    public String app2() {
        return restTemplate.getForObject(serverURL+"/testSentinel", String.class);
    }

}
