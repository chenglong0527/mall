package com.pandax.litemall.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class LogAspect {
    @Pointcut("execution(* com.pandax.litemall.controller..*(..))")
    public void mypointcut(){}
    @Before("mypointcut()")
    public void logManager(JoinPoint joinPoint){
        
    }
}
