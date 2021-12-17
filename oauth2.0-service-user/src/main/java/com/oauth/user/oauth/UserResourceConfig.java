package com.oauth.user.oauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author lkz
 * @date 2021/12/17 15:38
 * @description 资源服务器
 */
@EnableResourceServer //开启资源服务器 下面的url都被保护起来
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserResourceConfig extends ResourceServerConfigurerAdapter {


}
