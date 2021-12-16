package com.demo.oauth.config.sxt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lkz
 * @date 2021/12/16 20:15
 * @description
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {


    @Autowired
    PasswordEncoder passwordEncoder;

    //模拟数据库
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String, User> userMap = new HashMap<>();
        List<SimpleGrantedAuthority> roles = Arrays.asList(new SimpleGrantedAuthority("admin"));
        User admin = new User("admin", passwordEncoder.encode("admin"), roles);
        userMap.put("admin", admin);
        List<SimpleGrantedAuthority> roles2 = Arrays.asList(new SimpleGrantedAuthority("test"));
        User test = new User("test", passwordEncoder.encode("test"), roles2);
        userMap.put("test", test);
        return userMap.get(username);

    }
}
