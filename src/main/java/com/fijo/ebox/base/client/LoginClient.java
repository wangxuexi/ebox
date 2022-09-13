package com.fijo.ebox.base.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "FIJO-BASEPLAT")
public interface LoginClient {
    /**
     * 调用平台登录服务
     * @param name
     * @param password
     * @return
     */
    @PostMapping(value = "/FJSYSUS0001/getLogin") // 请求方式与服务方保持完全一致
    public String login(@RequestParam("name") String name,
                        @RequestParam("password") String password,
                        @RequestParam("tenant") String tenant,
                        @RequestParam("applicationCode") String applicationCode);



    /**
     * 调用平台登录服务
     * @param name
     * @param password
     * @return
     */
    @PostMapping(value = "/FJSYSUS0001/getLoginApp") // 请求方式与服务方保持完全一致
    public String login(@RequestParam("name") String name,
                        @RequestParam("password") String password,
                        @RequestParam("tenant") String tenant,
                        @RequestParam("cId") String cId,
                        @RequestParam("applicationCode") String applicationCode);


    /**
     * 修改账号密码
     * @param tenant 租户编码
     * @param loginName 用户名
     * @param oldPassword 历史密码
     * @param newPassword 新密码
     * @return
     */
    @PostMapping(value = "/FJSYSUS0001/updatePassword")
    public String updatePassword(@RequestParam("tenant") String tenant,
                                 @RequestParam("loginName") String loginName,
                                 @RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword);
}
