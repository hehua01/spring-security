package com.example.simple.controller;

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
    public String hello(){
        return "hello, admin!";
    }
}
