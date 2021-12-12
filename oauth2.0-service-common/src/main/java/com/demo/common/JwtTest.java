package com.demo.common;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lkz
 * @version 1.0.0
 * @ClassName JwtTest.java
 * @Description TODO
 * @createTime 2021年12月12日 12:42:00
 */
public class JwtTest {

    /****
     * 创建Jwt令牌
     */
    @Test
    public void testCreateJwt(){
        JwtBuilder builder= Jwts.builder()
                .setId("888")             //设置唯一编号
                .setSubject("小白")       //设置主题  可以是JSON数据
                .setIssuedAt(new Date())  //设置签发日期
              //  .setExpiration()  //设置过期时间
                .signWith(SignatureAlgorithm.HS256,"haha");//设置签名 使用HS256算法，并设置SecretKey(字符串)
        //自定义数据
        Map<String,Object> info=new HashMap<>();
        info.put("name","xiaohei");
        builder.addClaims(info);

        //构建 并返回一个字符串
        System.out.println( builder.compact() );
    }

    /***
     * 解析Jwt令牌数据
     */
    @Test
    public void testParseJwt(){
        String compactJwt="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE2MzkyODQzODF9.4-HSv0uPB8yfHrldRvsBonj8ssGVcpZ3-MC5gjeGgiE";
        Claims claims = Jwts.parser().
                setSigningKey("haha").
                parseClaimsJws(compactJwt).
                getBody();
        System.out.println(claims);
    }

}
