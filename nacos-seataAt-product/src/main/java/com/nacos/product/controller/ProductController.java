package com.nacos.product.controller;

import com.nacos.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lkz
 * @date 2021/10/18 15:45
 * @description
 */
@RestController
@Slf4j
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("test")
    public String test(){
        return " product";
    }

    @GetMapping("reduceStock")
    public void reduceStock(Long productId,Integer amount) throws Exception {
        log.info("[reduceStock] 收到减少库存请求, 商品:{}, 价格:{}", productId,
                amount);
        productService.reduceStock(productId,amount);
    }
}
