package com.nacos.demo.exception;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

public class MyBlockHandlerClass {

    /**
     * 该方法一定要是static方法
     *
     * @param a
     * @param b
     * @return
     */
    public static String block(String a, String b, BlockException e) {
        System.out.println(" restTemplate ----- Block Handler--> " + a + "--" + b);
        return "restTemplate -----Block Handler.";
    }

    /**
     * 限流后处理方法
     *
     * @param request
     * @param body
     * @param execution
     * @param ex
     * @return
     */
    public static SentinelClientHttpResponse blockA(HttpRequest request,
                                                    byte[] body, ClientHttpRequestExecution execution, BlockException ex) {
        System.err.println(" restTemplate -----block: " + ex.getClass().getCanonicalName());
        return new SentinelClientHttpResponse(" restTemplate -----custom block info");
    }

    /**
     * 熔断后处理的方法
     *
     * @param request
     * @param body
     * @param execution
     * @param ex
     * @return
     */
    public static SentinelClientHttpResponse fallbackA(HttpRequest request,
                                                       byte[] body, ClientHttpRequestExecution execution, BlockException ex) {
        System.err.println("restTemplate -----fallback: " + ex.getClass().getCanonicalName());
        return new SentinelClientHttpResponse("restTemplate -----custom fallback info");
    }
}