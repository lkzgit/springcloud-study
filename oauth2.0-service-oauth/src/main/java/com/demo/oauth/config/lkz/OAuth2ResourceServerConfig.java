package com.demo.oauth.config.lkz;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author lkz
 * @ClassName: OAuth2ResourceServerConfig
 * @description: TODO
 * @date 2021/12/24 9:40
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 设置 /login 无需权限访问
                .antMatchers("/login").permitAll()
                // 设置其它请求，需要认证后访问
                .anyRequest().authenticated()
        ;
    }

}
