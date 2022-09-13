package com.fijo.ebox.modular.sy.pojo;

import com.fijo.ebox.base.pojo.FijoBasePojo;
import lombok.Data;

/**
 * SYS_DDVALUES 数据字典值
 * ECSY0008
 **/
@Data
public class ECSY0008 extends FijoBasePojo {
    private Long id; //
    private Integer ddid; //字典id
    private Long parentDdid; //父项字典值ID
    private String orgCode; //组织编码
    private String code; //编码,冗余
    private String name; //值
    private String remark; //描述
    private Integer sortNo; //排序码，步长为5
    private Integer isToVoid; //是否作废1=是，0=否

    public String getIdCN() {
        return "";
    }

    public String getDdidCN() {
        return "字典id";
    }

    public String getParentDdidCN() {
        return "父项字典值ID";
    }

    public String getOrgCodeCN() {
        return "组织编码";
    }

    public String getCodeCN() {
        return "编码,冗余";
    }

    public String getNameCN() {
        return "值";
    }

    public String getRemarkCN() {
        return "描述";
    }

    public String getSortNoCN() {
        return "排序码，步长为5";
    }

    public String getIsToVoidCN() {
        return "是否作废1=是，0=否";
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

}
