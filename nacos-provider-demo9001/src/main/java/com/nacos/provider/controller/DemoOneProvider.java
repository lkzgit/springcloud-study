package com.nacos.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lkz
 * @date 2021/10/11 14:26
 * @description
 */
@RestController
@Slf4j
public class DemoOneProvider {

    @Value("${server.port}")
    private String port;
//    @Value("${spring.cloud.nacos.discovery.cluster-name}")
//    private String clustName;
    /**
     * @Author lkz
     * @Description //测试负载请求 对应consumer 中 restTemplate模式 不适用feign
     **/
    @GetMapping("/pro/demo")
    public String demo(){
        return "nihao provider"+port+"-----";
    }


    @GetMapping("demoFeign")
    public String demoFeign(){
        return "nihao provider demoFeign"+port;
    }

    @GetMapping("/")
    public ResponseEntity index() {
        log.info("provider /");
        return new ResponseEntity("index", HttpStatus.OK);
    }
    //测试负载
    @GetMapping("/testRibbon")
    public String testRibbon() {
        log.info("provider /testRibbon");
        return "testRibbon--测试负载--"+port;
    }

    @GetMapping("/test")
    public ResponseEntity test() {
        log.info("provider /test");
        return new ResponseEntity("test", HttpStatus.OK);
    }

    @GetMapping("/sleep")
    public String sleep() {
        log.info("provider /sleep");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @GetMapping("/echo/{string}")
    public String echo(@PathVariable String string) {
        log.info("provider /echo/{string}");
        return "hello Nacos Discovery " + string;
    }

    @GetMapping("/divide")
    public String divide(@RequestParam Integer a, @RequestParam Integer b) {
        log.info("provider /divide");
        return String.valueOf(a / b);
    }

    @GetMapping("/notFound")
    public String notFound() {
        System.out.println("provider 1 .........");
        return "notFound";
    }

}
