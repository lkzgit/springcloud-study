package com.nacos.demo.feign;

import com.nacos.demo.feign.configuration.FeignConfiguration;
import com.nacos.demo.feign.feignBack.DemoFeignOneBack;
import com.nacos.demo.feign.feignBack.DemoFeignOneFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lkz
 * @date 2021/10/11 16:13
 * @description fallbackFactory = DemoFeignOneFallbackFactory.class,
 * fallback 与 fallbackFactory 一个是聚合一个是分散
 *  configuration = FeignConfiguration.class
 */

@FeignClient(name = "demo9001-provider",
//        fallback = DemoFeignOneBack.class,
        fallbackFactory = DemoFeignOneFallbackFactory.class,
        configuration = FeignConfiguration.class
       )
@Component
public interface DemoFeignOne {

    @GetMapping("/demoFeign")
    public String demoFeign();
    @GetMapping("/testRibbon")
    public String testRibbon();
    @GetMapping("testSentinel")
    public String testSentinel();

}
