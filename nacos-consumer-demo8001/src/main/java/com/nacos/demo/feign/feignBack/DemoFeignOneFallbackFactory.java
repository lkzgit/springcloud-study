package com.nacos.demo.feign.feignBack;


import com.nacos.demo.feign.DemoFeignOne;
import feign.hystrix.FallbackFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class DemoFeignOneFallbackFactory implements FallbackFactory<DemoFeignOne> {

    @Override
    public DemoFeignOne create(Throwable throwable) {
        return new DemoFeignOne() {

            @Override
            public String demoFeign() {
                return "调用失败了喽"+throwable.getMessage();
            }

            @Override
            public String testRibbon() {
                return "轮询调用失败";
            }

            @Override
            public String testSentinel() {
                return "sentinel 限流了 哈哈哈";
            }
        };
    }
}