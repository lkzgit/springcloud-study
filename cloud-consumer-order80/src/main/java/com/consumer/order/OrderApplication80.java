package com.consumer.order;

import com.ribbon.irule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name = "CLOUD-PROVIDER-SERVICE",configuration= MySelfRule.class) //修改轮询规则
public class OrderApplication80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication80.class,args);
    }

    @Bean
    //@LoadBalanced // 开启负载均衡 跨服务调用 测试自己的负载均衡关闭
    RestTemplate restTemplate() {

        return new RestTemplate();

    }
}
