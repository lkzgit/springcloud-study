package com.demo.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lkz
 * @version 1.0.0
 * @ClassName OauthController.java
 * @Description TODO
 * @createTime 2021年12月05日 16:07:00
 */
@RestController
public class OauthController {

    @GetMapping("testOauth")
    public String testOauth(){
        return "oauth---服务";
    }
}
