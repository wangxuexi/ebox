package com.fijo.ebox.base.util.plat;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * 反射获取指定属性名的值
 */
@Slf4j
public class ReflectUtil {
    /**
     * 通过属性名称获取属性值，返回字符串
     *
     * @param fieldName 属性名
     * @param object    对象
     * @return
     */
    public static String getFieldValueByFieldName(String fieldName, Object object) {
        Field field = null;
        try {
             field = object.getClass().getDeclaredField(fieldName);
        } catch (Exception e) {
            try {
                field = object.getClass().getSuperclass().getDeclaredField(fieldName);//实例化对象的属性
            } catch (NoSuchFieldException noSuchFieldException) {
                log.error("未找到{}的属性",fieldName);
            }
        }
        try {
            field.setAccessible(true);
            Object hisValue = field.get(object);
            if (null == hisValue) {
                return "";
            }
            String value = hisValue.toString();
            return value;
        } catch (Exception e) {
            return "";
        }

    }


}
