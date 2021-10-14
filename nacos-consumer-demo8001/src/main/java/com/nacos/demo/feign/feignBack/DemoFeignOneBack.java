package com.nacos.demo.feign.feignBack;

import com.nacos.demo.feign.DemoFeignOne;
import org.springframework.stereotype.Component;

/**
 * @author lkz
 * @date 2021/10/12 17:52
 * @description
 */
@Component
public class DemoFeignOneBack implements DemoFeignOne {
    @Override
    public String demoFeign() {
        return "demoFeign 失败";
    }

    @Override
    public String testRibbon() {
        return "testRibbon失败";
    }

    @Override
    public String testSentinel() {
        return "sentinel back 失败";
    }
}
