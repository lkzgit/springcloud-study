package com.demo.oauth.config.sxt.service;

import com.demo.oauth.constant.AuthConstant;
import com.demo.oauth.domain.sxt.MemUser;
import com.demo.oauth.domain.sxt.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author lkz
 * @date 2021/12/16 20:15
 * @description
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {


    @Autowired
    PasswordEncoder passwordEncoder;

//    //模拟数据库
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Map<String, User> userMap = new HashMap<>();
//        List<SimpleGrantedAuthority> roles = Arrays.asList(new SimpleGrantedAuthority("admin"));
//        User admin = new User("admin", passwordEncoder.encode("admin"), roles);
//        userMap.put("admin", admin);
//        List<SimpleGrantedAuthority> roles2 = Arrays.asList(new SimpleGrantedAuthority("test"));
//        User test = new User("test", passwordEncoder.encode("test"), roles2);
//        userMap.put("test", test);
//        return userMap.get(username);
//
//    }

    /**
     * 和数据库结合的真实登录
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1.拿到request
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // 2. 拿到header
        String header = request.getHeader(AuthConstant.LOGIN_TYPE);
        if (!StringUtils.hasText(header)) {
            // 搞一个默认值 就是前台用户
            header = AuthConstant.MEMBER;
        }
        switch (header) {
            case AuthConstant.MEMBER:
                // 会员用户 查询会员响应的数据库
                List<String> memPer=new ArrayList<>();
              //  memPer.add("test"); //模拟根据当前用户查询出来的权限
                MemUser memUser=new MemUser("test",passwordEncoder.encode("test"),memPer);
                return memUser;
            case AuthConstant.SYS_USER:
                // 系统用户查询系统相对应的系统用户
                List<String> rolesPer=new ArrayList<>();
                rolesPer.add("test");
                rolesPer.add("admin");
                SysUser sysUser = new SysUser("admin",passwordEncoder.encode("admin"),rolesPer);
                return sysUser;
        }
        return null;
    }


}
