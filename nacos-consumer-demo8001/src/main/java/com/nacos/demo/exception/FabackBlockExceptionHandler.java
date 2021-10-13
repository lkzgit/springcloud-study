package com.nacos.demo.exception;

/**
 * @author lkz
 * @date 2021/10/13 14:21
 * @description 内部的异常
 */
public class FabackBlockExceptionHandler extends RuntimeException{
    public FabackBlockExceptionHandler(String message) {
        super(message);
    }
}
