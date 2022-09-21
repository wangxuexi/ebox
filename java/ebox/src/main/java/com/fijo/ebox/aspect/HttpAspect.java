package com.fijo.ebox.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * http请求切面
 */
@Aspect
@Component
@Slf4j
public class HttpAspect {

    @Autowired
    HttpServletRequest request;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Pointcut("execution(public * com.fijo.ebox.controller.*.*(..)) ||execution(public * com.fijo.ebox.modular.*.controller.*.*(..)) ")
    public void pointcut(){}

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        //log.info("【拦截请求，请求前】befor");
    }

    @After("pointcut()")
    public void  doAfter(){
        //log.info("【拦截请求，请求后】after");
    }

    @AfterReturning(returning = "obj",pointcut="pointcut()")
    public void doAfterReturning(Object obj){
        //log.info("【拦截请求，请求返回】response={}",obj);
    }
}
