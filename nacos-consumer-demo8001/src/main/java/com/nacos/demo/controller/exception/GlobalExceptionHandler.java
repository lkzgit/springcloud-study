package com.nacos.demo.controller.exception;


import com.alibaba.csp.sentinel.slots.block.BlockException;

import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.nacos.demo.exception.FabackBlockExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lkz
 * @date 2021/10/13 14:30
 * @description 异常返回
 */
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(BlockException.class)
//    public String customBlockException(BlockException handler){
//        if (handler instanceof FlowException) {
//            return "接口限流了";
//
//        } else if (handler instanceof DegradeException) {
//
//            return "服务降级了";
//
//        } else if (handler instanceof ParamFlowException) {
//
//            return "热点参数限流了";
//
//        } else if (handler instanceof SystemBlockException) {
//
//            return "触发系统保护规则";
//
//        } else if (handler instanceof AuthorityException) {
//
//            return "授权规则不通过";
//        }
//
//        return "最外层sentinel限流了";
//    }
//    @ExceptionHandler(FabackBlockExceptionHandler.class)
//    public String fabackBlockException(){
//        return "运行时异常";
//    }
//}
