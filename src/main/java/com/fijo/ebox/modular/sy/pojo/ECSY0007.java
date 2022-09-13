package com.fijo.ebox.modular.sy.pojo;

import com.fijo.ebox.base.pojo.FijoBasePojo;
import lombok.Data;

/**
 * SYS_DATADICTIONARY 数据字典
 * ECSY0007
 **/
@Data
public class ECSY0007 extends FijoBasePojo {
    private Long id; //
    private Integer category; //分类 0:类型            1：数据字典
    private Integer level; //层级
    private String code; //编码
    private String name; //名称
    private Long parentId; //父级id
    private String remark; //描述
    private String tenant; //租户编码
    private String orgCode; //组织编码
    private Integer sortNo; //排序码，步长为5

    public String getIdCN() {
        return "";
    }

    public String getCategoryCN() {
        return "分类 0:类型            1：数据字典";
    }

    public String getLevelCN() {
        return "层级";
    }

    public String getCodeCN() {
        return "编码";
    }

    public String getNameCN() {
        return "名称";
    }

    public String getParentIdCN() {
        return "父级id";
    }

    public String getRemarkCN() {
        return "描述";
    }

    public String getTenantCN() {
        return "租户编码";
    }

    public String getOrgCodeCN() {
        return "组织编码";
    }

    public String getEnabledCN() {
        return "是否有效";
    }

    public String getRemovedCN() {
        return "是否删除";
    }

    public String getCreateUserIdCN() {
        return "创建人ID";
    }

    public String getCreateUserNameCN() {
        return "创建人";
    }

    public String getCreateTimeCN() {
        return "创建时间";
    }

    public String getUpdateUserIdCN() {
        return "更新人ID";
    }

    public String getUpdateUserNameCN() {
        return "更新人";
    }

    public String getUpdateTimeCN() {
        return "更新时间";
    }

    public String getSortNoCN() {
        return "排序码，步长为5";
    }

}
