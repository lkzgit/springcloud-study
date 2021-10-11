package com.nacos.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lkz
 * @date 2021/10/11 14:23
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderDemo9001 {

    public static void main(String[] args) {
        SpringApplication.run(ProviderDemo9001.class,args);
    }
}
