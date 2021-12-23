package com.oauth.user.oauth;

import cn.hutool.core.io.FileUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author lkz
 * @date 2021/12/17 15:38
 * @description 资源服务器 处理梅西验证都去授权服务器去验证问题
 */
@EnableResourceServer //开启资源服务器 下面的url都被保护起来
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserResourceConfig extends ResourceServerConfigurerAdapter {

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 转换jwt
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        // 需要一个sign签名 这叫对称加密
//        jwtAccessTokenConverter.setSigningKey("oauth2");
        // 非对称加密的解密
        ClassPathResource resource = new ClassPathResource("public.txt");
        String publicKey = null;
        try {
            // 都成string字符串
            publicKey = FileUtil.readString(resource.getFile(), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 设置到转换器里面
        jwtAccessTokenConverter.setVerifierKey(publicKey);
        return jwtAccessTokenConverter;
    }
    /**
     * 让资源服务器 从tokenStore里面拿到token
     *
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
    }

    /***
     * Http安全配置，对每个到达系统的http请求链接进行校验
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //所有请求必须认证通过
        http.authorizeRequests()
                //下边的路径放行
                .antMatchers(
                        "/tokenInfo"). //配置地址放行
                permitAll()
                .anyRequest().
                authenticated();    //其他地址需要认证授权
    }





}
