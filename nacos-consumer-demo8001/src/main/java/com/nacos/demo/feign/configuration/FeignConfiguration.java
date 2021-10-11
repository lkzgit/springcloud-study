package com.nacos.demo.feign.configuration;

import com.nacos.demo.feign.DemoFeignOne;
import com.nacos.demo.feign.feignBack.DemoFeignOneFallbackFactory;
import org.springframework.context.annotation.Bean;

/**
 * @author lkz
 * @date 2021/10/11 16:14
 * @description
 */
public class FeignConfiguration  {


    @Bean
    public DemoFeignOneFallbackFactory demoFeignOneFallbackFactory(){
        return new DemoFeignOneFallbackFactory();
    }
}
