package com.oauth.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author lkz
 * @date 2021/12/14 14:31
 * @description 模拟用户登录
 */
@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer //开启资源服务器 下面的url都被保护起来
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OauthUserApplication {


    public static void main(String[] args) {
        SpringApplication.run(OauthUserApplication.class, args);
    }
}
