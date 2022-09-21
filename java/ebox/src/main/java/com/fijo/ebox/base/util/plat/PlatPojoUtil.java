package com.fijo.ebox.base.util.plat;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 对象util
 */
public class PlatPojoUtil {
    public final static String FIELD_EN_KEY = "fieldEn";
    public final static String FIELD_CN_KEY = "fieldCn";

    /**
     * 获取类的所有属性
     * @param clazz
     * @param ignoreFieldList 忽略不导出的字段名
     * @return
     */
    public static Map<String, String[]> getClazzFieldsInfo(Class clazz,List<String> ignoreFieldList) throws Exception{
        Field[] fieldArr = clazz.getDeclaredFields();//获取类的所有属性
        Map resultMap = new HashMap();
        List<String> fieldEnList = new ArrayList<>();
        List<String> fieldCnList = new ArrayList<>();
        Object object = clazz.newInstance();
        Arrays.stream(fieldArr).forEach(field -> {
            String fieldEn = field.getName();
            if(!ignoreFieldList.contains(fieldEn)){
                String fieldName = fieldEn.substring(0,1).toUpperCase()+fieldEn.substring(1);
                fieldEnList.add(fieldEn);
                try {
                    //获取字段公共属性
                    Method m_getCN = clazz.getMethod("get"+fieldName+"CN");
                    m_getCN.setAccessible(true);
                    String filedNameCN = (String) m_getCN.invoke(object);
                    fieldCnList.add(filedNameCN);
                } catch (NoSuchMethodException e) {
                    //获取不到公共属性则去取私有属性
                    try {
                        Method m_getCN = null;
                        m_getCN = clazz.getDeclaredMethod("get"+fieldName+"CN");
                        m_getCN.setAccessible(true);
                        String filedNameCN = (String) m_getCN.invoke(object);
                        fieldCnList.add(filedNameCN);
                    } catch (NoSuchMethodException noSuchMethodException) {
                        noSuchMethodException.printStackTrace();
                    } catch (InvocationTargetException invocationTargetException) {
                        invocationTargetException.printStackTrace();
                    } catch (IllegalAccessException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    } catch (Exception exception){
                        exception.printStackTrace();
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        String[] fieldEnArr=fieldEnList.toArray(new String[fieldEnList.size()]);
        String[] fieldCnArr=fieldCnList.toArray(new String[fieldCnList.size()]);
        resultMap.put(FIELD_EN_KEY,fieldEnArr);
        resultMap.put(FIELD_CN_KEY,fieldCnArr);
        return resultMap;
    }

}
