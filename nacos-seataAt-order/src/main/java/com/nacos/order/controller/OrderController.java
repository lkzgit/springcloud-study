package com.nacos.order.controller;

import com.nacos.order.fegin.AccountFeign;
import com.nacos.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lkz
 * @date 2021/10/18 15:46
 * @description
 */
@RestController
@Slf4j
public class OrderController {

    @Autowired
    AccountFeign accountFeign;

    @Autowired
    OrderService orderService;


    @GetMapping("/create")
    public Integer createOrder() throws Exception {
        Long userId=(long)1;
        Long productId=(long)1;
        Integer price=50;
        log.info("[createOrder] 收到下单请求,用户:{}, 商品:{}, 价格:{}", userId, productId, price);
        return orderService.createOrder(userId, productId, price);
    }

    @GetMapping("test")
    public String order(){
        return "order";
    }

    @GetMapping("a")
    public String testAccount(){
        return accountFeign.account();
    }
}
