package com.fijo.ebox.modular.sy.mapper;

import com.fijo.ebox.base.mapper.FijoBaseMapper;
import com.fijo.ebox.modular.sy.pojo.ECSY0008;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * createTime:2020-08-06 02:27:27
 * ECSY0008Mapper
 */
public interface ECSY0008Mapper extends FijoBaseMapper<ECSY0008, Long> {
    /**
     * 通过数据字典id查询数据字典值列表
     *
     * @param ddid 数据字典id
     * @return
     */
    List<ECSY0008> queryDDvalueListByDdid(Long ddid);

    /**
     * 通过数据字典id查询数据字典值列表
     *
     * @param ddid    数据字典id
     * @param orgCode 组织编码
     * @return
     */
    List<ECSY0008> queryDDvalueListByDdidAndOrgCode(String ddid, String orgCode);

    /**
     * 查询父级字典值列表
     *
     * @param ddid    数据字典id
     * @param orgCode 组织编码
     * @return
     */
    List<ECSY0008> getParentValue(String ddid, String orgCode);

    /**
     * 通过组织编码查询数据字典值
     *
     * @param orgCode 组织编码
     * @return
     */
    List<ECSY0008> queryAllByOrgCode(@Param("orgCode") String orgCode);

    /**
     * 通过数据字典id查询数据字典值列表
     *
     * @param ids 数据字典值ids
     * @return
     */
    List<ECSY0008> queryAllByIds(@Param("ids") String ids);

    /**
     * 通过组织删除数据字典值
     *
     * @param orgCode 组织编码
     * @throws Exception
     */
    void deleteByOrgCode(String orgCode) throws Exception;

    /**
     * 通过code查询字典值,不返回父级信息
     *
     * @param code    数据字典编码
     * @param orgCode 组织编码
     * @return
     */
    List<Map> queryDDValuesByCode(String code, String orgCode);

    /**
     * 新增业务信息时用到的数据字典下拉框，需传具体的组织,不返回父级信息
     *
     * @param code    数据字典编码
     * @param orgCode 组织编码
     * @return
     */
    List<Map> queryDDValuesByCodeForInsert(String code, String orgCode);

    /**
     * 通过数据字典编码和组织编码批量查询字典值,不返回父级信息
     *
     * @param code    数据字典编码(多个)
     * @param orgCode 组织编码(多个)
     * @return
     */
    List<Map> batchQueryDDValuesByCode(@Param("code") String code, @Param("orgCode") String orgCode);

    /**
     * 新增业务信息时用到的数据字典下拉框，需传具体的组织,不返回父级信息
     *
     * @param code    数据字典编码(多个)
     * @param orgCode 组织编码(多个)
     * @return
     */
    List<Map> batchQueryDDValuesByCodeForInsert(@Param("code") String code, @Param("orgCode") String orgCode);

    /**
     * 查询数据字典值
     * @param ddId
     * @param orgCode
     * @return
     */
    List<ECSY0008> queryAllDDvalueListByDdidAndOrgCode(@Param("ddId") Long ddId,
                                                       @Param("orgCode")  String orgCode);
}
