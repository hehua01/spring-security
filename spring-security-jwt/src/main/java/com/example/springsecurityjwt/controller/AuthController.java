package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.entity.User;
import com.example.springsecurityjwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

/**
 * @Date 2022/3/7 19:56
 * @Description
 */
@RestController
@RequestMapping("authentication")
public class AuthController {
    @Autowired
    private AuthService authService;

    // 登录
    @PostMapping(value = "login")
    public String createToken(String username, String password) throws AuthenticationException {
        return authService.login(username, password); // 登录成功会返回JWT Token给用户
    }

    // 注册
    @PostMapping(value = "register")
    public User register(@RequestBody User addedUser) throws AuthenticationException {
        return authService.register(addedUser);
    }
}
