package com.fijo.ebox.base.pojo;
import lombok.Data;

/**
 *v_sys_custom
 *FJSYSCT0004
 **/
@Data
public class FJSYSCT0004 extends FijoBasePojo {
	private long id; //id
	private String tenant; //租户编码
	private String orgCode; //组织编码
	private Long customFieldId; //
	private Long entyTypeId;//实体类型Id
	private Long objectId; //业务实体ID
	private int fieldSeq; //顺序号
	private String domFieldId; //页面元素字段Id
	private String domFieldClass; //页面元素字段classs
	private String domFieldName; //页面元素字段name
	private String fucRealmName; //触发事件域名
	private String fucServiceName; //目标服务名
	private String fucInterfaceName; //对应接口名
	private String fucInterface; //对应接口英文名
	private byte isRequired; //是否必填项
	private byte isKey; //是否关键字段
	private byte isMust; //是否必须
	private String remark; //备注
	private String fieldName; //字段名称
	private Integer fieldType; //字段类型
	private String defaultValue; //默认值
	private String fieldTips; //填写提示
	private byte isNeedMutivalue; //是否支持多选
	private int maxLen; //最大长度
	private int smallnum; //小数位
	private String value;//自定义字段值
	private String customFieldName;//帆软专用值
}
