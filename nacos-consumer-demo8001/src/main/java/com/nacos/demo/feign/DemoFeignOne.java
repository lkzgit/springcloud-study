package com.nacos.demo.feign;

import com.nacos.demo.feign.configuration.FeignConfiguration;
import com.nacos.demo.feign.feignBack.DemoFeignOneFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lkz
 * @date 2021/10/11 16:13
 * @description
 */

@FeignClient(name = "demo9001-provider",
        fallbackFactory = DemoFeignOneFallbackFactory.class,
        configuration = FeignConfiguration.class)
@Component
public interface DemoFeignOne {
    @GetMapping("/demoFeign")
    public String demoFeign();

}
