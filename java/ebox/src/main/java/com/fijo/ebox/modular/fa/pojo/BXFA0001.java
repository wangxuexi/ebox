package com.fijo.ebox.modular.fa.pojo;
import com.fijo.ebox.base.pojo.FijoBasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
*FA_BASE_INFO
*BXFA0001
**/
@Data
@ApiModel
public class BXFA0001 extends FijoBasePojo {
	@ApiModelProperty(value = "id")
	private Long id; //id

	@ApiModelProperty(value = "姓名")
	private String name; //姓名

	@ApiModelProperty(value = "工号")
	private String jobNo; //工号

	@ApiModelProperty(value = "性别")
	private Integer sax; //性别

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

	@ApiModelProperty(value = "分配结果字符串")
	private String distributeResultStr;//分配结果字符串




	private String getIdCN(){
		return "id";
	}

	private String getNameCN(){
		return "姓名";
	}

	private String getJobNoCN(){
		return "工号";
	}

	private String getSaxCN(){
		return "性别";
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

