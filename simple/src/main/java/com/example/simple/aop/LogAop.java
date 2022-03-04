package com.example.simple.aop;

import com.example.simple.annotation.LogAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;


/**
 * @Date 2022/3/4 11:56
 * @Description
 */
@Component
@Aspect
public class LogAop {
    @Pointcut("@annotation(com.example.simple.annotation.LogAnnotation)")
    public void pointCut(){}

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {

        Signature signature = joinPoint.getSignature();
        MethodSignature msg = (MethodSignature) signature;
        Method method =  msg.getMethod();

        if (method.isAnnotationPresent(LogAnnotation.class)) {
            LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
            System.out.println(annotation.value());
        } else {
            System.out.println("not annotation");
        }
    }
}
