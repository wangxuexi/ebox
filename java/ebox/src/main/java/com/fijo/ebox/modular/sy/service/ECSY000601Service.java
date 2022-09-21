package com.fijo.ebox.modular.sy.service;


import com.fijo.ebox.base.service.FijoBaseService;
import com.fijo.ebox.modular.sy.pojo.ECSY0006;
import com.fijo.ebox.modular.sy.pojo.ECSY000601;

import java.util.List;
import java.util.Map;

/**
 *ORG_TAG_CONFIG
 *ECSY000601
 **/
public interface ECSY000601Service extends FijoBaseService<ECSY000601,Long> {

    /**
     * 分页查询
     * @param orgCode 组织编码
     * @param pageNo 页码
     * @param pageSize 页长
     * @return
     */
    Map<String,Object> queryAllByPage(String orgCode,Integer pageNo,Integer pageSize);

    /**
     * 查询组织挑选的标签
     * @param orgCode			组织编码
     * @param tagTypeCode		标签类型CODE
     * @param tagName			标签名称
     * @return
     */
    List<ECSY0006> queryOrgChooseTag(String orgCode, String tagTypeCode, String tagName);

    /**
     * 查询未挑选的标签
     *
     * @param tagTypeCode 标签类型CODE
     * @param tagIds    标签ID集合
     * @param tagName   标签名称
     * @return
     */
    List<ECSY0006> queryUnselectedTag(String tagTypeCode, String tagIds, String tagName);

    /**
     * 移除组织选择的标签
     * @param orgCode	组织编码
     * @param tagId	标签Id集合
     * @return
     */
    void deleteTagOrgConfig(String orgCode, Long tagId);

    /**
     * 查询组织下的标签
     * @param orgCode		组织编码
     * @param tagName		标签名称
     * @param tagTypeName	标签类别名称
     * @return
     */
    List<ECSY0006> queryOrgInTagDataList(String orgCode, String tagTypeName, String tagName);

    /**
     * 查询标签数据
     * @param tagCode   标签编码
     * @return
     */
    ECSY0006 queryTagDataByTagCode(String tagCode);

}

