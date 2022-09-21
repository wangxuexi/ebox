package com.fijo.ebox.modular.fa.pojo;
import com.fijo.ebox.base.pojo.FijoBasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
*POLICY_BASE_INFO
*BXFA0002
**/
@Data
@ApiModel
public class BXFA0002 extends FijoBasePojo {
	@ApiModelProperty(value = "id")
	private Long id; //id

	@ApiModelProperty(value = "保单月份")
	private String policyMonth; //保单月份

	@ApiModelProperty(value = "保单名称")
	private String policyName; //保单名称

	@ApiModelProperty(value = "保单编号")
	private String policyCode; //保单编号

	@ApiModelProperty(value = "保单金额")
	private Double policyAmount; //保单金额

	@ApiModelProperty(value = "投保人姓名")
	private String policyHolderName; //投保人姓名

	@ApiModelProperty(value = "被保人姓名")
	private String insuredName; //被保人姓名

	@ApiModelProperty(value = "所属FAID")
	private Long distributeFaId; //所属FAID

	@ApiModelProperty(value = "所属FA工号")
	private String distributeFaNo; //所属FA工号

	@ApiModelProperty(value = "所属FA姓名")
	private String distributeFaName; //所属FA姓名

	@ApiModelProperty(value = "是否已被分配")
	private Boolean isDistributeFa; //是否已被分配

	@ApiModelProperty(value = "预留字段1")
	private String col1; //预留字段1

	@ApiModelProperty(value = "预留字段2")
	private String col2; //预留字段2

	@ApiModelProperty(value = "预留字段3")
	private String col3; //预留字段3

	@ApiModelProperty(value = "预留字段4")
	private String col4; //预留字段4

	@ApiModelProperty(value = "预留字段5")
	private String col5; //预留字段5

	@ApiModelProperty(value = "预留字段6")
	private String col6; //预留字段6

	@ApiModelProperty(value = "预留字段7")
	private String col7; //预留字段7

	@ApiModelProperty(value = "预留字段8")
	private String col8; //预留字段8

	@ApiModelProperty(value = "预留字段9")
	private String col9; //预留字段9

	@ApiModelProperty(value = "预留字段10")
	private String col10; //预留字段10

	@ApiModelProperty(value = "排序")
	private Integer sort; //排序

	@ApiModelProperty(value = "备注")
	private String remark; //备注




	private String getIdCN(){
		return "id";
	}

	private String getPolicyMonthCN(){
		return "保单月份";
	}

	private String getPolicyNameCN(){
		return "保单名称";
	}

	private String getPolicyCodeCN(){
		return "保单编号";
	}

	private String getPolicyAmountCN(){
		return "保单金额";
	}

	private String getPolicyHolderNameCN(){
		return "投保人姓名";
	}

	private String getInsuredNameCN(){
		return "被保人姓名";
	}

	private String getDistributeFaIdCN(){
		return "所属FAID";
	}

	private String getDistributeFaNoCN(){
		return "所属FA工号";
	}

	private String getDistributeFaNameCN(){
		return "所属FA姓名";
	}

	private String getIsDistributeFaCN(){
		return "是否已被分配";
	}

	private String getCol1CN(){
		return "预留字段1";
	}

	private String getCol2CN(){
		return "预留字段2";
	}

	private String getCol3CN(){
		return "预留字段3";
	}

	private String getCol4CN(){
		return "预留字段4";
	}

	private String getCol5CN(){
		return "预留字段5";
	}

	private String getCol6CN(){
		return "预留字段6";
	}

	private String getCol7CN(){
		return "预留字段7";
	}

	private String getCol8CN(){
		return "预留字段8";
	}

	private String getCol9CN(){
		return "预留字段9";
	}

	private String getCol10CN(){
		return "预留字段10";
	}

	private String getSortCN(){
		return "排序";
	}

	private String getRemarkCN(){
		return "备注";
	}

	private String getEnabledCN(){
		return "是否有效";
	}

	private String getRemovedCN(){
		return "是否删除";
	}

	private String getCreateUserIdCN(){
		return "创建人ID";
	}

	private String getCreateUserNameCN(){
		return "创建人姓名";
	}

	private String getCreateTimeCN(){
		return "创建时间";
	}

	private String getUpdateUserIdCN(){
		return "更新人ID";
	}

	private String getUpdateUserNameCN(){
		return "更新人姓名";
	}

	private String getUpdateTimeCN(){
		return "更新时间";
	}


}

