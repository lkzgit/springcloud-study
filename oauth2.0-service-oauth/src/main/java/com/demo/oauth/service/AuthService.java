package com.demo.oauth.service;

import com.demo.oauth.util.AuthToken;

/**
 * @author lkz
 * @version 1.0.0
 * @ClassName AuthService.java
 * @Description 授权认证方法
 * @createTime 2021年12月19日 23:02:00
 */
public interface AuthService {

    /***
     * 授权认证方法
     */
    AuthToken login(String username, String password, String clientId, String clientSecret);

}
