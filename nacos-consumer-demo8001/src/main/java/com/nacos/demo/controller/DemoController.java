package com.nacos.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lkz
 * @date 2021/10/11 11:41
 * @description
 */
@RestController
public class DemoController {

    @GetMapping("demo")
  public String test(){
      return "nihao nacos";
  }
}
