package com.nacos.demo.feign.feignBack;


import com.nacos.demo.feign.DemoFeignOne;
import feign.hystrix.FallbackFactory;


public class DemoFeignOneFallbackFactory implements FallbackFactory<DemoFeignOne> {

    @Override
    public DemoFeignOne create(Throwable throwable) {
        return new DemoFeignOne() {

            @Override
            public String demoFeign() {
                return "调用失败了喽"+throwable.getMessage();
            }

        };
    }
}