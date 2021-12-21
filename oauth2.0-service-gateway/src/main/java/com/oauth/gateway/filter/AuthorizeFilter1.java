package com.oauth.gateway.filter;

import com.demo.common.util.JwtUtil;
import com.demo.common.util.ServletUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.netty.handler.codec.http.cookie.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServerResponse;
import sun.net.httpserver.AuthFilter;

import java.io.IOException;

/**
 * 全局过滤器 :用于鉴权(获取令牌 解析 判断)
 *
 */
@Component
public class AuthorizeFilter1 implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(AuthorizeFilter1.class);
    private static final String AUTHORIZE_TOKEN = "Authorization";


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //1.获取请求对象
        ServerHttpRequest request = exchange.getRequest();

        //2.获取响应对象
        ServerHttpResponse response = exchange.getResponse();
        //获取请求的URI
        String path = request.getURI().getPath();

        //如果是登录、
        if (path.startsWith("/user/login/tologin")) {
            //放行
            Mono<Void> filter = chain.filter(exchange);
            return filter;
        }


        //4.1 从头header中获取令牌数据
        String token = request.getHeaders().getFirst(AUTHORIZE_TOKEN);
        // boolean true;令牌在头文件中 false:令牌不在头文件中
        boolean hasToken=true;


        if(StringUtils.isEmpty(token)){
            //4.3 从请求参数中获取令牌数据
            token= request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
         //   hasToken=false;
        }
        //从cook中获取token
        if(StringUtils.isEmpty(token)){
            HttpCookie httpCookie = request.getCookies().getFirst(AUTHORIZE_TOKEN);
            if(httpCookie!=null){
            token=  httpCookie.getValue();
            }

        }
        // 如果没有令牌 则拦截
        if (StringUtils.isEmpty(token)) {
            //设置方法不允许被访问，405错误代码
//            response.setStatusCode(HttpStatus.METHOD_NOT_ALLOWED);
//            return response.setComplete();
            return unauthorizedResponse(exchange,"token认证未通过");
        }

        //如果有令牌判断令牌是否有效
        try {
            Claims claims = JwtUtil.parseJWT(token);
            //将令牌数据添加到头文件中
            request.mutate().header(AUTHORIZE_TOKEN,claims.toString());
        } catch (Exception e) {
            e.printStackTrace();
            //解析失败，响应401错误
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        //添加头信息 传递给 各个微服务()
        // request.mutate().header(AUTHORIZE_TOKEN,"Bearer "+ token);


        //有效放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String msg)
    {
        log.error("getWay[鉴权异常处理]请求路径:{}", exchange.getRequest().getPath());
        return ServletUtils.webFluxResponseWriter(exchange.getResponse(), HttpStatus.UNAUTHORIZED, msg,0);
    }





}
