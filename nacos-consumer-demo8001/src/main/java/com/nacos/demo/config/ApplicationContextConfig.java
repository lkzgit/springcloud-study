package com.nacos.demo.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author lkz
 * @date 2021/10/11 15:15
 * @description 模拟负载均衡
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced // 负载均衡
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }

}
