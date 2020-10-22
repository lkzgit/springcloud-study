package com.seata.model.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan({"com.seata.model.dao"})
public class MyBatisConfig {
}
