package com.provider.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsulApplication8084 {

    public static void main(String[] args) {
        SpringApplication.run(ConsulApplication8084.class,args);
    }

}
