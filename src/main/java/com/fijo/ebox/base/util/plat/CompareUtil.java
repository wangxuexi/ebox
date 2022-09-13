package com.fijo.ebox.base.util.plat;


import com.fijo.ebox.base.pojo.ComparePojo;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class CompareUtil {

    /**
     * 比较两个实体类的属性值
     * @param oldObj 旧对象
     * @param newObj 新对象
     * @param clazz 数据对象类型
     * @param baseClazz 平台封装的基础对象
     * @param <T> 泛型类
     * @return
     * @throws Exception
     */
    public static  <T> List<ComparePojo> CompareObject(T oldObj , T newObj, Class clazz, Class baseClazz) throws Exception  {
        if(!oldObj.getClass().isAssignableFrom(clazz)){
            throw new Exception("旧值类型与需比较类型不符合，请检查数据类型！");
        }
        if(!newObj.getClass().isAssignableFrom(clazz)){
            throw new Exception("新值类型与需比较类型不符合，请检查数据类型！");
        }

        List<ComparePojo> resultList = new ArrayList<>();//属性存在差异的结果集
        Field[] fieldArr_base = baseClazz.getDeclaredFields();//获取封装的通用类的所有属性

        String fieldNameArrStr = "";
        for(Field field : fieldArr_base){
            fieldNameArrStr +=  field.getName()+",";
        }
        String [] fieldNameArr_base = fieldNameArrStr.split(",");

        Field[] fieldArr = clazz.getDeclaredFields();//规范实体的所有属性
        Field[] old_fieldArr = oldObj.getClass().getDeclaredFields();//获取旧实体所有属性
        Field[] new_fieldArr = newObj.getClass().getDeclaredFields();//获取新实体所有属性


        for(int i = 0 ; i < fieldArr.length ; i++){
            old_fieldArr[i].setAccessible(true);//设置操作权限
            new_fieldArr[i].setAccessible(true);//设置操作权限

            String fieldName = fieldArr[i].getName();
            Boolean index = Arrays.asList(fieldNameArr_base).contains(fieldName); //排除基础属性值

            if(!index){
                fieldName = fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);//将属性的首字符大写，方便构造get，set方法
                String fieldType =  fieldArr[i].getGenericType().toString();    //获取属性的类型
                Class T_fieldType = fieldArr[i].getType();

                Method m_getCN =  oldObj.getClass().getMethod("get"+fieldName+"CN");
                String filedNameCN = (String) m_getCN.invoke(oldObj);
                Method m_old = oldObj.getClass().getMethod("get"+fieldName);
                Method m_new = newObj.getClass().getMethod("get"+fieldName);

                Object oldVal = null;
                Object newVal = null;

                //判断是否是string类型
                oldObj.getClass().isAssignableFrom(clazz);
                if(T_fieldType.isAssignableFrom(Byte.class) || T_fieldType.isAssignableFrom(byte.class)){
                    oldVal =  (Integer)m_old.invoke(oldObj);
                    newVal =  (Integer)m_new.invoke(newObj);
                }else if(T_fieldType.isAssignableFrom(Short.class) || T_fieldType.isAssignableFrom(short.class)){
                    oldVal =  (Short)m_old.invoke(oldObj);
                    newVal =  (Short)m_new.invoke(newObj);
                }else if(T_fieldType.isAssignableFrom(Integer.class) || T_fieldType.isAssignableFrom(int.class)){
                    oldVal =  (Integer)m_old.invoke(oldObj);
                    newVal =  (Integer)m_new.invoke(newObj);
                }else if(T_fieldType.isAssignableFrom(Long.class) || T_fieldType.isAssignableFrom(long.class)){
                    oldVal =  (Long)m_old.invoke(oldObj);
                    newVal =  (Long)m_new.invoke(newObj);
                }else if(T_fieldType.isAssignableFrom(Float.class) || T_fieldType.isAssignableFrom(float.class)){
                    oldVal =  (Float)m_old.invoke(oldObj);
                    newVal =  (Float)m_new.invoke(newObj);
                }else if(T_fieldType.isAssignableFrom(Double.class) || T_fieldType.isAssignableFrom(double.class)){
                    oldVal =  (Double)m_old.invoke(oldObj);
                    newVal =  (Double)m_new.invoke(newObj);
                }else if(T_fieldType.isAssignableFrom(char.class)){
                    oldVal =  (char)m_old.invoke(oldObj);
                    newVal =  (char)m_new.invoke(newObj);
                }else if(T_fieldType.isAssignableFrom(Boolean.class) || T_fieldType.isAssignableFrom(boolean.class)){
                    oldVal =  (Boolean)m_old.invoke(oldObj);
                    newVal =  (Boolean)m_new.invoke(newObj);
                }else if(T_fieldType.isAssignableFrom(String.class)){
                    oldVal =  (String)m_old.invoke(oldObj);
                    newVal =  (String)m_new.invoke(newObj);
                } else{
                    throw new Exception("数据类型为非八大基本数据类型以及String类型，请检查"+fieldName+"属性");
                }

                if(oldVal != null){ //旧值不等于空
                    if(!oldVal.equals(newVal)){ //进行比较后新增数据
                        ComparePojo comparePojo = new ComparePojo();
                        comparePojo.setFieldType(fieldType);//属性类型
                        comparePojo.setFieldName( fieldArr[i].getName());//属性名
                        comparePojo.setFiledNameCN(filedNameCN);//属性中文名
                        comparePojo.setOldValue(oldVal);//旧值
                        comparePojo.setNewValue(newVal);//新值
                        resultList.add(comparePojo);
                    }
                }else if(oldVal == null && newVal != null){ //旧值为空并且新值不为空 则直接新增数据
                    ComparePojo comparePojo = new ComparePojo();
                    comparePojo.setFieldType(fieldType);//属性类型
                    comparePojo.setFieldName( fieldArr[i].getName());//属性名
                    comparePojo.setFiledNameCN(filedNameCN);//属性中文名
                    comparePojo.setOldValue(oldVal);//旧值
                    comparePojo.setNewValue(newVal);//新值
                    resultList.add(comparePojo);
                }

               /* if((oldVal == null && newVal != null) || !oldVal.equals(newVal)){
                    ComparePojo comparePojo = new ComparePojo();
                    comparePojo.setFieldType(fieldType);//属性类型
                    comparePojo.setFieldName( fieldArr[i].getName());//属性名
                    comparePojo.setFiledNameCN(filedNameCN);//属性中文名
                    comparePojo.setOldValue(oldVal);//旧值
                    comparePojo.setNewValue(newVal);//新值
                    resultList.add(comparePojo);
                }*/
            }
        }
        return resultList;
    }
}
