package com.fijo.ebox.base.util.plat;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AjaxUtil {

    /**
     *功能描述
     * @author lgj
     * @Description   Ajax请求时重定向处理
     * @date 2/27/19
     * @param:
     * @return:
     *
     */
    public static void sendRedirect(HttpServletResponse response, String redirectUrl){
        try {
            //这里并不是设置跳转页面，而是将重定向的地址发给前端，让前端执行重定向
            //设置跳转地址
            response.setHeader("redirectUrl", redirectUrl);
            //设置跳转使能
            response.setHeader("enableRedirect","true");
            response.flushBuffer();
        } catch (IOException ex) {
            log.error("Could not redirect to: " + redirectUrl, ex);
        }
    }
}
