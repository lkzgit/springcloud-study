//package com.demo.oauth.config.heima;
//
//
//import com.demo.oauth.constant.AuthConstant;
//import com.demo.oauth.domain.sxt.MemUser;
//import com.demo.oauth.domain.sxt.SysUser;
//import com.demo.oauth.util.UserJwt;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.provider.ClientDetails;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.List;
//
///*****
// * 自定义授权认证类
// */
//@Service("userDetailsServiceImpl")
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Resource
//    ClientDetailsService clientDetailsService;
//
//    @Resource
//    PasswordEncoder passwordEncoder;
//
////   @Resource
////    private UserFeign userFeign;
//
//    /****
//     * 自定义授权认证
//     * @param username
//     * @return
//     * @throws UsernameNotFoundException
//     */
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        //取出身份，如果身份为空说明没有认证
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
////        if(authentication==null){
////            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
////            if(clientDetails!=null){
////                //秘钥
////                String clientSecret = clientDetails.getClientSecret();
////                //静态方式
////                return new User(username,new BCryptPasswordEncoder().encode(clientSecret), AuthorityUtils.commaSeparatedStringToAuthorityList(""));
////                //数据库查找方式
////               // return new User(username,clientSecret, AuthorityUtils.commaSeparatedStringToAuthorityList(""));
////            }
////        }
////
////        if (StringUtils.isEmpty(username)) {
////            return null;
////        }
////
////        //根据用户名查询用户信息
////       String password = new BCryptPasswordEncoder().encode("changgou");
////        // 通过数据库去查询用户通过密码授权
////        //String password = userFeign.findById(username).getData().getPassword();
////        //创建User对象(权限对象)
////        String permissions = "goods_list,seckill_list,user,admin";
////        UserJwt userDetails = new UserJwt(username,password,AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
////        return userDetails;
////    }
//        @Override
//        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//            // 1.拿到request
//            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            HttpServletRequest request = requestAttributes.getRequest();
//            // 2. 拿到header
//            String header = request.getHeader(AuthConstant.LOGIN_TYPE);
//            if (!StringUtils.hasText(header)) {
//                // 搞一个默认值 就是前台用户
//                header = AuthConstant.MEMBER;
//            }
//            switch (header) {
//                case AuthConstant.MEMBER:
//                    // 会员用户 查询会员响应的数据库
//                    List<String> memPer=new ArrayList<>();
//                  //  memPer.add("test"); //模拟根据当前用户查询出来的权限
//                    MemUser memUser=new MemUser("test",passwordEncoder.encode("test"),memPer);
//                    return memUser;
//                case AuthConstant.SYS_USER:
//                    // 系统用户查询系统相对应的系统用户
//                    List<String> rolesPer=new ArrayList<>();
//                    rolesPer.add("test");
//                    rolesPer.add("admin");
//                    SysUser sysUser = new SysUser("admin",passwordEncoder.encode("admin"),rolesPer);
//                    return sysUser;
//            }
//            return null;
//        }
//
//}
