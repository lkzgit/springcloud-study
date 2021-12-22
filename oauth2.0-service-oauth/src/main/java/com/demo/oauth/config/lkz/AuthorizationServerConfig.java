//package com.demo.oauth.config.lkz;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//
///**
// * @author lkz
// * @version 1.0.0
// * @ClassName AuthorizationServerConfig.java
// * @Description 授权服务中心
// * @createTime 2021年12月22日 21:43:00
// */
//@Configuration
//@EnableAuthorizationServer //开启授权服务
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//
//
//    /**
//     * 用户认证 Manager
//     */
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(authenticationManager);
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        oauthServer.checkTokenAccess("isAuthenticated()")
////            .tokenKeyAccess("permitAll()")
//        ;
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("lkz").secret(passwordEncoder().encode("lkz")) // Client 账号、密码。
//                .authorizedGrantTypes("authorization_code") // 授权码模式
//                .redirectUris("http://localhost") // 配置回调地址，选填。
//                .scopes("read_userinfo", "read_contacts") // 可授权的 Scope
////                .and().withClient() // 可以继续配置新的 Client
//        ;
//    }
//
//
//}
