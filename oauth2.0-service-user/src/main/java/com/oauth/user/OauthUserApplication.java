package com.oauth.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author lkz
 * @date 2021/12/14 14:31
 * @description 模拟用户登录
 */
@SpringBootApplication
@EnableEurekaClient

public class OauthUserApplication {


    public static void main(String[] args) {
        SpringApplication.run(OauthUserApplication.class, args);
    }
}
