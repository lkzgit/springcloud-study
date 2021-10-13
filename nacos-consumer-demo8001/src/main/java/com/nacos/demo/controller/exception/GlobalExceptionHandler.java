package com.nacos.demo.controller.exception;


import com.alibaba.csp.sentinel.slots.block.BlockException;

import com.nacos.demo.exception.FabackBlockExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lkz
 * @date 2021/10/13 14:30
 * @description 异常返回
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BlockException.class)
    public String customBlockException(BlockException handler){

        return "sentinel限流了";
    }
    @ExceptionHandler(FabackBlockExceptionHandler.class)
    public String fabackBlockException(){
        return "运行时异常";
    }
}
