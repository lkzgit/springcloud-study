package com.nacos.demo.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.nacos.demo.exception.FabackBlockExceptionHandler;
import com.nacos.demo.exception.MyBlockHandlerClass;
import com.nacos.demo.feign.SentinelFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lkz
 * @date 2021/10/12 18:38
 * @description 测试限流
 *
 * 1.流控模式：
 *  直接 ：就是直接对该资源进行控制；
 *  关联，关联某一个资源（/app2），被关联的资源/app2达到单机阈值，则限制当前资源/test的访问；
 *  链路，记录指定链路上的流量； 入口资源就是  sentinel_spring_web_context
 * 流控效果：
 *      快速失败 ，直接限制
 *      Warm Up，根据coldFactor（默认为3）的值，从 阈值/coldFactor，
 * 经过预热的时长，才达到设置的QPS阈值，比如设置QPS阈值为100，那么100/3 =33，用33作为最初的阈值，
 * 然后在10秒到达100后再开始限流
 *      排队等待，在QPS阈值到达后，新的请求就等待，直到超时，可以适用于突发流量的请求 超时时间内还是到达阈值 就限流
 *  2.降级策略：
 *      RT: 平均响应时间 (DEGRADE_GRADE_RT)，当 1s 内持续进入 N 个请求，
 * 对应时刻的平均响应时间（秒级）均超过阈值（count，以 ms 为单位），那么在接下的时间窗口（DegradeRule 中的 timeWindow，
 * 以 s 为单位）之内，对这个方法的调用都会自动地熔断（抛出 DegradeException），
 * 走降级规则 当超过时间窗口的时间之后又会开始新一轮的判断
 * 注意 Sentinel 默认统计的 RT 上限是 4900 ms，超出此阈值的都会算作 4900 ms，
 * 若需要变更此上限可以通过启动配置项 -Dcsp.sentinel.statistic.max.rt=xxx 来配置；
 *      异常比例：异常比例 (DEGRADE_GRADE_EXCEPTION_RATIO)是指当资源的每秒异常总数占通过量的比值
 * 超过阈值（DegradeRule 中的 count）之后，
 * 资源进入降级状态，即在接下的时间窗口（DegradeRule 中的 timeWindow，以 s 为单位）之内，
 * 对这个方法的调用都会自动降级返回， 异常比率的阈值范围是 [0.0, 1.0]，代表 0% - 100%；
 *      异常数：异常数 (DEGRADE_GRADE_EXCEPTION_COUNT)是指当资源近1分钟的异常数目超过阈值之后会进行熔断，
 * 注意由于统计时间窗口是分钟级别的，若timeWindow小于 60s，则结束熔断状态后仍可能再进入熔断状态；
 *  3.热点规则  热点即经常访问的数据，很多时候我们希望统计某个热点数据中访问频次最高的 Top K 数据，并对其访问进行限制
 *  热点规则需要使用@SentinelResource("app")注解，否则不生效
 *  参数必须是7种基本数据类型才会生效   热点参数可以对某个具体的热点参数进行限流
 *  参数例外项 中 参数值 与阈值设置同样限流 正常情况走上面设置的阈值
 *  4.系统规则 ;针对整个服务限流
 *      4.1 Load，Load自适应（仅对Linux/Unix-like机器生效）：系统的load1作为启发指标，进行自适应系统保护，
 *  当系统load1超过设定的启发值，且系统当前的并发线程数超过估算的系统容量时才会触发系统保护，
 * 系统容量由系统的maxQps * minRt估算得出，设定参考值一般是 CPU cores * 2.5；
 *      4.2.平均 RT：当单台机器上所有入口流量的平均RT达到阈值即触发系统保护，单位是毫秒；
 *      4.3.并发线程数：当单台机器上所有入口流量的并发线程数达到阈值即触发系统保护；
 *      4.4入口 QPS：当单台机器上所有入口流量的 QPS 达到阈值即触发系统保护；
 *      4.5.CPU usage（1.5.0+ 版本）：当系统 CPU 使用率超过阈值即触发系统保护（取值范围 0.0-1.0），比较灵敏；
 *  5.授权规则
 *     资源名：
 *     流控应用：请求头中添加 MyRequestOriginParser
 *     授权类型： 黑白名单
 */
@RestController
@Slf4j
public class SentinelController {


    @Autowired
    SentinelFeign sentinelFeign;


    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.url.provider}")
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

    //测试服务降级规则 平均时间
    @GetMapping("/app1")
    public String app1() {
        try {
            Thread.sleep(2005);
            //int a=10/0; //模拟异常
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(111);
        return restTemplate.getForObject(serverURL+"/testSentinel", String.class);
    }

    /**
     * 热点参数规则
     * blockHandler = "block", blockHandlerClass = MyBlockHandlerClass.class 处理限流和降级
     * fallback = "fallback", fallbackClass = MyFallbackClass.class 处理限流和降级
     */
    @GetMapping("/app") // 埋点：加入sentinel的监控
    //@SentinelResource(value = "app", fallback = "fallback", fallbackClass = MyFallbackClass.class)
    @SentinelResource(value = "app", blockHandler = "block", blockHandlerClass = MyBlockHandlerClass.class)
    public String app(@RequestParam(value = "a", required = false) String a,
                      @RequestParam(value = "b", required = false) String b) {
        System.out.println("/app/--> " + a + "--" + b);
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
