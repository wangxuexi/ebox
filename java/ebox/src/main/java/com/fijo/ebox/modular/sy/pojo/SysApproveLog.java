package com.fijo.ebox.modular.sy.pojo;
import lombok.Data;
/**
*SYS_APPROVE_LOG 审批记录
*SysApproveLog
**/
@Data
public class SysApproveLog {
  private Integer id; //
  private Long userId; //审批人ID
  private String userName; //审批人姓名
  private String operateTime; //审批时间
  private String typeCode; //审批类型编码
  private String typeName; //审批类型名称
  private Long objectId; //审批对象ID
  private String objectName; //审批对象名称
  private String taskName; //审批环节
  private String taskResult; //审批结果
  private String taskComment; //审批意见
  private String remarks;//备注
  private Long createUserId; //创建人ID
  private String createUserName; //创建人
  private String createTime; //创建时间
  private Long updateUserId; //更新人ID
  private String updateUserName; //更新人
  private String updateTime; //更新时间
  private String orderByClause; //排序规则
}
