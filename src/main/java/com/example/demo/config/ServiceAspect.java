package com.example.demo.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ServiceAspect {
    private Logger logger = LoggerFactory.getLogger(ServiceAspect.class);

    

    @Before("execution (* com.example.demo.controller.MainController.*(..))")
    public void before(JoinPoint joinPoint ) throws Throwable {
        logger.info("before class " + joinPoint.getSignature().getName());
    }

}
