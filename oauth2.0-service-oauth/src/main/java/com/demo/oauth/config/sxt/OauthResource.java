//package com.demo.oauth.config.sxt;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//
//import javax.annotation.Resource;
//
///**
// * @author lkz
// * @ClassName: OauthResource
// * @description: TODO
// * @date 2021/12/21 15:13
// */
//@Configuration
//@EnableResourceServer  //开始资源服务器 表示改服务作为一个资源服务器 所有请求都必须携带token
//public class OauthResource extends ResourceServerConfigurerAdapter {
//
//
//
//    @Autowired
//   // @Qualifier("redisTokenStore")
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
//    	/*
//    	 注意：
//    	 1、必须先加上：.requestMatchers().antMatchers(...)，表示对资源进行保护，也就是说，在访问前要进行OAuth认证。
//    	 2、接着：访问受保护的资源时，要具有哪里权限。
//    	 ------------------------------------
//    	 否则，请求只是被Security的拦截器拦截，请求根本到不了OAuth2的拦截器。
//    	 ------------------------------------
//    	 requestMatchers()部分说明：
//    	 Invoking requestMatchers() will not override previous invocations of ::
//    	 mvcMatcher(String)}, requestMatchers(), antMatcher(String), regexMatcher(String), and requestMatcher(RequestMatcher).
//    	 */
//        http
//                // Since we want the protected resources to be accessible in the UI as well we need
//                // session creation to be allowed (it's disabled by default in 2.0.6)
//                //另外，如果不设置，那么在通过浏览器访问被保护的任何资源时，每次是不同的SessionID，并且将每次请求的历史都记录在OAuth2Authentication的details的中
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .and()
//                .requestMatchers()
//                .antMatchers("/user","/res/**")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/user","/res/**")
//                .authenticated();
//    }
//
//}
