package com.demo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lkz
 * @date 2021/10/14 15:46
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosGateWayApplication.class,args);
    }
}
