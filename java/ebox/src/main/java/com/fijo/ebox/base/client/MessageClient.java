package com.fijo.ebox.base.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "fijo-message")//value=注册在eureka上的服务名
public interface MessageClient {
    /**
     * 发送短信
     * @param tenant 租户编码
     * @param applicationCode 应用编码
     * @param templateid 模板ID
     * @param mobile 接收人手机号
     * @param contens 短信模板变量内容
     * @return
     */
    @PostMapping(value = "/FISYME0001/SendSMS")
    String SendSMS(@RequestParam("tenant") String tenant,
                   @RequestParam("applicationCode") String applicationCode,
                   @RequestParam("templateid") String templateid,
                   @RequestParam("mobile") String mobile,
                   @RequestParam("contens") String contens);


    /**
     * 发送微信模板消息
     * @param tenant 租户编码
     * @param applicationCode 应用编码
     * @param templateid 模板ID
     * @param openid 微信openid
     * @param contens 微信消息模板变量内容
     * @param url 跳转地址
     * @return
     */
    @PostMapping(value = "/FISYME0002/SendWX")
    String SendWX(@RequestParam("tenant") String tenant,
                   @RequestParam("applicationCode") String applicationCode,
                   @RequestParam("templateid") String templateid,
                   @RequestParam("openid") String openid,
                   @RequestParam("appId") String appId,
                   @RequestParam("contens") String contens,
                   @RequestParam("url") String url);

    /**
     *
     * @param tenant  租户编码
     * @param applicationCode  组织编码
     * @param type  短信类型
     * @param orgcode  组织编码
     * @param mobile  手机号
     * @param contens  短信内容
     * @return
     */
    @PostMapping(value = "/FISYME0001/sendsmsbyzszx")
    String sendsmsbyzszx(@RequestParam("tenant") String tenant,
                   @RequestParam("applicationCode") String applicationCode,
                   @RequestParam("type") String type,
                   @RequestParam("orgcode") String orgcode,
                   @RequestParam("mobile") String mobile,
                   @RequestParam("contens") String contens);

    @PostMapping(value = "/FISYME0002/sendwxbyzszx")
    String sendwxbyzszx( @RequestParam("tenant") String tenant,
                         @RequestParam("applicationCode") String applicationCode,
                         @RequestParam("type") String type,
                         @RequestParam("openid") String openid,
                         @RequestParam("appId") String appId,
                         @RequestParam("mobile") String mobile,
                         @RequestParam("contens") String contens);

    /**
     * 微信模板消息发送
     * @param tenant 租户
     * @param applicationCode 应用编码
     * @param templateCode 模板id
     * @param openId 微信id
     * @param mobile 手机号
     * @param contents 内容
     * @param url 微信跳转路径
     * @return
     */
    @PostMapping(value = "/msgOpenApi/sendWeChatMsg")
    String sendWeChatMsg( @RequestParam("tenant") String tenant,
                          @RequestParam("applicationCode") String applicationCode,
                          @RequestParam("templateCode") String templateCode,
                          @RequestParam("openId") String openId,
                          @RequestParam("mobile") String mobile,
                          @RequestParam("contents") String contents,
                          @RequestParam("url") String url);

    /**
     * 短信发送
     * @param tenant 租户
     * @param applicationCode 应用编码
     * @param templateCode 模板id
     * @param openid 微信id
     * @param mobile 手机号
     * @param contents 内容
     * @param url 微信跳转路径
     * @return
     */
    @PostMapping(value = "/msgOpenApi/sendMobileMsg")
    String sendMobileMsg( @RequestParam("tenant") String tenant,
                          @RequestParam("applicationCode") String applicationCode,
                          @RequestParam("templateCode") String templateCode,
                          @RequestParam("openId") String openid,
                          @RequestParam("mobile") String mobile,
                          @RequestParam("contents") String contents,
                          @RequestParam("url") String url);

}
