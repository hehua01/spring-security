package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.entity.SecurityUser;
import com.example.springsecurityjwt.entity.User;
import com.example.springsecurityjwt.repo.UserRepository;
import com.example.springsecurityjwt.util.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Date 2022/3/7 19:52
 * @Description
 */
@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    // 登录
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    // 注册
    public User register(User userToAdd) {
        final String username = userToAdd.getUsername();
        if(userRepository.getUserByUsername(username) != null ) {
            return null;
        }

        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(rawPassword);
        return userRepository.save(userToAdd);
    }
}
