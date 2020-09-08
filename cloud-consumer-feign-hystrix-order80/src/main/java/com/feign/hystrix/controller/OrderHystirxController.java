package com.feign.hystrix.controller;


import com.feign.hystrix.Feign.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystirxController
{
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/payment/hystrix/ok/{id}")
    //@HystrixCommand
    public String paymentInfo_OK(@PathVariable("id") Integer id)
    {
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }


    /**
     * 设置自身调用超时时间的峰值（1500）,峰值内可以正常运行,
     * 代码出现错误或者超过了需要有兜底的方法处理,做服务降级fallback
     * @param id
     * @return 设置在自身中 容易代码臃肿
     * // 设置自身超时时间，1500秒钟以内为正常，大于这个峰值，则会进行服务降级处理
     */
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",
//            commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")})
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id)
    {
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        System.out.println("lkz");
        return result+"lkz";
    }

    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id)
    {
        return "我是消费者8000,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    // 下面是全局fallback方法 //@HystrixCommand表示没有指定使用全局的fallback
    public String payment_Global_FallbackMethod()
    {
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }
}
