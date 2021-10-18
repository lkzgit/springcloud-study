package com.nacos.order.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lkz
 * @date 2021/10/18 15:51
 * @description
 */
@Component
@FeignClient(name = "nacos-account")
public interface AccountFeign {

    @GetMapping("/test")
    String account();

    @GetMapping("reducebalance")
    void reduceBalance(@RequestParam("userId") Long userId,@RequestParam("price") Integer price);
}
