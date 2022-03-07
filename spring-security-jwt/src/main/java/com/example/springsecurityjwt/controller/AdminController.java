package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.annotation.LogAnnotation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date 2022/3/2 15:33
 * @Description
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping("/hello")
    @PreAuthorize("hasAuthority('ADMIN')")
    @LogAnnotation(value = "aaa")
    public String hello(){
        return "hello, admin!";
    }
}
