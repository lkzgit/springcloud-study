//package com.demo.oauth.config.sxt;
//
//import com.demo.oauth.config.sxt.service.UserDetailServiceImpl;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
//
//import javax.annotation.Resource;
//import java.security.KeyPair;
//
///**
// * @author lkz
// * @version 1.0.0
// * @ClassName AuthorizationJWTConfig.java
// * @Description TODO 引入JWT
// * @createTime 2021年12月17日 23:54:00
// * securedEnabled = true,prePostEnabled = true
// */
//@Configuration
//@EnableAuthorizationServer //开始授权服务器
//@EnableGlobalMethodSecurity(prePostEnabled = true) //security 里面的注解，所有的 方法级别的访问需要验证权限
//public class AuthorizationJWTConfig extends AuthorizationServerConfigurerAdapter {
//
//
//    /**
//     * 注入密码管理器 授权方式 支持密码方式 必须使用
//     */
//    @Resource
//    AuthenticationManager authenticationManager;
//    @Resource
//    UserDetailServiceImpl userDetailService;
//
//    /***
//     * 描述: 创建一个 redis 的 tokenStore 存放颁发的 token
//     * */
//    @Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(jwtAccessTokenConverter());
//    }
//
//
//    /**
//     * jwt的token转换器
//     * 把用户信息转成jwt的形式颁发出去
//     *
//     * @return
//     */
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
//        // 需要一个sign签名 这叫对称加密
//        //jwtAccessTokenConverter.setSigningKey("oauth2");
//        // 非对称加密了
//        // 1. 读进来
//        ClassPathResource resource = new ClassPathResource("changgou.jks");
//        // 2. 创建钥匙工厂
//        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(resource, "changgou".toCharArray());
//        // 3.通过别名cxs-jwt拿到私钥
//        KeyPair keyPair = keyStoreKeyFactory.getKeyPair("changgou");
////        // 4.设置进去
//        jwtAccessTokenConverter.setKeyPair(keyPair);
//        return jwtAccessTokenConverter;
//    }
//
//
//    /*
//    注入密码加密
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    /*** '
//     * 描述: 第三方应用的配置，只有配置了第三方应用，才能访问授权服务器
//     * @param clients:
//     *   authorizedGrantTypes: 授权方式  验证码 授权码 静默授权 客户端授权
//     * http://localhost:9999/oauth/authorize?response_type=code&client_id=web&state= sxt&redirect_uri=https://www.baidu.com
//     * http://localhost:9999/oauth/token?grant_type=authorization_code&code=8GirYx&redirec t_uri=https://www.baidu.com
//    http://localhost:9999/oauth/authorize?response_type=token&client_id=ios&state= sxt&redirect_uri=https://www.baidu.com
//     */
//    //方式一
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        //创建一个基本的第三方应用，第三方应用少的时候，就放在内存中，多的就使用 jdbc
//        clients.inMemory()
//                .withClient("web") // 第三方应用
//                .secret(passwordEncoder().encode("web-secret")) //密码
//                .scopes("web-scopes") //作用域
//                .authorizedGrantTypes("authorization_code") //授权方式  验证码
//                .redirectUris("https://www.baidu.com") // 访问成功后的跳转地址
//                .accessTokenValiditySeconds(7200) //token的有效期
//                .and()
//                .withClient("ios")
//                .secret(passwordEncoder().encode("ios-secret"))
//                .scopes("ios-scopes")
//                .authorizedGrantTypes("implicit") //静默授权
//                .redirectUris("https://www.baidu.com")
//                .accessTokenValiditySeconds(7200)
//                .and()
//                .withClient("api")
//                .secret(passwordEncoder().encode("api-secret"))
//                .scopes("api-scopes")
//                .authorizedGrantTypes("password","refresh_token") //密码模式
//                .redirectUris("https://www.baidu.com")
//                .accessTokenValiditySeconds(7200)
//                .and()
//                .withClient("client")
//                .secret(passwordEncoder().encode("client-secret"))
//                .scopes("client-scopes")
//                .authorizedGrantTypes("client_credentials")
//                .redirectUris("https://www.baidu.com")
//                .accessTokenValiditySeconds(7200)
//                .and()
//                .withClient("changgou")
//                .secret(passwordEncoder().encode("changgou"))
//                .scopes("all")
//                .authorizedGrantTypes("client_credentials","password","refresh_token",
//                        "authorization_code","implicit")
//                .redirectUris("http://localhost")
//                .accessTokenValiditySeconds(7200);
//
//
//    }
//
//    /**
//     * 方式一
//     * token的存储方式 这里表示存储在redis中
//     * 需要暴露授权服务给token的存储
//     * 暴露授权服务器给认证管理器
//     * 暴露授权服务给token转换器
//     *
//     * @param
//     * @throws Exception
//     */
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints
//                .tokenStore(tokenStore()) //token存储在redis
//                .authenticationManager(authenticationManager)// 密码模式下的认证管理器 必须
//                .accessTokenConverter(jwtAccessTokenConverter())
//        .userDetailsService(userDetailService);
//    }
//
//    /**
//     * AuthorizationServerSecurityConfigurer：用来配置令牌端点(Token Endpoint)的安全约束，在
//     * @param security
//     */
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) {
//        security.tokenKeyAccess("permitAll()") //(1) tokenkey这个endpoint当使用JwtToken且使用非对称加密时，
//                // 资源服务用于获取公钥而开放的，这里指这个 endpoint完全公开。
//            .checkTokenAccess("permitAll()") //(2) checkToken这个endpoint完全公开
//            .allowFormAuthenticationForClients(); //(3); 允许表单认证
//    }
//
//
//}
