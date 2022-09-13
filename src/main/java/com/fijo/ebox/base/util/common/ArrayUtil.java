package com.fijo.ebox.base.util.common;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ArrayUtil {

    //交集(注意结果集中若使用LinkedList添加，则需要判断是否包含该元素，否则其中会包含重复的元素)
    public static String[] intersect(String[] arr1, String[] arr2){
        List<String> l = new LinkedList<String>();
        Set<String> common = new HashSet<String>();
        for(String str:arr1){
            if(!l.contains(str)){
                l.add(str);
            }
        }
        for(String str:arr2){
            if(l.contains(str)){
                common.add(str);
            }
        }
        String[] result={};
        return common.toArray(result);
    }

    /**
     * list转str数组
     * @param list
     * @return
     */
    public static String [] List2StrArr(List list){
        return (String[])list.toArray(new String[list.size()]);
    }
}
