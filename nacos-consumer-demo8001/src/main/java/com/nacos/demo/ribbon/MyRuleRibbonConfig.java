package com.nacos.demo.ribbon;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lkz
 * @date 2021/10/12 10:02
 * @description 负载均衡算法
 */
@Configuration
public class MyRuleRibbonConfig {

    /**
     * 更改负载均衡策略，默认是ZoneAvoidanceRule策略
     * 注入改负载   restTemplate 使用@LoadBalanced失效
     * @return
     * 可以通过以下方式在配置文件中进行配置
     * 服务名.ribbon.NFLoadBalancerRuleClassName=com.bjpowernode.ribbon.MyNacosRule
     */
//    @Bean
//    public IRule iRule() {
//      //  return new NacosRule();
//        return new MyNacosRule(); //使用自定义的负载算法
//        return new MyNacosVersionRule(); //版本号负载
//    }
    //该类是ribbon的一个接口 什么都不做 默认每一个实例都能ping通 自定义扩展使用
//    @Bean
//    public IPing iPing () {
//        return new PingUrl();
//    }

    /***
     * @Author lkz
     * @Description Nacos同一集群优先负载均衡
     * 集群名称相同的优先调用，当具有相同名称的集群宕机了，才会调用另一个名称不相同的集群；
     * Nacos不能跨namespace调用
     **/

}
