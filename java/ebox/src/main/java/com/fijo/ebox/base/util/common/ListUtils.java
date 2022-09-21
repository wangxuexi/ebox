package com.fijo.ebox.base.util.common;

import java.util.*;

public class ListUtils {
    public static Map<String, Object> change(List<Map<String, Object>> list, String oneMapKey) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Set<Object> setTmp = new HashSet<Object>();
        for (Map<String, Object> tmp : list) {
            setTmp.add(tmp.get(oneMapKey));
        }
        Iterator<Object> it = setTmp.iterator();
        while (it.hasNext()) {
            String oneSetTmpStr = String.valueOf(it.next());
            List<Map<String, Object>> oneSetTmpList = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> tmp : list) {
                String oneMapValueStr =  String.valueOf(tmp.get(oneMapKey));
                if (oneMapValueStr.equals(oneSetTmpStr)) {
                    oneSetTmpList.add(tmp);
                }
            }
            resultMap.put(oneSetTmpStr, oneSetTmpList);
        }
        return resultMap;
    }

    /**
     * 按照List<Map<String,Object>>里面map的某个value重新封装成多个不同的list, 原始数据类型List<Map
     * <String,Object>>, 转换后数据类型Map<String,List<Map<String,Object>>>
     *
     * @param list
     * @param oneMapKey
     * @return
     */
    public static List<Map<String, Object>> change2(List<Map<String, Object>> inList, String oneMapKey,
                                                     List<Map<String, Object>> outList) {
        // 1.将某个key的值存在set中
        Set<Object> setTmp = new HashSet<Object>();
        for (Map<String, Object> tmp : inList) {
            setTmp.add(tmp.get(oneMapKey));
        }
        // 2.遍历set
        Iterator<Object> it = setTmp.iterator();
        while (it.hasNext()) {
            String oneMapValueStr = "";
            String oneSetTmpStr = (String) it.next();
            Map<String, Object> oneSetTmpMap = new HashMap<String, Object>();
            List<Map<String, Object>> oneSetTmpList = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> tmp : inList) {
                oneMapValueStr = (String) tmp.get(oneMapKey);
                if (oneSetTmpStr.equals(oneMapValueStr)) {
                    oneSetTmpMap.put("type", oneSetTmpStr);
                    oneSetTmpList.add(tmp);
                }
            }
            oneSetTmpMap.put("data", oneSetTmpList);
            outList.add(oneSetTmpMap);
        }
        return outList;
    }





}
