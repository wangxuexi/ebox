package com.fijo.ebox.modular.sy.pojo;
import com.fijo.ebox.base.pojo.FijoBasePojo;
import lombok.Data;
/**
*SYS_TAG_INFO
*ECSY0006 标签详情
**/
@Data
public class ECSY0006 extends FijoBasePojo {
  private Long id; //
  private String tagTypeCode; //标签类型code
  private String tagCode; //标签编码
  private String tagName; //标签名称
  private String textColor; //文本颜色
  private String backgroundColor; //背景颜色
  private String remark; //备注

  private Integer sort; //排序

  private String tagTypeName; //标签类型名称
  private Integer entNumber; //关联企业数
  private String orgCode; //组织编码
  private Integer choiceType; //选择类型（1：单选，2：多选）
}
