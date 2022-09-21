package com.fijo.ebox.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * http请求切面
 */
@Aspect
@Component
@Slf4j
public class HttpOpenApiAspect {

    @Autowired
    HttpServletRequest request;

    @Pointcut("execution(public * com.fijo.ebox.api.*.*.*(..)) ")
    public void pointcut(){}

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint){

            //url
            String url = request.getRequestURL().toString();
            //url
            String uri = request.getRequestURI().toString();
            System.out.println(uri);
            log.info("【拦截请求，请求前】url={}",request.getRequestURL());
            //method
            log.info("【拦截请求，请求前】method={}",request.getMethod());
            //ip
            log.info("【拦截请求，请求前】ip={}",request.getRemoteAddr());
            //类方法
            log.info("【拦截请求，请求前】class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
            //参数
            log.info("【拦截请求，请求前】args={}", Arrays.toString(joinPoint.getArgs()));
            //TODO 将日志存数据库

    }

    @After("pointcut()")
    public void  doAfter(){
        log.info("【拦截请求，请求后】after");
    }

    @AfterReturning(returning = "obj",pointcut="pointcut()")
    public void doAfterReturning(Object obj){
    }
}
