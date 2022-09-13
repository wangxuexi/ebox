package com.fijo.ebox.base.pojo;

import lombok.Data;

import java.util.List;

/**
*sys_early_warning_msg
*FJSYSEW0004
**/
@Data
public class FJSYSEW0004 extends FijoBasePojo {
	private Long id; //id

	private String tenant; //租户编码

	private String orgCode; //组织编码

	private String entityTypeCode; //实体类型编码

	private Long businessId; //业务ID

	private Long earlyWarningId; //预警项ID

	private Integer gearValue; //预警档位值

	private String warningInfo; //预警消息内容

	private Integer state;//消息状态

	private Boolean isUserOffLamp; //是否人工消灯

	private Integer offLampTimeLimit; //预警消灯处理时限

	private Long approveTypeId; //消灯流程审批流ID

	private Integer autoOffLampTimeLimit; //自动消灯周期时间

	private Integer sort; //排序

	private String remark; //备注

	private Long chargUserId;//查询条件 处理人id

	private List<Long> groupIdList;//查询条件，处理人所属用户组id








	public String getIdCN(){
		return "id";
	}

	public String getTenantCN(){
		return "租户编码";
	}

	public String getOrgCodeCN(){
		return "组织编码";
	}

	public String getEntityTypeCodeCN(){
		return "实体类型编码";
	}

	public String getBusinessIdCN(){
		return "业务ID";
	}

	public String getEarlyWarningIdCN(){
		return "预警项ID";
	}

	public String getGearValueCN(){
		return "预警档位值";
	}

	public String getWarningInfoCN(){
		return "预警消息内容";
	}

	private String getStateInfoCN(){return "消息状态";};


	public String getIsUserOffLampCN(){
		return "是否人工消灯";
	}

	public String getOffLampTimeLimitCN(){
		return "预警消灯处理时限";
	}

	public String getApproveTypeIdCN(){
		return "消灯流程审批流ID";
	}

	public String getAutoOffLampTimeLimitCN(){
		return "自动消灯周期时间";
	}

	public String getSortCN(){
		return "排序";
	}

	public String getRemarkCN(){
		return "备注";
	}

	public String getEnabledCN(){
		return "是否有效";
	}

	public String getRemovedCN(){
		return "是否删除";
	}

	public String getCreateUserIdCN(){
		return "创建人ID";
	}

	public String getCreateUserNameCN(){
		return "创建人姓名";
	}

	public String getCreateTimeCN(){
		return "创建时间";
	}

	public String getUpdateUserIdCN(){
		return "更新人ID";
	}

	public String getUpdateUserNameCN(){
		return "更新人姓名";
	}

	public String getUpdateTimeCN(){
		return "更新时间";
	}


}

