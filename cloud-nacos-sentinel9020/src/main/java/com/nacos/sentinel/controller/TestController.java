package com.nacos.sentinel.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sun.deploy.security.BlockedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class TestController {


    @GetMapping("test")
    public String test1() {
        System.out.println(Thread.currentThread().getName()+"\t");
        return "hello Sentinel!";
    }
    @GetMapping("test2")
    public String test2(){
        return "hello Sentinel2!";
    }

    @GetMapping("test3")
    public String test3() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        log.info("hello Sentinel3");
        return "hello Sentinel3!";
    }

    /**
     * 测试热点key
     */
    @GetMapping("/testHostKey")
    @SentinelResource(value = "testHostKey",blockHandler = "deal_testHost")
    public String testHostKey(@RequestParam(value = "p1",required = false)String p1,
                              @RequestParam(value = "p1",required = false)String p2){
        //int i=10/0;
    return "testHostKey成功";

    }

    /**
     * 兜底方法 类似hix 参数索引配置是 接收的参数索引配置
     */
    public String deal_testHost(@RequestParam(value = "p1",required = false)String p1,
                                @RequestParam(value = "p1",required = false)String p2, BlockException ex){
        return "testHostKey,失败";
    }
}
