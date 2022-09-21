package com.fijo.ebox.base.aspect;

import com.fijo.ebox.base.annotation.ApproveAuthorityAnnotation;
import com.fijo.ebox.base.client.BasePlatClient;
import com.fijo.ebox.base.pojo.FijoBasePojo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

@Aspect
@Component
@Slf4j
public class ApproveAuthorityAspect {
    @Autowired
    private BasePlatClient basePlatClient;
    @Pointcut("@annotation(com.fijo.ebox.base.annotation.ApproveAuthorityAnnotation) ")
    public void pointcut() {
    }

    @SneakyThrows
    @Around("pointcut() && @annotation(approveAuthorityAnnotation)")
    public Object doBefore(ProceedingJoinPoint joinPoint, ApproveAuthorityAnnotation approveAuthorityAnnotation) throws Exception {
        log.info("【拦截请求，请求前】befor");
        System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
        System.out.println("目标方法所属类的简单类名:" + joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
        String newArgValue = "";//TODO 请求接口
        Object[] args = joinPoint.getArgs();
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames(); // 参数名

        AtomicReference<String> tenant = new AtomicReference<>("");
        AtomicReference<String> entityTypeCode = new AtomicReference<>("");
        AtomicReference<String> dbTableName = new AtomicReference<>("");
        AtomicLong uId = new AtomicLong(-1);
        AtomicReference<String> orgCode = new AtomicReference<>("");
        AtomicReference<String> headerTableReName = new AtomicReference<>("");
        AtomicReference<String> states = new AtomicReference<>("");


        for (int i = 0; i < argNames.length; i++) {
            Object arg = args[i];
            Boolean isExtendPlatPojo = false;
            if(arg instanceof FijoBasePojo){
                isExtendPlatPojo = true;
            }
            if(isExtendPlatPojo){
                Field[] fieldArr = arg.getClass().getDeclaredFields();//获取类的所有属性
                Arrays.stream(fieldArr).forEach(field -> {
                    field.setAccessible(true);
                    try {
                        String fieldEn = field.getName();
                        String value = String.valueOf(field.get(arg));
                        if (fieldEn.equals(approveAuthorityAnnotation.tenant())) {
                            tenant.set(value);
                        }
                        if (fieldEn.equals(approveAuthorityAnnotation.entityTypeCode())) {
                            entityTypeCode.set(value);
                        }
                        if (fieldEn.equals(approveAuthorityAnnotation.dbTableName())) {
                            dbTableName.set(value);
                        }
                        if (fieldEn.equals(approveAuthorityAnnotation.uId())) {
                            uId.set(new Long(value));
                        }
                        if (fieldEn.equals(approveAuthorityAnnotation.orgCode())) {
                            orgCode.set(value);
                        }
                        if (fieldEn.equals(approveAuthorityAnnotation.headerTableReName())) {
                            headerTableReName.set(value);
                        }
                        if (fieldEn.equals(approveAuthorityAnnotation.states())) {
                            states.set(value);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });

            }else{
                if (argNames[i].equals(approveAuthorityAnnotation.tenant())) {
                    tenant.set(String.valueOf(args[i]));
                }
                if (argNames[i].equals(approveAuthorityAnnotation.entityTypeCode())) {
                    entityTypeCode.set(String.valueOf(args[i]));
                }
                if (argNames[i].equals(approveAuthorityAnnotation.dbTableName())) {
                    dbTableName.set(String.valueOf(args[i]));
                }
                if (argNames[i].equals(approveAuthorityAnnotation.uId())) {
                    uId.set((Long) args[i]);
                }
                if (argNames[i].equals(approveAuthorityAnnotation.orgCode())) {
                    orgCode.set(String.valueOf(args[i]));
                }
                if (argNames[i].equals(approveAuthorityAnnotation.headerTableReName())) {
                     String _value  = args[i]== null ? dbTableName.get().toLowerCase() : String.valueOf(args[i]);
                    headerTableReName.set(_value);
                }
                if (argNames[i].equals(approveAuthorityAnnotation.states())) {
                    states.set(String.valueOf(args[i]));
                }
            }


        }
        newArgValue =  basePlatClient.queryApproveAuthority(tenant.get(), entityTypeCode.get(), dbTableName.get(), uId.get(), orgCode.get(), headerTableReName.get() , states.get());
        for (int i = 0; i < argNames.length; i++) {
            if (argNames[i].equals(approveAuthorityAnnotation.sqlArgName())) {
                args[i] = newArgValue;
            }
        }
        return joinPoint.proceed(args);
    }


    @After("pointcut()")
    public void doAfter() {
        log.info("【拦截请求，请求后】after");
    }

    @AfterReturning(returning = "obj", pointcut = "pointcut()")
    public void doAfterReturning(Object obj) {
        //log.info("【拦截请求，请求返回】response={}", obj);
    }


}