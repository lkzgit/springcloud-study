package com.demo.oauth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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


    @GetMapping("getInfo")
    public Object getLoginInfo(Principal principal){
        return principal;
    }

    /**
     * 测试必须拥有admin权限
     * @return
     */
    @GetMapping("getadmin")
    @PreAuthorize("hasAuthority('admin')")
    public Object getAdmin(){
        return "admin";
    }

    /**
     * 测试拥有test
     * @return
     */
    @GetMapping("getTest")
    @PreAuthorize("hasAuthority('test')")
    public Object getTest(){
        return "test";
    }

    /**
     * 测试 权限拥有 test权限或者admin
     * @return
     */
    @GetMapping("getPer")
    @PreAuthorize("hasAuthority('test')or hasAuthority('admin')")
    public Object getPer(){
        return "admin or test";
    }
    //匿名访问
    @GetMapping("getPermitAll")
    @PreAuthorize("permitAll")
    public Object getPermitAll(){
        return "permitAll";
    }

}
