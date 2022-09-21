package com.fijo.ebox.modular.sy.pojo;
import com.fijo.ebox.base.pojo.FijoBasePojo;
import lombok.Data;
/**
*SYS_MSG
*ECSY0009 系统消息
**/
@Data
public class ECSY0009 extends FijoBasePojo {
	private Long id; //主键ID

	private String entitiyTypeCode; //实体类型编码

	private Long userId; //用户ID

	private String content; //消息内容

	private Integer msgFrom; //消息来源(1：消息通知，2：预警消息)

	private Boolean state; //是否已读（1：是，2：否）

	private String readDate; //阅读时间

	private Long objectId; //业务ID

	private Boolean phoneMsg; //是否需要发短信(1：是，2：否)

	private Boolean phoneMsgState; //是否已发送（1：是，2：否）

	private String modelParams;//短信模板参数

	private String title;

	private String col1; //预留字段 (短信模板id)

	private String col2; //预留字段

	private String col3; //预留字段

	private String col4; //预留字段

	private String col5; //预留字段

	private String col6; //预留字段

	private String col7; //预留字段

	private String col8; //预留字段

	private String col9; //预留字段

	private String col10; //预留字段



	public String getIdCN(){
		return "主键ID";
	}

	public String getEntitiyTypeCodeCN(){
		return "实体类型编码";
	}

	public String getUserIdCN(){
		return "用户ID";
	}

	public String getContentCN(){
		return "消息内容";
	}

	public String getMsgFromCN(){
		return "消息来源(1：消息通知，2：预警消息)";
	}

	public String getStateCN(){
		return "是否已读（1：是，2：否）";
	}

	public String getReadDateCN(){
		return "阅读时间";
	}

	public String getObjectIdCN(){
		return "业务ID";
	}

	public String getPhoneMsgCN(){
		return "是否需要发短信(1：是，2：否)";
	}

	public String getPhoneMsgStateCN(){
		return "是否已发送（1：是，2：否）";
	}

	public String getCol1CN(){
		return "预留字段";
	}

	public String getCol2CN(){
		return "预留字段";
	}

	public String getCol3CN(){
		return "预留字段";
	}

	public String getCol4CN(){
		return "预留字段";
	}

	public String getCol5CN(){
		return "预留字段";
	}

	public String getCol6CN(){
		return "预留字段";
	}

	public String getCol7CN(){
		return "预留字段";
	}

	public String getCol8CN(){
		return "预留字段";
	}

	public String getCol9CN(){
		return "预留字段";
	}

	public String getCol10CN(){
		return "预留字段";
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

