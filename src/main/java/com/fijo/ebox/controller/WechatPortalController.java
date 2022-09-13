package com.fijo.ebox.controller;


import com.fijo.ebox.enums.ModularEnum.WechatOfficialAccountIdEnums;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

import java.lang.reflect.InvocationTargetException;


@RestController
@Slf4j
@RequestMapping({"/ANONYMOUS/PORTAL/{appid}"})
public class WechatPortalController {

    public WechatPortalController() {}

    @GetMapping(produces = {"text/plain;charset=utf-8"})
    public String authGet(@PathVariable String appid, @RequestParam(name = "signature",required = false) String signature, @RequestParam(name = "timestamp",required = false) String timestamp, @RequestParam(name = "nonce",required = false) String nonce, @RequestParam(name = "echostr",required = false) String echostr) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        log.debug("WPC====>接收到来自微信服务器的认证消息：\n [{}, {}, {}, {}]", new Object[]{signature, timestamp, nonce, echostr});
        if (StringUtils.isAnyBlank(new CharSequence[]{signature, timestamp, nonce, echostr})) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        } else {
            WxMpService wxService = new WxMpServiceImpl();
            wxService.setWxMpConfigStorage(wxMpConfigStorage(appid));
            //WxMpService wxService = (WxMpService)WxMpConfiguration.getMpServices().get(appid);
            if (wxService == null) {
                throw new IllegalArgumentException(String.format("未找到对应appid=[%d]的配置，请核实！", appid));
            } else {
                return wxService.checkSignature(timestamp, nonce, signature) ? echostr : "非法请求";
            }
        }
    }

    @PostMapping(produces = {"application/xml; charset=UTF-8"})
    public String post(@PathVariable String appid, @RequestBody String requestBody, @RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce, @RequestParam("openid") String openid, @RequestParam(name = "encrypt_type",required = false) String encType, @RequestParam(name = "msg_signature",required = false) String msgSignature) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        //WxMpService wxService = (WxMpService) WxMpConfiguration.getMpServices().get(appid);

        WxMpService wxService = new WxMpServiceImpl();
        wxService.setWxMpConfigStorage(wxMpConfigStorage(appid));

        log.debug("WPC====>接收微信请求：\n[openid=[{}], [signature=[{}], encType=[{}], msgSignature=[{}], timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ", new Object[]{openid, signature, encType, msgSignature, timestamp, nonce, requestBody});
        if (!wxService.checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        } else {
            String out = null;
            WxMpXmlMessage inMessage;
            WxMpXmlOutMessage outMessage;
            if (encType == null) {
                System.out.println(requestBody);
                inMessage = WxMpXmlMessage.fromXml(requestBody);
                log.warn("WPC====>inMessage明文接收消息：{}", inMessage.toString());
                outMessage = this.route(inMessage, appid);
                if (outMessage == null) {
                    //log.warn("WPC====>公众号路由处理为空……………………：{}", "success");
                    return "success";
                }
                out = outMessage.toXml();
                log.warn("WPC====>outMessage明文输出消息：{}", out);
            }
            /*else if ("aes".equalsIgnoreCase(encType)) {
                inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody, wxService.getWxMpConfigStorage(), timestamp, nonce, msgSignature);
                log.debug("WPC====>inMessage加密接收消息：{}", inMessage.toString());
                outMessage = this.route(inMessage, appid);
                if (outMessage == null) {
                    log.debug("WPC====>公众号路由处理为空……………………：{}", "success");
                    return "success";
                }
                out = outMessage.toEncryptedXml(wxService.getWxMpConfigStorage());
                log.debug("WPC====>outMessage加密输出消息：{}", outMessage.toString());
            }*/

            return out;
        }
    }

    public WxMpConfigStorage wxMpConfigStorage(String wechatOfficialAccountId) throws IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String mpAppId = wechatOfficialAccountId;//获取appid
        String mpAppSecret = WechatOfficialAccountIdEnums.getEnum(wechatOfficialAccountId).getSecret();//获取appsecret
        String mpAppToken = WechatOfficialAccountIdEnums.getEnum(wechatOfficialAccountId).getToken();//获取token
        String mpAppAesKey = WechatOfficialAccountIdEnums.getEnum(wechatOfficialAccountId).getAeskey();//获取aeskey
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(mpAppId);
        wxMpConfigStorage.setSecret(mpAppSecret);
        wxMpConfigStorage.setToken(mpAppToken);
        wxMpConfigStorage.setAesKey(mpAppAesKey);
        return wxMpConfigStorage;
    }


    private WxMpXmlOutMessage route(WxMpXmlMessage message, String appid) {
        try {
            return  null;
        } catch (Exception var4) {
            log.error("WPC====>路由消息时出现异常！", var4);
            return null;
        }
    }

}
