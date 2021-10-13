package com.nacos.demo.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.nacos.demo.exception.FabackBlockExceptionHandler;
import com.nacos.demo.feign.SentinelFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lkz
 * @date 2021/10/12 18:38
 * @description 测试限流
 */
@RestController
@Slf4j
public class SentinelController {


    @Autowired
    SentinelFeign sentinelFeign;


    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping("testSentinelFeign")
    public String testSentinelFeign(){
        return sentinelFeign.testSentinel();
    }

    @GetMapping("test2")
    @SentinelResource("test2")
    public String test2(String id){
        if(id.equals("0")){
            throw new FabackBlockExceptionHandler("0不可以");
        }
        return "sentinel";
    }


    @GetMapping("/app1")
    public String app1() {
        try {
            Thread.sleep(2005);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(111);
        return restTemplate.getForObject(serverURL+"/testSentinel", String.class);
    }

    @GetMapping("/app2")
    public String app2() {
        return restTemplate.getForObject(serverURL+"/testSentinel", String.class);
    }
    
    /*
     * @Author lkz
     * @Description //限流实现方式二: 注解方式定义资源
     * @Date 10:08 2021/10/13
     * @SentinelResource 注解用来标识资源是否被限流、降级。
     * value = "order" 写上资源名称
        blockHandler:  定义当资源内部发生了BlockException应该进入的方法（捕获的是Sentinel定义的异常）
        fallback:  定义的是资源内部发生了Throwable应该进入的方法
        exceptionsToIgnore：配置fallback可以忽略的异常
     *  <!-- @SentinelResource注解实现 -->
        <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-annotation-aspectj</artifactId>
            <version>1.8.0</version>
        </dependency>
     **/
//    @SentinelResource(value = "order", blockHandler = "handleFlowQpsException",
//            fallback = "queryOrderInfo2Fallback")
//    @GetMapping("order")
//    public String queryOrderInfo2(String orderId) {
//
//        // 模拟接口运行时抛出代码异常
//        if ("000".equals(orderId)) {
//            throw new RuntimeException();
//        }
//
//        System.out.println("获取订单信息:" + orderId);
//        return "return OrderInfo :" + orderId;
//    }
//
//    /**
//     * 订单查询接口抛出限流或降级时的处理逻辑
//     * 注意: 方法参数、返回值要与原函数保持一致
//     * @return
//     */
//    public String handleFlowQpsException(String orderId, BlockException e) {
//        e.printStackTrace();
//        return "handleFlowQpsException for queryOrderInfo2: " + orderId;
//    }
//    /**
//     * 订单查询接口运行时抛出的异常提供fallback处理
//     * 注意: 方法参数、返回值要与原函数保持一致
//     * @return
//     */
//    public String queryOrderInfo2Fallback(String orderId, Throwable e) {
//        return "fallback queryOrderInfo2: " + orderId;
//    }


   /*
    * @Author lkz
    * @Description //Api方式使用sentinel
    * @Date 10:01 2021/10/13
             <!-- Api方式调用 -->
        <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-core</artifactId>
            <version>1.8.0</version>
        </dependency>

    **/
//    private static final String RESOURCE_NAME = "hello";
//
//    @RequestMapping(value = "/hello")
//    public String hello() {
//
//        Entry entry = null;
//        try {
//            // 资源名可使用任意有业务语义的字符串，比如方法名、接口名或其它可唯一标识的字符串。
//            entry = SphU.entry(RESOURCE_NAME);
//            // 被保护的业务逻辑
//            String str = "hello world";
//            log.info("====="+str);
//            return str;
//        } catch (BlockException e1) {
//            // 资源访问阻止，被限流或被降级
//            //进行相应的处理操作
//            log.info("block!");
//            return "资源访问阻止，被限流或被降级";
//        } catch (Exception ex) {
//            // 若需要配置降级规则，需要通过这种方式记录业务异常
//            Tracer.traceEntry(ex, entry);
//        } finally {
//            if (entry != null) {
//                entry.exit();
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 定义流控规则
//     */
//    @PostConstruct
//    private static void initFlowRules(){
//        List<FlowRule> rules = new ArrayList<>();
//        FlowRule rule = new FlowRule();
//        //设置受保护的资源
//       // rule.setResource(RESOURCE_NAME);
//        rule.setResource("order");
//        // 设置流控规则 QPS
//        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
//        // 设置受保护的资源阈值
//        // Set limit QPS to 20.
//        rule.setCount(1);
//        rules.add(rule);
//        // 加载配置好的规则
//        FlowRuleManager.loadRules(rules);
//    }


}
