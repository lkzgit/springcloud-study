package com.nacos.seate.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan({"com.nacos.seate.dao"})
public class MyBatisConfig {
}
