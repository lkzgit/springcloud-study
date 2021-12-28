package com.demo.oauth.config;

import com.demo.oauth.config.jwt.JwtTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lkz
 * @ClassName: AuthorizationConfig
 * @description: 授权服务
 * @date 2021/12/28 15:43
 */
@Configuration
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("jwtTokenStore")
    TokenStore tokenStore;

//    @Bean
//    public TokenStore tokenStore() { return new RedisTokenStore(redisConnectionFactory); }

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private JwtTokenEnhancer jwtTokenEnhancer;

    /*
    第三方授权应用
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("oauth")
                .secret(passwordEncoder.encode("oauth"))
                .scopes("all")
                .authorizedGrantTypes("password","authorization_code","refresh_token")
                .accessTokenValiditySeconds(3600)
                .redirectUris("http://localhost");

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        //配置JWT的内容增强器
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(delegates);

        //使用 redis 做 token 的存储
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(enhancerChain)
                .reuseRefreshTokens(false)  //refresh_token是否重复使用
                .allowedTokenEndpointRequestMethods(HttpMethod.GET,HttpMethod.POST)

                ;

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许表单认证
        security.allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                // 配置校验token需要带入clientId 和clientSeret配置
                .checkTokenAccess("isAuthenticated()");
    }

}
