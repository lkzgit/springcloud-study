package com.nacos.cosumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NacosConsumer {

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumer.class,args);
    }

}
