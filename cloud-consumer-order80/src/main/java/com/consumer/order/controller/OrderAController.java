package com.consumer.order.controller;


import com.api.common.CommonResult;
import com.api.common.entity.Payment;
import com.consumer.order.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("order")
@Slf4j
public class OrderAController {

    @Resource
    private RestTemplate restTemplate;
    //
   @Resource
   private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;


    @GetMapping("/test")
    public CommonResult get(){

        return new CommonResult(0000,"测试成功order");
    }

   // public static final String PAYMENT_URL = "http://localhost:8081";

    public static final String PAYMENT_URL = "http://CLOUD-PROVIDER-SERVICE";



    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment)
    {
        return restTemplate.postForObject(PAYMENT_URL +"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id)
    {
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id)
    {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);

        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult<>(444,"操作失败");
        }
    }

    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB()
    {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");

        if(instances == null || instances.size() <= 0)
        {
            return null;
        }

        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri+"/payment/lb",String.class);

    }
//
//    // ====================> zipkin+sleuth
//    @GetMapping("/consumer/payment/zipkin")
//    public String paymentZipkin()
//    {
//        String result = restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin/", String.class);
//        return result;
//    }



}
