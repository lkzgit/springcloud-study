package com.oauth.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lkz
 * @version 1.0.0
 * @ClassName Test.java
 * @Description TODO
 * @createTime 2021年12月05日 16:10:00
 */
@RestController
public class Test {

    @GetMapping("test")
    public String test(){
        return "gateway";
    }
}
