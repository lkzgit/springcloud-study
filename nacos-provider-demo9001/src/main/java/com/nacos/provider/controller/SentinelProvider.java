package com.nacos.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lkz
 * @date 2021/10/12 18:39
 * @description 服务端
 */
@RestController
public class SentinelProvider {

    @GetMapping("testSentinel")
    public String testSentinel(){
        return "testSentinel haha";
    }
}
