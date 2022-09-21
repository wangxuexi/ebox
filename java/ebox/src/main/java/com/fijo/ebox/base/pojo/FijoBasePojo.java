package com.fijo.ebox.base.pojo;

import lombok.Data;

@Data
public abstract class FijoBasePojo {
    private String createUserName; //创建人
    private String createTime; //创建日期
    private Long createUserId; //创建人ID
    private String updateTime; //更新时间
    private String updateUserName; //更新人
    private Long updateUserId; //更新人ID
    private String operateIp; //操作人IP
    private Boolean enabled; //是否有效
    private Boolean removed; //是否删除
    private Integer pageNo;
    private Integer pageSize;

}
