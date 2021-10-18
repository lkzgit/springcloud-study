package com.nacos.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author lkz
 * @date 2021/10/18 15:01
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SeataAtAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataAtAccountApplication.class,args);
    }
}
