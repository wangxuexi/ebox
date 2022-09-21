package com.fijo.ebox.base.pojo;

import lombok.Data;

/**
 * sys_org
 * FJSYSOG0001
 **/
@Data
public class FJSYSOG0001 extends FijoBasePojo {
    private Long id; //ID
    private Long pId; //父id
    private String tenant; //租户编码
    private String name; //组织名称
    private String orgCode; //组织编码
    private Integer level; //组织层级
    private Boolean isVirtual;//是否虚拟组织
    private Integer sort;//排序
    private String remark; //备注


    public String getIdCN() {
        return "ID";
    }

    public String getPIdCN() {
        return "父id";
    }

    public String getTenantCN() {
        return "租户编码";
    }

    public String getNameCN() {
        return "组织名称";
    }

    public String getOrgCodeCN() {
        return "组织编码";
    }

    public String getLevelCN() {
        return "组织层级";
    }

    public String getIsVirtualCN() {
        return "是否虚拟组织";
    }

    public String getSortCN() {
        return "排序";
    }

    public String getRemarkCN() {
        return "备注";
    }

    public String getEnabledCN() {
        return "是否有效";
    }

    public String getRemovedCN() {
        return "是否删除";
    }

    public String getCreateUserIdCN() {
        return "新增人id";
    }

    public String getCreateUserNameCN() {
        return "新增人";
    }

    public String getCreateTimeCN() {
        return "新增时间";
    }

    public String getUpdateUserIdCN() {
        return "更新用户id";
    }

    public String getUpdateUserNameCN() {
        return "更新用户名";
    }

    public String getUpdateTimeCN() {
        return "更新时间";
    }

}
