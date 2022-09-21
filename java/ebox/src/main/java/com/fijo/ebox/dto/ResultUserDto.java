package com.fijo.ebox.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户表
 */
@Data
public class ResultUserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long uId; //用户id
    private String cId;//消息推送ID
    private String tenant;//租户编码
    private String userOrg;//用户归属组织
    private String orgCode;//组织编码 数据权限
    private String position;//职位
    private String authorityId;//权限组id
    private String authorityName;//权限组名字
    private String loginName;//用户名
    private String nickName;//用昵称
    private String phone;//联系方式
    private String mail;//邮件地址
    private String headImgUrl; //头像路径

}
