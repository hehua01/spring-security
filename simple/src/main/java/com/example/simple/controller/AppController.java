package com.example.simple.controller;

import com.example.simple.annotation.LogAnnotation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date 2022/3/2 15:34
 * @Description
 */
@RestController
@RequestMapping("/app")
public class AppController {

    @RequestMapping("/hello")
    @LogAnnotation(value = "bbbb")
    public String hello(){
        return "hello, app!";
    }
}