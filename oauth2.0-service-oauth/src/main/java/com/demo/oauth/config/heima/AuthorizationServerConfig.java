package com.demo.oauth.config.heima;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.annotation.Resource;
import javax.sql.DataSource;

import java.security.KeyPair;


@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    //数据源，用于从数据库获取数据进行认证操作，测试可以从内存中获取
    @Resource
    private DataSource dataSource;
    @Resource
    PasswordEncoder passwordEncoder;
    //jwt令牌转换器
    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    //SpringSecurity 用户自定义授权认证类
    @Resource
    UserDetailsService userDetailsService;
    //授权认证管理器
    @Resource
    AuthenticationManager authenticationManager;

    @Resource
    private CustomUserAuthenticationConverter customUserAuthenticationConverter;

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    /***
     * 采用BCryptPasswordEncoder对密码进行编码
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /***
     * 客户端信息配置
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
      // clients.jdbc(dataSource).clients(clientDetails());
      clients.inMemory()
                .withClient("changgou")          //客户端id
                .secret(passwordEncoder.encode("changgou"))                     //秘钥
                .redirectUris("http://localhost")       //重定向地址
                .accessTokenValiditySeconds(3600)          //访问令牌有效期
                .refreshTokenValiditySeconds(3600)         //刷新令牌有效期
                .authorizedGrantTypes(
                        "authorization_code",          //根据授权码生成令牌
                        "client_credentials",          //客户端认证
                        "refresh_token",                //刷新令牌
                        "password")                     //密码方式认证
                .scopes("app");                         //客户端范围，名称自定义，必填
    }

    /***
     * 授权服务器端点配置
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.accessTokenConverter(jwtAccessTokenConverter)
                .authenticationManager(authenticationManager)//认证管理器
                .tokenStore(tokenStore())                     //令牌存储
                .userDetailsService(userDetailsService);     //用户信息service
    }

    /***
     * 授权服务器的安全配置
     * @param oauthServer
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.allowFormAuthenticationForClients()
                .passwordEncoder(new BCryptPasswordEncoder())
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }


    //读取密钥的配置
    @Bean("keyProp")
    public KeyProperties keyProperties(){
        return new KeyProperties();
    }

    @Resource(name = "keyProp")
    private KeyProperties keyProperties;

    //客户端配置
//    @Bean
//    public ClientDetailsService clientDetails() {
//        return new JdbcClientDetailsService(dataSource);
//    }



    /****
     * JWT令牌转换器
     * @param
     * @return
     */
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter(CustomUserAuthenticationConverter customUserAuthenticationConverter) {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        KeyPair keyPair = new KeyStoreKeyFactory(
//                keyProperties.getKeyStore().getLocation(),                          //证书路径 changgou.jks
//                keyProperties.getKeyStore().getSecret().toCharArray())              //证书秘钥 changgouapp
//                .getKeyPair(
//                        keyProperties.getKeyStore().getAlias(),                     //证书别名 changgou
//                        keyProperties.getKeyStore().getPassword().toCharArray());   //证书密码 changgou
//        converter.setKeyPair(keyPair);
//        //配置自定义的CustomUserAuthenticationConverter
//        DefaultAccessTokenConverter accessTokenConverter = (DefaultAccessTokenConverter) converter.getAccessTokenConverter();
//        accessTokenConverter.setUserTokenConverter(customUserAuthenticationConverter);
//        return converter;
//    }
        //功能同上
        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
            // 需要一个sign签名 这叫对称加密
            //jwtAccessTokenConverter.setSigningKey("oauth2");
            // 非对称加密了
            // 1. 读进来
            ClassPathResource resource = new ClassPathResource("changgou.jks");
            // 2. 创建钥匙工厂
            KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(resource, "changgou".toCharArray());
            // 3.通过别名cxs-jwt拿到私钥
            KeyPair keyPair = keyStoreKeyFactory.getKeyPair("changgou");
            // 4.设置进去
            jwtAccessTokenConverter.setKeyPair(keyPair);
            return jwtAccessTokenConverter;
        }



}

