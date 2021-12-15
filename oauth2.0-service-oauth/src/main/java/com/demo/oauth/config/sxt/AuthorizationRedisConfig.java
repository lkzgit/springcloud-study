package com.demo.oauth.config.sxt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author lkz
 * @version 1.0.0
 * @ClassName AuthorizationRedisConfig.java
 * @Description TODO
 * @createTime 2021年12月15日 23:00:00
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationRedisConfig extends AuthorizationServerConfigurerAdapter {

    //注入 redis 的连接工厂
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    /***
     * 描述: 创建一个 redis 的 tokenStore 存放颁发的 token
     * */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }
    /*
    注入密码加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /*** '
     * 描述: 第三方应用的配置，只有配置了第三方应用，才能访问授权服务器
     * @param clients:
     */
    //基于内存
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //创建一个基本的第三方应用，第三方应用少的时候，就放在内存中，多的就使用 jdbc
        clients.inMemory()
                .withClient("web") // 第三方应用
                .secret("web-secret") //密码
                .scopes("web-scopes") //作用域
                .authorizedGrantTypes("authorization_code") //授权方式  验证码 授权码 静默授权 客户端授权
                .redirectUris("https://www.baidu.com") // 访问成功后的跳转地址
                .accessTokenValiditySeconds(7200); //token的有效期

    }

    /**
     * token的存储方式 这里表示存储在redis中
     * @param
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore());
    }
}
