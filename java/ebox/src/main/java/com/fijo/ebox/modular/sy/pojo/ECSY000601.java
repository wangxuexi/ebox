package com.fijo.ebox.modular.sy.pojo;
import com.fijo.ebox.base.pojo.FijoBasePojo;
import lombok.Data;

/**
*ORG_TAG_CONFIG
*ECSY000601
**/
@Data
public class ECSY000601 extends FijoBasePojo {
	private Long id; //ID

	private String orgCode; //组织编码

	private String tagTypeCode; //标签类型编码

	private Long tagId; //标签ID

	private String tagCode; //标签编码

	private Integer sort; //排序

	private String remarks; //备注

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



	public String getIdCN(){
		return "ID";
	}

	public String getOrgCodeCN(){
		return "组织编码";
	}

	public String getTagTypeCodeCN(){
		return "标签类型编码";
	}

	public String getTagIdCN(){
		return "标签ID";
	}

	public String getTagCodeCN(){
		return "标签编码";
	}

	public String getSortCN(){
		return "排序";
	}

	public String getRemarksCN(){
		return "备注";
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

	public String getCreateUserIdCN(){
		return "创建人ID";
	}

	public String getCreateUserNameCN(){
		return "创建人";
	}

	public String getCreateTimeCN(){
		return "创建时间";
	}

	public String getUpdateUserIdCN(){
		return "更新人ID";
	}

	public String getUpdateUserNameCN(){
		return "更新人";
	}

	public String getUpdateTimeCN(){
		return "更新时间";
	}


}

