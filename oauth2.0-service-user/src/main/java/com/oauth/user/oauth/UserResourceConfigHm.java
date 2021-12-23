//package com.oauth.user.oauth;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//import java.util.stream.Collectors;
//
///**
// * @author lkz
// * @date 2021/12/17 15:38
// * @description 资源服务器 处理梅西验证都去授权服务器去验证问题
// */
//@EnableResourceServer //开启资源服务器 下面的url都被保护起来
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class UserResourceConfigHm extends ResourceServerConfigurerAdapter {
//
//    //公钥
//    private static final String PUBLIC_KEY = "public.key";
//
//    @Autowired
//    TokenStore tokenStore;
//
//
//    /***
//     * 定义JwtTokenStore
//     * @param jwtAccessTokenConverter
//     * @return
//     */
//    @Bean
//    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
//        return new JwtTokenStore(jwtAccessTokenConverter);
//    }
//
//    /***
//     * 定义JJwtAccessTokenConverter
//     * @return
//     */
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setVerifierKey(getPubKey());
//        return converter;
//    }
//
//
//    /**
//     * 获取非对称加密公钥 Key
//     * @return 公钥 Key
//     */
//    private String getPubKey() {
//        Resource resource = new ClassPathResource(PUBLIC_KEY);
//        try {
//            InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
//            BufferedReader br = new BufferedReader(inputStreamReader);
//            return br.lines().collect(Collectors.joining("\n"));
//        } catch (IOException ioe) {
//            return null;
//        }
//    }
//
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        resources.resourceId("changgou")//资源 id
//                .tokenStore(tokenStore)
//                .stateless(true);
//    }
//
//    /***
//     * Http安全配置，对每个到达系统的http请求链接进行校验
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        //所有请求必须认证通过
//        http.authorizeRequests()
//                //下边的路径放行
//                .antMatchers(
//                        "/tokenInfo","/oauth-service/**"). //配置地址放行
//                permitAll()
//                .anyRequest().
//                authenticated();    //其他地址需要认证授权
//    }
//
//
//
//
//
//}
