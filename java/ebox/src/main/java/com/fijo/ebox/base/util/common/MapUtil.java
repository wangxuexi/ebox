package com.fijo.ebox.base.util.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MapUtil {

    /**
     *功能描述
     * @author ljy
     * @Description   Object转Map
     * @date 20/09/10
     * @param:
     * @return:
     *
     */
    public static Map<String, Object> convertToMap(Object obj) {
        try {
            if (obj instanceof Map) {
                return (Map)obj;
            }
            Map<String, Object> returnMap = BeanUtils.describe(obj);
            returnMap.remove("class");
            return returnMap;
        } catch (IllegalAccessException e1) {
            e1.getMessage();
        } catch (InvocationTargetException e2) {
            e2.getMessage();
        } catch (NoSuchMethodException e3) {
            e3.getMessage();
        }
        return new HashMap();
    }
}
