package com.fijo.ebox.base.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EarlyWarningBackDataDto {
    private Long id;//数据实体id

    private String orgCode; //数据归属组织

    private String name;//名称

    private BigDecimal value;//计算值

    private Integer gearValue;//档位值

    private Boolean isUserOffLamp; //是否人工消灯

    private String notifierUserGroupId; //通知人用户组ID

    private String chargeUserGroupId; //责任人用户组ID


    private Integer offLampTimeLimit; //预警消灯处理时限

    private Long approveTypeId; //消灯流程审批流ID

    private Integer autoOffLampTimeLimit; //自动消灯周期时间

    private String info;//其他信息
}
