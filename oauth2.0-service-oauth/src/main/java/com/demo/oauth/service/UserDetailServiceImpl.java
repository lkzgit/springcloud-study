package com.demo.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author lkz
 * @ClassName: UserDetailServiceImpl
 * @description: TODO
 * @date 2021/12/28 15:44
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        // 模拟一个数据库
        HashMap<String, User> userHashMap = new HashMap<>(4);
        ArrayList<SimpleGrantedAuthority> testAuths = new ArrayList<>(4);
        testAuths.add(new SimpleGrantedAuthority("test:query"));
        testAuths.add(new SimpleGrantedAuthority("test:find"));

        User test = new User("test", passwordEncoder.encode("test"), testAuths);

        ArrayList<SimpleGrantedAuthority> adminAuths = new ArrayList<>(4);
        adminAuths.add(new SimpleGrantedAuthority("sys:add"));
        adminAuths.add(new SimpleGrantedAuthority("sys:del"));
        User admin = new User("admin", passwordEncoder.encode("admin"), adminAuths);

        userHashMap.put("user", test);
        userHashMap.put("sysUser", admin);

        // 我们可以在请求头里面加一个字段
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // 拿到header
        String login_type = request.getHeader("login_type");
        if (!StringUtils.hasText(login_type)) {
            // 也可以直接 return null
            login_type = "user";
        }
        switch (login_type) {
            case "user":
                // 如果是前台 就查询前台的表
                return userHashMap.get(login_type);
            case "sysUser":
                // 如果后台 我们就查询后台的表
                return userHashMap.get(login_type);
        }
        return null;
    }
}
