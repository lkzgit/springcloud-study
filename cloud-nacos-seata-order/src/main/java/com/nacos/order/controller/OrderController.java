package com.nacos.order.controller;


import com.nacos.order.domain.CommonResult;
import com.nacos.order.domain.Order;
import com.nacos.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class OrderController
{
    @Resource
    private OrderService orderService;
    @GetMapping("test")
    public String test(){
        return "我是订单；";
    }

    @GetMapping("/order/create")
    public CommonResult create(Order order)
    {
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }
}
