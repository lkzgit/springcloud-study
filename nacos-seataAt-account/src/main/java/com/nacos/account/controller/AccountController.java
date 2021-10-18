package com.nacos.account.controller;


import com.nacos.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lkz
 * @date 2021/10/18 15:01
 * @description
 */
@RestController
@Slf4j
public class AccountController {


    @Autowired
    AccountService accountService;

    @GetMapping("/reducebalance")
    public void reduceBalance(@RequestParam("userId")Long userId, @RequestParam("price")Integer price) throws Exception {
        log.info("[reduceBalance] 收到减少余额请求, 用户:{}, 金额:{}", userId,
                price);
        accountService.reduceBalance(userId, price);
    }

    @GetMapping("/test2")
    public String test(){
        return "account";
    }
}
