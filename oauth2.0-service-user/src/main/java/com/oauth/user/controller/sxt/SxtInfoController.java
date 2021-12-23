package com.oauth.user.controller.sxt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lkz
 * @date 2021/12/17 15:46
 * @description
 */
@RestController
public class SxtInfoController {

    @GetMapping("tokenInfo")
    public String tokenInfo() {
        //从 request 中获取token
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String authorization = request.getHeader("Authorization");
        System.out.println("获取的token；" + authorization);
        String token = authorization.replaceAll("bearer ", "");
        //2.从 SecurityContextHolder 中获取
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //转成我们需要的
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        String tokenValue = details.getTokenValue();
        System.out.println(tokenValue);
        return tokenValue;


    }



}
