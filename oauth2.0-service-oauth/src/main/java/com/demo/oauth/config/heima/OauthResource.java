//package com.demo.oauth.config.heima;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//
///**
// * @author lkz
// * @ClassName: OauthResource
// * @description: TODO
// * @date 2021/12/21 15:13
// */
//@Configuration
//@EnableResourceServer  //开始资源服务器 表示改服务作为一个资源服务器 所有请求都必须携带token
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class OauthResource extends ResourceServerConfigurerAdapter {
//
//
//
//    @Autowired
//    private TokenStore jwtTokenStore;
//
//
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.tokenStore(jwtTokenStore);
//    }
//
//    /**
//     * 配置受OAuth2保护的URL资源。
//     * 注意:必须配置sessionManagement(),否则访问受护资源请求不会被OAuth2的拦截器
//     * 		ClientCredentialsTokenEndpointFilter与OAuth2AuthenticationProcessingFilter拦截,
//     * 		也就是说,没有配置的话,资源没有受到OAuth2的保护。
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//
////        http
////                // Since we want the protected resources to be accessible in the UI as well we need
////                // session creation to be allowed (it's disabled by default in 2.0.6)
////                //另外，如果不设置，那么在通过浏览器访问被保护的任何资源时，每次是不同的SessionID，并且将每次请求的历史都记录在OAuth2Authentication的details的中
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
////                .and()
////                .requestMatchers()
////                .antMatchers("/user","/res/**")
////                .and()
////                .authorizeRequests()
////                .antMatchers("/user","/res/**")
////                .authenticated();
//
//        //所有请求必须认证通过
//        http
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .and()
//                .authorizeRequests()
//                //下边的路径放行
//                .antMatchers(
//                        "/testOauth"). //配置地址放行
//                permitAll()
//                .anyRequest().
//                authenticated();
//    }
//
//}
