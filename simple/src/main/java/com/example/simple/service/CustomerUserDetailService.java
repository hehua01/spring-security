package com.example.simple.service;

import com.example.simple.entity.SecurityUser;
import com.example.simple.entity.User;
import com.example.simple.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date 2022/3/2 15:52
 * @Description
 */
@Service
public class CustomerUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User sysUser = userRepository.getUserByUsername(s);
        if(null == sysUser){
            throw new UsernameNotFoundException("该用户不存在！");
        }

        //将数据库形式的roles转换为UserDetails识别的权限集
        //commaSeparatedStringToAuthorityList方法的实现是按逗号分隔,我们也可以借助SimpleGrantedAuthority类提供其它实现
        List<GrantedAuthority> list = AuthorityUtils.commaSeparatedStringToAuthorityList(sysUser.getRoles());
        SecurityUser securityUser = new SecurityUser(sysUser);
        securityUser.setAuthorityList(list);
        // 认证信息写入 SecurityContextHolder中
        SecurityContextHolder.getContext().
                setAuthentication(new UsernamePasswordAuthenticationToken(securityUser, null, list));

        return securityUser;
    }
}