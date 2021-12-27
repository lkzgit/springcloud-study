package com.oauth.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lkz
 * @ClassName: OauthNewApplication
 * @description: TODO
 * @date 2021/12/27 14:54
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OauthNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthNewApplication.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
