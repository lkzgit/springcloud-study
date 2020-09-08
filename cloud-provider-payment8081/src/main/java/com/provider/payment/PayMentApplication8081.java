package com.provider.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient //服务 的注册与发现
public class PayMentApplication8081 {

    public static void main(String[] args) {
        SpringApplication.run(PayMentApplication8081.class,args);
    }
}
