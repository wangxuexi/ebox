package com.fijo.ebox.base.util.plat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SessionUtil {

    public static Map<String, Object> getSession(HttpServletRequest request,String key) {
        Map<String, Object> map = new HashMap<>();
        HttpSession session = request.getSession();
        Object attribute = session.getAttribute(key);
        map.put(key, attribute);
        return map;
    }
}
