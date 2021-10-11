package com.nacos.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * @author lkz
 * @date 2021/10/11 20:05
 * @description
 */
@SpringBootApplication
public class Config7001 {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(Config7001.class, args);

        while(true) {
            //当动态配置刷新时，会更新到 Enviroment中，因此此处每隔一秒从Enviroment中获取配置
            String userName = applicationContext.getEnvironment().getProperty("user.name");
            String userAge = applicationContext.getEnvironment().getProperty("user.age");
            System.out.println("user name : " + userName + "; age: " + userAge);

            //获取当前部署的环境
            String currentEnv = applicationContext.getEnvironment().getProperty("current.env");
            System.err.println("in [ "+currentEnv+" ] enviroment; "+"user name :" + userName + "; age: " + userAge);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


}
