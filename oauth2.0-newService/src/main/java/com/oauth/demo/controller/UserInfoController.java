package com.oauth.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lkz
 * @ClassName: UserInfoController
 * @description: 用户信息
 * @date 2021/12/27 15:54
 */
@RequestMapping("user")
@RestController
public class UserInfoController {


    @RequestMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication) {
        return authentication.getPrincipal();
    }

}
