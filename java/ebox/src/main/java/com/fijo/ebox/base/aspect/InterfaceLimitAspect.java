package com.fijo.ebox.base.aspect;


import com.fijo.ebox.base.annotation.InterfaceLimitAnnotation;
import com.fijo.ebox.base.constant.AspectConstant;
import com.fijo.ebox.dto.ResultDto;
import com.fijo.ebox.base.util.plat.SessionUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Slf4j
public class InterfaceLimitAspect {
    @Autowired
    HttpServletRequest request;
    @Autowired
    private RedisTemplate redisTemplate;
    private static final String BASE_NAMESPACE = "fijo:";
    private static final long REDIS_TIME_OUT = 2*1000;

    @Pointcut("@annotation(com.fijo.ebox.base.annotation.InterfaceLimitAnnotation) ")
    public void pointcut(){}

    @SneakyThrows
    @Around("pointcut() && @annotation(interfaceLimitAnnotation)")
    public Object  doBefore(ProceedingJoinPoint joinPoint, InterfaceLimitAnnotation interfaceLimitAnnotation) throws Exception {
        HttpSession session = request.getSession();
        Map map = SessionUtil.getSession(request, AspectConstant.REDIS_KEY_LOGINUSER);
        String sessionId = session.getId();

        log.info("【拦截请求，请求前】befor");
        System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
        System.out.println("目标方法所属类的简单类名:" + joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        String key = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        key = BASE_NAMESPACE+sessionId+":"+key.replace(".",":");
        log.info(key);
        //判断key是否存在
        Object val = redisTemplate.opsForValue().get(key);
        if(val == null){
            redisTemplate.opsForValue().set(key,sessionId,REDIS_TIME_OUT, TimeUnit.MILLISECONDS);
            return joinPoint.proceed();
        }else{
            return ResultDto.ERROR("系统正在响应处理中，请勿多次重复操作！");
        }


    }


    @After("pointcut()")
    public void  doAfter(){
        log.info("【拦截请求，请求后】after");
    }

    @AfterReturning(returning = "obj",pointcut="pointcut()")
    public void doAfterReturning(Object obj){
        //log.info("【拦截请求，请求返回】response={}",obj);
    }
}