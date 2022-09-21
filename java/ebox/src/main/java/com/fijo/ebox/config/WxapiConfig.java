package com.fijo.ebox.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wxapi")
public class WxapiConfig {
    private String getAccessToken;  //获取accessToken

    private String insertImg;  //新增永久素材（图文消息缩略图）

    private String imgFileUpload;  //图文消息内容图片上传

    private String insertDraft;  //新增草稿

    private String sendMsgPreview;  //图文消息群发推送 （预览接口）

    private String sendMsg;  //图文消息群发接口

    private String getMstSendState;  //获取消息发送状态
}
