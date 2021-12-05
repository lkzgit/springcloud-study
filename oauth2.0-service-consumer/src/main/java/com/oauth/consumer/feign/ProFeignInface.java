package com.oauth.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lkz
 * @version 1.0.0
 * @ClassName ProFeignInface.java
 * @Description TODO
 * @createTime 2021年12月05日 15:50:00
 */
@FeignClient(name = "oauth-produce")
@Component
public interface ProFeignInface {

    @GetMapping("produce/testPro")
    String getTestPro();
}
