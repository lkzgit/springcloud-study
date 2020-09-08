package com.feign.hystrix.Feign;

import org.springframework.stereotype.Component;

/**
 * 服务降级处理 ，
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService
{
    /**
     * 消费者项目；并且因为还有一个 feign 也有一个超时时间的设置，
     * 当然feign底层是ribbon的封装，所以直接配置ribbon，ribbon默认超时也是1秒。
     * 所以这里都是强制要求，ribbon的超时时间要大于hystrix的超时时间
     * ，否则 hystrix自定义的超时时间毫无意义。
     */

    @Override
    public String paymentInfo_OK(Integer id)
    {
        return "-----lkz +PaymentFallbackService fall back-paymentInfo_OK ,o(╥﹏╥)o"+id;
    }

    @Override
    public String paymentInfo_TimeOut(Integer id)
    {
        return "-----kz+PaymentFallbackService fall back-paymentInfo_TimeOut ,o(╥﹏╥)o"+id;
    }
}
