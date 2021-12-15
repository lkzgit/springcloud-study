package com.oauth.user.controller;


import cn.hutool.crypto.digest.BCrypt;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.demo.common.util.JwtUtil;
import com.oauth.user.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author lkz
 * @date 2021/12/14 14:33
 * @description
 */
@RestController
@RequestMapping("login")
public class LoginController {


    @GetMapping("test")
    public String login(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        System.out.println(authorization);
        return "ok";
    }

    @GetMapping("tologin")
    public String toLogin(@RequestParam("name")String name,@RequestParam("pass")String pass){
        User user = new User();
        user.setName("33");
        user.setPass("$2a$10$uQj5zpivxuq4Yk4W8Ux5vOFfG3mlNHvjRKGHU0xymhMMmoDjFyX.O");

        if(BCrypt.checkpw(pass,user.getPass())){
            //设置令牌信息
            Map<String,Object> info = new HashMap<String,Object>();
            info.put("role","USER");
            info.put("success","SUCCESS");
            info.put("username",name);

            //生成令牌
            String jwt = JwtUtil.createJWT(UUID.randomUUID().toString(), JSONUtil.toJsonStr(info),null);
            Cookie cookie = new Cookie("Authorization", jwt);
            return "生成的token:"+jwt;
        }
        return  "err";


    }


    public static void main(String[] args) {
        String hashpw = BCrypt.hashpw("12345");
        boolean checkpw = BCrypt.checkpw("123445", "$2a$10$uQj5zpivxuq4Yk4W8Ux5vOFfG3mlNHvjRKGHU0xymhMMmoDjFyX.O");
        System.out.println(checkpw);
        System.out.println(hashpw);
    }
}
