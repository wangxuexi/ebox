package com.fijo.ebox.controller;

import com.fijo.ebox.base.client.LoginClient;
import com.fijo.ebox.base.constant.AspectConstant;
import com.fijo.ebox.dto.ResultDto;
import com.fijo.ebox.enums.SysEnum.ResultEnum;
import com.fijo.ebox.base.util.plat.JsonUtil;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class LoginController {
    @Autowired
    HttpServletRequest request;

    @Autowired
    private LoginClient loginClient;


    /**
     * 登录
     * @param userName 账号
     * @param passWord 密码
     * @param tenant 租户名
     * @return
     */
    @PostMapping(value = "login")
    public String login(String userName,String passWord,String tenant,String applicationCode){


        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        //检验登录
        String result = null;
        try {
            result = loginClient.login(userName,passWord,tenant, applicationCode);
            ResultDto resultDto = JsonUtil.json2Bean(result,ResultDto.class);
            //判断是否登录成功
            if(Integer.parseInt(resultDto.getCode())==ResultEnum.RESULT_CODE_SUCCESS.getCode()){
                //保存用户session信息
                String userStr = JsonUtil.toJson(resultDto.getData());
                //保存sessionid
                session.setAttribute(AspectConstant.REDIS_KEY_SESSIONID, sessionId);
                //保存用户信息
                //TODO 调整存入的用户信息格式
                session.setAttribute(AspectConstant.REDIS_KEY_LOGINUSER,userStr);
            }else {
                //登录失败，直接返回提示
                return JsonUtil.toJsonNoSerialize(resultDto);
            }
            return result;
        } catch (NumberFormatException e) {
            return ResultDto.ERROR(e.getMessage());
        }

    }



    @PostMapping(value = "updatePassword")
    public String updatePassword(String tenant, String name,String oldPassword,String newPassword){
        log.info("【更新密码】账号：{},原始密码：{},新密码：{}",name,oldPassword,newPassword);
        String result = loginClient.updatePassword(tenant, name,oldPassword,newPassword);
        //如果返回结果为空
        if(StringUtil.isNullOrEmpty(result)){
            log.info("【修改密码】请求平台服务器修改密码，但返回值为空！");
            return ResultDto.ERROR("系统内部错误，修改密码失败！");
        }else{
            ResultDto resultDto = JsonUtil.json2Bean(result,ResultDto.class);
            if(resultDto == null){
                log.info("【修改密码】返回结果封装为结果对象时出错！");
                return ResultDto.ERROR("系统内部错误，修改密码失败！");
            }else{
                //判断修改密码的返回code
                if(resultDto.getCode().equals(ResultEnum.RESULT_CODE_SUCCESS)){
                    //修改成功，清空session和cookie
                    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                    HttpSession session = request.getSession();
                    HttpServletResponse response = attributes.getResponse();
                    String sessionId = session.getId();
                    //清空session
                    session.invalidate();

                    log.info("【修改密码】修改成功");
                    return result;
                }else{
                    log.info("【修改密码】修改失败，失败原因：{}",resultDto.getMsg());
                    return result;
                }
            }
        }
    }
    @PostMapping(value = "updatePasswordByTenant")
    public String updatePasswordByTenant(String tenant, String name,String oldPassword,String newPassword){
        log.info("【更新密码】账号：{},原始密码：{},新密码：{},租户：{}",tenant,name,oldPassword,newPassword);
        String result = loginClient.updatePassword(tenant, name,oldPassword,newPassword);
        //如果返回结果为空
        if(StringUtil.isNullOrEmpty(result)){
            log.info("【修改密码】请求平台服务器修改密码，但返回值为空！");
            return ResultDto.ERROR("系统内部错误，修改密码失败！");
        }else{
            ResultDto resultDto = JsonUtil.json2Bean(result,ResultDto.class);
            if(resultDto == null){
                log.info("【修改密码】返回结果封装为结果对象时出错！");
                return ResultDto.ERROR("系统内部错误，修改密码失败！");
            }else{
                //判断修改密码的返回code
                if(resultDto.getCode().equals(ResultEnum.RESULT_CODE_SUCCESS)){
                    //修改成功，清空session和cookie
                    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                    HttpSession session = request.getSession();
                    HttpServletResponse response = attributes.getResponse();
                    String sessionId = session.getId();
                    //清空session
                    session.invalidate();
                    log.info("【修改密码】修改成功");
                    return result;
                }else{
                    log.info("【修改密码】修改失败，失败原因：{}",resultDto.getMsg());
                    return result;
                }
            }
        }
    }


}
