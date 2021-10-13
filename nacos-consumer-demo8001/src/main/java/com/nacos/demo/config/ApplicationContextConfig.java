package com.nacos.demo.config;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.nacos.demo.exception.MyBlockHandlerClass;
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

    //支持sentinel 限流
//    @SentinelRestTemplate(/*blockHandler="blockA", blockHandlerClass= MyBlockHandlerClass.class*/ //限流
//            fallback="fallbackA", fallbackClass = MyBlockHandlerClass.class) // 降级
    @Bean
    @LoadBalanced // 负载均衡
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }

}
