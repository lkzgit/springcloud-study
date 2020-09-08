package com.provider.zooker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用consul或者zookeeper作为注册中心时注册服务
public class ZookerApplication8083 {
    /**
     * 使用zookeeper作为配置中心
     */
    public static void main(String[] args) {
        SpringApplication.run(ZookerApplication8083.class,args);
    }
}
