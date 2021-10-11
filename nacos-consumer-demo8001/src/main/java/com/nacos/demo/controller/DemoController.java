package com.nacos.demo.controller;

import com.nacos.demo.feign.DemoFeignOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author lkz
 * @date 2021/10/11 11:41
 * @description
 */
@RestController
public class DemoController {

    @Resource
    RestTemplate restTemplate;

    @Autowired
    DemoFeignOne demoFeignOne;
    //获取服务信息
    @Autowired
    DiscoveryClient discoveryClient;


    @Value("${service-url.nacos-user-service}")
    private String serverURL;


    //测试轮询调用
    @GetMapping("test")
    public String test1(){

        return restTemplate.getForObject(serverURL+"/demo",String.class);

    }
    //获取服务信息
    @GetMapping("/services/{service}")
    public Object client(@PathVariable String service) {
        return discoveryClient.getInstances(service);
    }

    @GetMapping("/services")
    public Object services() {
        System.out.println(discoveryClient.description());
        System.out.println(discoveryClient.getOrder());
        return discoveryClient.getServices();
    }

    //测试Feign调用
    @GetMapping("testFeign")
    public String testFeign(){

        return demoFeignOne.demoFeign();

    }

    @GetMapping("demo")
  public String test(){
      return "nihao nacos";
  }
}
