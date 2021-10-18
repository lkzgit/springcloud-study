package com.nacos.order.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lkz
 * @date 2021/10/18 15:56
 * @description
 */
@FeignClient(name = "nacos-product")
@Component
public interface ProductFeign {

    @GetMapping("test")
    String productFeign();
    @GetMapping("reduceStock")
    void reduceStock(@RequestParam("productId") Long productId,@RequestParam("amount") Integer amount);
}
