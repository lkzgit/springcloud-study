package com.demo.oauth.config.sxt;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;


import javax.annotation.Resource;

/**
 * @author lkz
 * @version 1.0.0
 * @ClassName AuthorizationRedisConfig.java
 * @Description TODO
 * @createTime 2021年12月15日 23:00:00
 */
@Configuration
@EnableAuthorizationServer //开始授权服务器
//@EnableResourceServer  //开始资源服务器 表示改服务作为一个资源服务器 所有请求都必须携带token
@EnableGlobalMethodSecurity(prePostEnabled = true) //security 里面的注解，所有的 方法级别的访问需要验证权限
public class AuthorizationRedisConfig extends AuthorizationServerConfigurerAdapter {

    //注入 redis 的连接工厂
    @Resource
    private RedisConnectionFactory redisConnectionFactory;
    /**
     * 注入密码管理器 授权方式 支持密码方式 必须使用
     */
    @Resource
    AuthenticationManager authenticationManager;

//    @Autowired
//    private DataSource dataSource;



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
     *   authorizedGrantTypes: 授权方式  验证码 授权码 静默授权 客户端授权
     */
    //方式一
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //创建一个基本的第三方应用，第三方应用少的时候，就放在内存中，多的就使用 jdbc
        clients.inMemory()
                .withClient("web") // 第三方应用
                .secret(passwordEncoder().encode("web-secret")) //密码
                .scopes("web-scopes") //作用域
                .authorizedGrantTypes("authorization_code") //授权方式  验证码
                .redirectUris("https://www.baidu.com") // 访问成功后的跳转地址
                .accessTokenValiditySeconds(7200) //token的有效期
                .and()
                .withClient("ios")
                .secret(passwordEncoder().encode("ios-secret"))
                .scopes("ios-scopes")
                .authorizedGrantTypes("implicit") //静默授权
                .redirectUris("https://www.baidu.com")
                .accessTokenValiditySeconds(7200)
                .and()
                .withClient("api")
                .secret(passwordEncoder().encode("api-secret"))
                .scopes("api-scopes")
                .authorizedGrantTypes("password") //密码模式
                .redirectUris("https://www.baidu.com")
                .accessTokenValiditySeconds(7200)
                .and()
                .withClient("client")
                .secret(passwordEncoder().encode("client-secret"))
                .scopes("client-scopes")
                .authorizedGrantTypes("client_credentials")
                .redirectUris("https://www.baidu.com")
                .accessTokenValiditySeconds(7200);


    }
    /**
     * 从数据库读取clientDetails相关配置
     * 有InMemoryClientDetailsService 和 JdbcClientDetailsService 两种方式选择
     */
//    @Bean
//    public ClientDetailsService clientDetails() {
//        return new JdbcClientDetailsService(dataSource);
//    }


    /**
     * 方式二 client存储方式，此处使用jdbc存储 第三方应用的配置，只有配置了第三方应用，才能访问授权服务器
     */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.withClientDetails(clientDetails());
//    }

    /**
     * 方式一
     * token的存储方式 这里表示存储在redis中
     * @param
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore()) //token存储在redis
                .authenticationManager(authenticationManager); // 密码模式下的认证管理器 必须
    }
}
