package com.demo.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lkz
 * @ClassName: YDLoginController
 * @description: 登录获取token
 * @date 2021/12/24 10:03
 */
@RestController
public class YDLoginController {

    @Autowired
    OAuth2ClientProperties oAuth2ClientProperties;
    @Value("${security.oauth2.client.access-token-uri}")
    String accessTokenUri;


    @PostMapping("/login")
    public OAuth2AccessToken login(@RequestParam("username") String username,
                                   @RequestParam("password") String password) {
        // <1> 创建 ResourceOwnerPasswordResourceDetails 对象
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setAccessTokenUri(accessTokenUri);
        resourceDetails.setClientId(oAuth2ClientProperties.getClientId());
        resourceDetails.setClientSecret(oAuth2ClientProperties.getClientSecret());
        resourceDetails.setUsername(username);
        resourceDetails.setPassword(password);
        // <2> 创建 OAuth2RestTemplate 对象
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails);
        restTemplate.setAccessTokenProvider(new ResourceOwnerPasswordAccessTokenProvider());
        // <3> 获取访问令牌
        return restTemplate.getAccessToken();
    }

}
