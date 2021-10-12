package com.nacos.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lkz
 * @date 2021/10/12 18:41
 * @description
 */
@FeignClient(name = "demo9001-provider"
)
@Component
public interface SentinelFeign {

    @GetMapping("testSentinel")
    public String testSentinel();
}
