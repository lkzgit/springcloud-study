package com.nacos.order.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan({"com.nacos.order.dao"})
public class MyBatisConfig {
}
