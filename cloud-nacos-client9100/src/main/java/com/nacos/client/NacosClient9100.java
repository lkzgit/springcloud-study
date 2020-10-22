package com.nacos.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosClient9100 {

    public static void main(String[] args) {
        SpringApplication.run(NacosClient9100.class,args);
    }
}
