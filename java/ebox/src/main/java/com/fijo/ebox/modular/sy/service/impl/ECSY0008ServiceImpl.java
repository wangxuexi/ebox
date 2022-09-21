package com.fijo.ebox.modular.sy.service.impl;

import com.fijo.ebox.base.service.impl.FijoBaseServiceImpl;
import com.fijo.ebox.modular.sy.mapper.ECSY0008Mapper;
import com.fijo.ebox.modular.sy.pojo.ECSY0008;
import com.fijo.ebox.modular.sy.service.ECSY0008Service;
import com.fijo.ebox.base.util.common.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * createTime:2020-08-06 02:27:27
 * ECSY0008ServiceImpl
 */
@Service
@Slf4j
public class ECSY0008ServiceImpl extends FijoBaseServiceImpl<ECSY0008, Long> implements ECSY0008Service {
    @Autowired
    private ECSY0008Mapper ecsy0008Mapper;

    @Autowired
    public ECSY0008ServiceImpl(ECSY0008Mapper ecsy0008Mapper) {
        super(ecsy0008Mapper);
        this.ecsy0008Mapper = ecsy0008Mapper;
    }

    /**
     * 通过数据字典id查询数据字典值列表
     *
     * @param ddid 数据字典id
     * @return
     */
    @Override
    public List<ECSY0008> queryDDvalueListByDdid(Long ddid) {
        return ecsy0008Mapper.queryDDvalueListByDdid(ddid);
    }

    /**
     * 通过数据字典id查询数据字典值列表
     *
     * @param ddid    数据字典id
     * @param orgCode 组织编码
     * @return
     */
    @Override
    public List<ECSY0008> queryDDvalueListByDdidAndOrgCode(String ddid, String orgCode) {
        return ecsy0008Mapper.queryDDvalueListByDdidAndOrgCode(ddid, orgCode);
    }

    /**
     * 查询父级字典值列表
     *
     * @param ddid    数据字典id
     * @param orgCode 组织编码
     * @return
     */
    @Override
    public List<ECSY0008> getParentValue(String ddid, String orgCode) {
        return ecsy0008Mapper.getParentValue(ddid, orgCode);
    }

    /**
     * 通过组织删除数据字典值
     *
     * @param orgCode 组织编码
     * @throws Exception
     */
    @Override
    public void deleteByOrgCode(String orgCode) throws Exception {
        ecsy0008Mapper.deleteByOrgCode(orgCode);
    }

    /**
     * 分享全部数据字典值到某个组织
     *
     * @param orgCode    组织编码
     * @param nowOrgCode 当前组织编码
     * @return
     */
    @Override
    @Transactional
    public void shareAllDdvalueList2Org(String orgCode, String nowOrgCode) throws Exception {
        String[] orgCodeArray = orgCode.split(",");
        if (orgCodeArray.length > 0) {
            for (int i = 0; i < orgCodeArray.length; i++) {
                // 1.先将传过来的组织下的所有数据字典值删除
                ecsy0008Mapper.deleteByOrgCode(orgCodeArray[i]);
            }

            // 2.查询没有删除的数据字典值，并且是没有被作废的数据字典值
            List<ECSY0008> ecsy0008List = ecsy0008Mapper.queryAllByOrgCode(nowOrgCode);

            // 3.将查询到的没有被删除的数据字典值新增到传过来的组织中
            if (ecsy0008List.size() > 0) {
                for (int i = 0; i < orgCodeArray.length; i++) {
                    for (ECSY0008 ecsy0008 : ecsy0008List) {
                        ecsy0008.setId(null);
                        ecsy0008.setOrgCode(orgCodeArray[i]);
                        ecsy0008.setParentDdid(null);
                        try {
                            ecsy0008Mapper.insert(ecsy0008);
                        } catch (Exception e) {
                            log.error("【新增】 新增失败，失败原因：{}", e.getMessage());
                            throw new Exception("分享失败！失败原因：{}" + e.getMessage());
                        }
                    }
                }
            }
        }
    }

    /**
     * 分享选中数据字典值到某个组织
     *
     * @param orgCode 组织编码
     * @param ids     数据字典值ids
     * @return
     */
    @Override
    public void shareSelectDdvalueList2Org(String orgCode, String ids) throws Exception {
        if (orgCode != null && orgCode != "" && ids != null && ids != "") {
            String[] orgCodeArray = orgCode.split(",");
            ids = StringUtil.orgCodesToStr(ids);
            List<ECSY0008> ecsy0008List = ecsy0008Mapper.queryAllByIds(ids);

            if (orgCodeArray.length > 0) {
                // 将传过来的数据字典值新增到传过来的组织中
                if (ecsy0008List.size() > 0) {
                    for (int i = 0; i < orgCodeArray.length; i++) {
                        for (ECSY0008 ecsy0008 : ecsy0008List) {
                            ecsy0008.setId(null);
                            ecsy0008.setOrgCode(orgCodeArray[i]);
                            ecsy0008.setParentDdid(null);
                            try {
                                ecsy0008Mapper.insert(ecsy0008);
                            } catch (Exception e) {
                                log.error("【新增】 新增失败，失败原因：{}", e.getMessage());
                                throw new Exception("分享失败！失败原因：{}" + e.getMessage());
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 新增业务信息时用到的数据字典下拉框，需传具体的组织,不返回父级信息
     *
     * @param code    数据字典编码
     * @param orgCode 组织编码
     * @return
     */
    public List<Map> queryDDValuesByCodeForInsert(String code, String orgCode) {
        orgCode = StringUtil.orgCodesToStr(orgCode);
        return ecsy0008Mapper.queryDDValuesByCodeForInsert(code, orgCode);
    }

    /**
     * 通过code查询字典值,不返回父级信息
     *
     * @param code    数据字典编码
     * @param orgCode 组织编码
     * @return
     */
    public List<Map> queryDDValuesByCode(String code, String orgCode) {
        orgCode = StringUtil.orgCodesToStr(orgCode);
        return ecsy0008Mapper.queryDDValuesByCode(code, orgCode);
    }

    /**
     * 通过数据字典编码和组织编码批量查询字典值,不返回父级信息
     *
     * @param code    数据字典编码(多个)
     * @param orgCode 组织编码(多个)
     * @return
     */
    @Override
    public Map batchQueryDDValuesByCode(String code, String orgCode) {
        Map resultMap = new HashMap();
        if (StringUtils.isNotEmpty(code) && StringUtils.isNotEmpty(code)) {
            orgCode = StringUtil.str2SqlStr(orgCode);
            String[] codeArr = code.split(",");

            for (String dCode : codeArr) {
                List<Map> result = ecsy0008Mapper.batchQueryDDValuesByCode(dCode, orgCode);
                resultMap.put(dCode, result);
            }
        }
        return resultMap;
    }

    /**
     * 新增业务信息时用到的数据字典下拉框，需传具体的组织,不返回父级信息
     *
     * @param code    数据字典编码(多个)
     * @param orgCode 组织编码(多个)
     * @return
     */
    @Override
    public Map batchQueryDDValuesByCodeForInsert(String code, String orgCode) {
        Map resultMap = new HashMap();
        if (StringUtils.isNotEmpty(code) && StringUtils.isNotEmpty(code)) {
            orgCode = StringUtil.str2SqlStr(orgCode);
            String[] codeArr = code.split(",");

            for (String dCode : codeArr) {
                List<Map> result = ecsy0008Mapper.batchQueryDDValuesByCodeForInsert(dCode, orgCode);
                resultMap.put(dCode, result);
            }
        }
        return resultMap;
    }

    /**
     * 查询数据字典值
     * @param ddId
     * @param orgCode
     * @return
     */
    @Override
    public List<ECSY0008> queryAllDDvalueListByDdidAndOrgCode(Long ddId, String orgCode) {
        return ecsy0008Mapper.queryAllDDvalueListByDdidAndOrgCode(ddId, orgCode);
    }
}
