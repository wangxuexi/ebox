package com.fijo.ebox.modular.sy.service;

import com.fijo.ebox.base.service.FijoBaseService;
import com.fijo.ebox.modular.sy.pojo.ECSY0007;

import java.util.List;
import java.util.Map;


/**
 * createTime:2020-08-06 02:27:03
 * ECSY0007Service
 */
public interface ECSY0007Service extends FijoBaseService<ECSY0007, Long> {
    /**
     * 通过租户查询数据字典
     *
     * @param tenant 租户
     * @return
     */
    List<Map> queryListByTenant(String tenant);

    /**
     * 通过租户和数据字典编码查询数据字典
     *
     * @param tenant 租户
     * @param code   数据字典编码
     * @return
     */
    List<ECSY0007> queryListByTenantAndCode(String tenant, String code);

    /**
     * 通过租户和数据字典编码查询全部的数据字典
     * @param tenant
     * @param ddCode
     * @return
     */
    List<ECSY0007> queryAllDataListByTenantAndCode(String tenant, String ddCode);
}
