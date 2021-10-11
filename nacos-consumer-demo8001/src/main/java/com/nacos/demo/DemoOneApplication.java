package com.nacos.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lkz
 * @date 2021/10/11 11:38
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class DemoOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoOneApplication.class,args);
    }
}
