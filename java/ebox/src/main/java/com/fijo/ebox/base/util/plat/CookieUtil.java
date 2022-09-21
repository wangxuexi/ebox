package com.fijo.ebox.base.util.plat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * cookie工具类
 */
public class CookieUtil {

    /**
     * 设置cookie
     * @param response
     * @param name
     * @param value
     * @param maxage
     */
    public static void set(HttpServletResponse response,
                           String name,
                           String value,
                           int maxage){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(maxage);
        response.addCookie(cookie);
    }


    public static Cookie get(HttpServletRequest request, String name){
        Map<String , Cookie> cookieMap = readCookieMap(request);
        if(cookieMap.containsKey(name)){
            return cookieMap.get(name);
        }else {
            return null;
        }
    }

    /**
     * 将cookie封装成map
     * @param request
     * @return
     */
    private static Map<String , Cookie> readCookieMap(HttpServletRequest request){
        Map<String , Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies){
                cookieMap.put(cookie.getName(),cookie);
            }
        }
        return cookieMap;
    }
}
