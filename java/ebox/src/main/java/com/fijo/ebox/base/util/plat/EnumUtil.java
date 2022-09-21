package com.fijo.ebox.base.util.plat;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理枚举值
 */
@Slf4j
public class EnumUtil {

    public static String[] getEnumValue(Class clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        if(clazz.isEnum()){
            Object[] enumConstants = clazz.getEnumConstants();
            String [] enumMsgArr = new String [enumConstants.length];
            Method getMsg = clazz.getMethod("getMsg");
            for(int i = 0 ; i < enumConstants.length ; i++){
                String msg = (String) getMsg.invoke(enumConstants[i]);
                enumMsgArr[i] = msg;
            }
            return enumMsgArr;
        }else{
            log.error("EnumUtil.getEnumValue:传入类型非法,请传入枚举类型！");
            throw new RuntimeException("EnumUtil.getEnumValue:传入类型非法,请传入枚举类型！");
        }
    };

    public static Map<String,String> getEnumMap(Class clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        if(clazz.isEnum()){
            Object[] enumConstants = clazz.getEnumConstants();
            Map<String,String> enumMap = new HashMap<>();
            Method getMsg = clazz.getMethod("getMsg");
            Method getCode = clazz.getMethod("getCode");
            for(int i = 0 ; i < enumConstants.length ; i++){
                String code = String.valueOf(getCode.invoke(enumConstants[i]));
                String msg = (String) getMsg.invoke(enumConstants[i]);
                enumMap.put(code,msg);
            }
            return enumMap;
        }else{
            log.error("EnumUtil.getEnumValue:传入类型非法,请传入枚举类型！");
            throw new RuntimeException("EnumUtil.getEnumValue:传入类型非法,请传入枚举类型！");
        }
    };
}
