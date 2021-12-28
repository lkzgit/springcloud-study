package com.demo.oauth;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableEurekaClient
@EnableAuthorizationServer //授权服务注解
public class OauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class,args);
    }

    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * @PreAuthorize:
     * hasRole，  @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
     *      对应 public final boolean hasRole(String role) 方法，含义为必须含有某角色（非ROLE_开头），
     *      如有多个的话，必须同时具有这些角色，才可访问对应资源。
     * hasAnyRole，对应 public final boolean hasAnyRole(String... roles) 方法，
     *      含义为只具有有某一角色（多多个角色的话，具有任意一个即可），即可访问对应资源。
     * hasAuthority，对应 public final boolean hasAuthority(String authority) 方法，
     *      含义同 hasRole，不同点在于这是权限，而不是角色，区别就在于权限往往带有前缀（如默认的ROLE_），而角色只有标识。
     * hasAnyAuthority，对应 public final boolean hasAnyAuthority(String... authorities) 方法，
     *      含义同 hasAnyRole，不同点在于这是权限，而不是角色，区别就在于权限往往带有前缀（如默认的ROLE_），而角色只有标识
     * permitAll，对应 public final boolean permitAll() 方法，含义为允许所有人（可无任何权限）访问。
     * denyAll，对应 public final boolean denyAll() 方法，含义为不允许任何（即使有最大权限）访问。
     * isAnonymous，对应 public final boolean isAnonymous() 方法，含义为可匿名（不登录）访问。
     * isAuthenticated，对应 public final boolean isAuthenticated() 方法，含义为身份证认证后访问。
     * isRememberMe，对应 public final boolean isRememberMe() 方法，含义为记住我用户操作访问。
     * isFullyAuthenticated，对应 public final boolean isFullyAuthenticated() 方法，含义为非匿名且非记住我用户允许访问。
     *
     * AuthorizationEndpoint 服务于认证请求。默认 URL： /oauth/authorize 。
     * TokenEndpoint 服务于访问令牌的请求。默认 URL： /oauth/token
     *
     */


}
