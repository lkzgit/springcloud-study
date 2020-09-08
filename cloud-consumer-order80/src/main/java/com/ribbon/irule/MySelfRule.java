package com.ribbon.irule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义负载均衡路由规则
 * 官网提示不能放在@CompoentScance扫描和子包下面
 */

@Configuration
public class MySelfRule {

    /**
     * 它默认出厂自带了7种算法。
     *
     * 第一种是：RoundRobinRule 轮询
     *
     * 第二种是：RandomRule 随机
     *
     * 第三种是：AvailabilityFilteringRule 会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，
     *
     * 还有并发的连接数量超过阈值的服务，然后对剩余的服务列表按照轮询策略进行访问。
     *
     * 第四种是：WeightedResponseTimeRule 根据平均响应时间计算所有服务的权重，响应时间越快服务权重越大被选中的概率越高。刚启动时如果统计信息不足，则使用RoundRobinRule策略，等统计信息足够，会切换到WeightedResponseTimeRule。
     *
     * 第五种是：RetryRule 先按照RoundRobinRule的策略获取服务，如果获取服务失败则在指定时间内会进行重试，获取可用的服务。
     *
     * 第六种是：BestAvailableRule 会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务。
     *
     * 第七种是：ZoneAvoidanceRule 默认规则,复合判断server所在区域的性能和server的可用性选择服务器。
     * @return
     */
    @Bean
    public IRule myRule()
    {
        return new RandomRule();//定义为随机
    }

}
