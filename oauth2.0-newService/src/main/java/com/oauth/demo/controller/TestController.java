package com.oauth.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lkz
 * @ClassName: TestController
 * @description: TODO
 * @date 2021/12/27 15:14
 */
@RestController
public class TestController {

    @GetMapping("test")
    public String test1(){
        return "test---1";
    }
}
