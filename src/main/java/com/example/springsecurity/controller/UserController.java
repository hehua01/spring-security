package com.example.springsecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date 2022/3/2 14:36
 * @Description
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/hello")
    public String start() {
        return "hello, user";
    }
}
