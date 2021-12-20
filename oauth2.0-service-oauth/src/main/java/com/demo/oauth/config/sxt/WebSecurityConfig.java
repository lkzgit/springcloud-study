package com.demo.oauth.config.sxt;

import com.demo.oauth.config.sxt.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lkz
 * @version 1.0.0
 * @ClassName WebSecurityConfig.java
 * @Description TODO
 * @createTime 2021年12月15日 23:41:00
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    /**
     * http安全配置
     * @param http http安全对象
     * @throws Exception http安全异常信息
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //设置所有的请求都要在登陆后才能访问
        http.authorizeRequests().anyRequest().authenticated();
        //给一个登录页面
        http.formLogin();
    }

    //模拟在内存中创建一个用户
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//                auth.inMemoryAuthentication() //内存方式设置一个用户
//                .withUser("lkz")
//                .password(passwordEncoder.encode("lkz"))
//                .authorities("sys:admin");
//    }
    //从数据库中查找
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    /**
     * 密码模式下 必须需要这个认证管理器 不使用密码模式可以不用这个
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }




//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(
//                "/error",
//                "/static/**",
//                "/v2/api-docs/**",
//                "/swagger-resources/**",
//                "/webjars/**",
//                "/favicon.ico"
//        );
//    }


}
