package com.fijo.ebox.modular.sy.pojo;
import com.fijo.ebox.base.pojo.FijoBasePojo;
import lombok.Data;
/**
*SYS_DDVALUE_CONFIG 数据字典使用位置配置
*ECSY0004
**/
@Data
public class ECSY0004 extends FijoBasePojo {
  private Long id; //ID
  private String tableNameCn; //表中文名
  private String tableName; //表名
  private String ddIdField; //数据字典值id字段名
  private String ddNameField; //数据字典值名称字段名
  private String ddCode; //数据字典编码
  private Integer isConnectStr; //是否拼接
  private String col1; //预留字段1
  private String col2; //预留字段2
  private String col3; //预留字段3
  private String col4; //预留字段4
  private String col5; //预留字段5

  private String isConnectStrString;//导出时用 是否拼接
}
