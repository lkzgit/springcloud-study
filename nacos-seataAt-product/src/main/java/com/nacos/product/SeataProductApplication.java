package com.nacos.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lkz
 * @date 2021/10/18 15:44
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SeataProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataProductApplication.class,args);
    }
}
