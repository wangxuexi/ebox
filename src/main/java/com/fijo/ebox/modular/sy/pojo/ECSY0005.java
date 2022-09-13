package com.fijo.ebox.modular.sy.pojo;
import com.fijo.ebox.base.pojo.FijoBasePojo;
import lombok.Data;
/**
*SYS_TAG_TYPE
*ECSY0005 系统标签
**/
@Data
public class ECSY0005 extends FijoBasePojo {
  private Long id; //
  private String tagTypeCode; //标签类型编码
  private String tagTypeName; //标签类型名称
  private String roundType; //圆角样式
  private String markType; //标记样式
  private String plainType; //空心样式
  private String tagSize; //标签大小
  private String defaultTextColor; //默认文本颜色
  private String defaultBackgroundColor; //默认背景颜色
  private String remark; //备注

  private Integer sort; //排序
  private Integer choiceType; //选择类型（1：单选，2：多选）

  private String textColor;//文本颜色
  private String backgroundColor;//背景颜色
  private String tagCode; //标签编码
  private String tagName; //标签名称
}
