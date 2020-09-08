package com.consumer.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@EnableDiscoveryClient
@SpringBootApplication
public class ConsulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulApplication.class,args);
    }

    @Bean
    @LoadBalanced
        // 开启负载均衡 跨服务调用
    RestTemplate restTemplate() {

        return new RestTemplate();

    }

}
