package com.oauth.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lkz
 * @ClassName: TestController
 * @description: 测试登录授权服务
 * @date 2021/12/23 14:34
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("test1")
    @PreAuthorize("hasAuthority('test')")
    public String test1(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        System.out.println(authorization);
        return "test1-----ok";
    }


    @GetMapping("test2")
    @PreAuthorize("hasAuthority('admin')")
    public String test2(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        System.out.println(authorization);
        return "test2-----ok";
    }

    @GetMapping("test3")
    @PreAuthorize("hasAuthority('test')or hasAuthority('admin')")
    public String test3(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        System.out.println(authorization);
        return "test3-----ok";
    }

}
