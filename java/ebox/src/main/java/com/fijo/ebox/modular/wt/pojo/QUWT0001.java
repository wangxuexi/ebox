package com.fijo.ebox.modular.wt.pojo;
import com.fijo.ebox.base.pojo.FijoBasePojo;
import lombok.Data;
/**
*CM_PROBLEM_HANDLE_RECORD
*QUWT0001
**/
@Data
public class QUWT0001 extends FijoBasePojo {
	private Long id; //主键ID

	private String orgCode; //组织编码

	private String entityTypeCode; //来源实体类编码

	private Long objectId; //来源ID

	private String problemName; //问题标题

	private String problemAddress; //发生地址

	private String happenTime; //发生时间

	private Integer isRepairRecord; //是否生成维修记录（废弃）

	private Integer isSyncDisPlat; //是否同步区级平台

	private Integer problemSource; //数据来源 1：商铺巡查 2：设施巡查 3：自主提交 4：外部问题

	private String problemSourceId; //问题来源ID（外部问题唯一标识）（废弃）

	private String problemType; //问题类型（数据字典）

	private Long problemTypeId; //问题类型ID （数据字典）

	private String problemContent; //问题说明

	private String submitName; //提交人

	private String submitTime; //提交时间

	private String punishDepartment; //处罚部门

	private String endTime; //结案时间

	private String endName; //结案人

	private String endExplain; //结案说明

	private String stateName; //状态名称

	private Integer state; //状态 1：待处理 2：已处理 3：已结案

	private String note; //备注

	private Integer sort; //排序

	private String col1; //预留字段1

	private String col2; //预留字段2

	private String col3; //预留字段3

	private String col4; //预留字段4

	private String col5; //预留字段5

	private String col6; //预留字段6

	private String col7; //预留字段7

	private String col8; //预留字段8

	private String col9; //预留字段9

	private String col10; //预留字段10


	private String happenStratTime;	//问题开始时间

	private String happenEndTime;	//问题结束时间

	private String lng;	//问题发生地址的经度

	private String lat; //问题发生地址的纬度

	private Long submitId;	//提交人

	private String imageDataStr;	//附件数据字符串

	private String onelvlOrgCode;	//一级组织

	private String problemSmallType; //问题小类类型（数据字典）

	private Long problemSmallTypeId; //问题小类类型ID （数据字典）

	private Long endUserId; //结案人Id

	public String getProblemSmallTypeCN(){
		return "问题小类类型";
	}

	public String getProblemSmallTypeIdCN(){
		return "问题小类类型";
	}

	public String getOnelvlOrgCodeCN(){
		return "一级组织";
	}

	public String getSubmitIdCN(){
		return "提交人Id";
	}

	public String getHappenStratTimeCN(){
		return "问题开始时间";
	}

	public String getHappenEndTimeCN(){
		return "问题结束时间";
	}

	public String getLngCN(){
		return "经度";
	}

	public String getLatCN(){
		return "纬度";
	}

	public String getIdCN(){
		return "主键ID";
	}

	public String getOrgCodeCN(){
		return "组织编码";
	}

	public String getEntityTypeCodeCN(){
		return "来源实体类编码";
	}

	public String getObjectIdCN(){
		return "来源ID";
	}

	public String getProblemNameCN(){
		return "问题标题";
	}

	public String getProblemAddressCN(){
		return "发生地址";
	}

	public String getHappenTimeCN(){
		return "发生时间";
	}

	public String getIsRepairRecordCN(){
		return "是否生成维修记录（废弃）";
	}

	public String getIsSyncDisPlatCN(){
		return "是否同步区级平台";
	}

	public String getProblemSourceCN(){
		return "数据来源 1：商铺巡查 2：设施巡查 3：自主提交 4：外部问题";
	}

	public String getProblemSourceIdCN(){
		return "问题来源ID（外部问题唯一标识）（废弃）";
	}

	public String getProblemTypeCN(){
		return "问题类型（数据字典）";
	}

	public String getProblemTypeIdCN(){
		return "问题类型ID （数据字典）";
	}

	public String getProblemContentCN(){
		return "问题说明";
	}

	public String getSubmitNameCN(){
		return "提交人";
	}

	public String getSubmitTimeCN(){
		return "提交时间";
	}

	public String getPunishDepartmentCN(){
		return "处罚部门";
	}

	public String getEndTimeCN(){
		return "结案时间";
	}

	public String getEndNameCN(){
		return "结案人";
	}

	public String getEndExplainCN(){
		return "结案说明";
	}

	public String getStateNameCN(){
		return "状态名称";
	}

	public String getStateCN(){
		return "状态 1：待处理 2：已处理 3：已结案";
	}

	public String getNoteCN(){
		return "备注";
	}

	public String getSortCN(){
		return "排序";
	}

	public String getCol1CN(){
		return "预留字段1";
	}

	public String getCol2CN(){
		return "预留字段2";
	}

	public String getCol3CN(){
		return "预留字段3";
	}

	public String getCol4CN(){
		return "预留字段4";
	}

	public String getCol5CN(){
		return "预留字段5";
	}

	public String getCol6CN(){
		return "预留字段6";
	}

	public String getCol7CN(){
		return "预留字段7";
	}

	public String getCol8CN(){
		return "预留字段8";
	}

	public String getCol9CN(){
		return "预留字段9";
	}

	public String getCol10CN(){
		return "预留字段10";
	}

	public String getEnabledCN(){
		return "是否有效";
	}

	public String getRemovedCN(){
		return "是否删除";
	}

	public String getCreateUserNameCN(){
		return "创建人";
	}

	public String getCreateTimeCN(){
		return "创建日期";
	}

	public String getCreateUserIdCN(){
		return "创建人ID";
	}

	public String getUpdateTimeCN(){
		return "更新时间";
	}

	public String getUpdateUserNameCN(){
		return "更新人";
	}

	public String getUpdateUserIdCN(){
		return "更新人ID";
	}


}

