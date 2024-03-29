package com.nacos.demo;

import lombok.experimental.var;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author lkz
 * @date 2021/10/11 11:38
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients //开启feign
public class DemoOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoOneApplication.class,args);
    }

}
