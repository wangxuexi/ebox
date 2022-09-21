package com.fijo.ebox.base.util.plat;

import com.fijo.ebox.base.constant.AspectConstant;
import com.fijo.ebox.dto.ResultUserDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 平台工具
 */
public class PlatUtil {


    /**
     * 获取登录人信息
     */
    public static ResultUserDto getLoginUser(HttpServletRequest request){
        Map map = SessionUtil.getSession(request, AspectConstant.REDIS_KEY_LOGINUSER);
        String loginUserStr = (String) map.get(AspectConstant.REDIS_KEY_LOGINUSER);
        ResultUserDto loginUser = JsonUtil.json2Bean(loginUserStr,ResultUserDto.class);
        return loginUser;
    }
}
