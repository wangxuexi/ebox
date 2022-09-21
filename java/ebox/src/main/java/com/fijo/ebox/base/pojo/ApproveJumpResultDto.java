package com.fijo.ebox.base.pojo;
import lombok.Data;

/**
*sys_approve_state_jump
*FJSYSAR0004
**/
@Data
public class ApproveJumpResultDto {
  private Integer code;//状态码

  private Long pboId;//单据id

  private Integer approveStateCode;//审批状态编码

  private String startStateName;//原始状态

  private String tagStateName;//目标状态名

  private String eventName;//触发事件名

  private String eventCode;//触发事件编码

  private String secretKey;//触发后置事件的秘钥

  private String msg;//提示消息
}
