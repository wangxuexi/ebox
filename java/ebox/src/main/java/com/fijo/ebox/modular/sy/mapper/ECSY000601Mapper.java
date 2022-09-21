package com.fijo.ebox.modular.sy.mapper;


import com.fijo.ebox.base.mapper.FijoBaseMapper;
import com.fijo.ebox.modular.sy.pojo.ECSY0006;
import com.fijo.ebox.modular.sy.pojo.ECSY000601;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * ORG_TAG_CONFIG
 * ECSY000601
 **/
public interface ECSY000601Mapper extends FijoBaseMapper<ECSY000601, Long> {

    /**
     * 查询组织挑选的标签
     * @param orgCode			组织编码
     * @param tagTypeCode		标签类型CODE
     * @param tagName			标签名称
     * @return
     */
    List<ECSY0006> queryOrgChooseTag(@Param("orgCode") String orgCode,
                                     @Param("tagTypeCode") String tagTypeCode,
                                     @Param("tagName") String tagName);

    /**
     * 查询未挑选的标签
     *
     * @param tagTypeCode 标签类型CODE
     * @param tagIds    标签ID集合
     * @param tagName   标签名称
     * @return
     */
    List<ECSY0006> queryUnselectedTag(@Param("tagTypeCode") String tagTypeCode,
                                      @Param("tagIds") String tagIds,
                                      @Param("tagName") String tagName);

    /**
     * 移除组织选择的标签
     *
     * @param orgCode 组织编码
     * @param tagId   标签Id集合
     * @return
     */
    void deleteTagOrgConfig(@Param("orgCode") String orgCode,
                            @Param("tagId") Long tagId);

    /**
     * 查询组织下的标签
     *
     * @param orgCode     组织编码
     * @param tagName     标签名称
     * @param tagTypeName 标签类别名称
     * @return
     */
    List<ECSY0006> queryOrgInTagDataList(@Param("orgCode") String orgCode,
                                         @Param("tagTypeName") String tagTypeName,
                                         @Param("tagName") String tagName);

    /**
     * 查询标签数据
     *
     * @param tagCode 标签编码
     * @return
     */
    ECSY0006 queryTagDataByTagCode(@Param("tagCode") String tagCode);

}

