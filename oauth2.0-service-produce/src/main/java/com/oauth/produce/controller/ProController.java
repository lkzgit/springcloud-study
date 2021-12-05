package com.oauth.produce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lkz
 * @version 1.0.0
 * @ClassName ProController.java
 * @Description TODO
 * @createTime 2021年12月05日 15:42:00
 */

@RestController
public class ProController {


    @GetMapping("testPro")
    public String testPro(){

        return "pro---服务";
    }
}
