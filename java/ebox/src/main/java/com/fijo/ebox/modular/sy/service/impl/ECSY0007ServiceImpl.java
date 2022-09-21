package com.fijo.ebox.modular.sy.service.impl;

import com.fijo.ebox.base.service.impl.FijoBaseServiceImpl;
import com.fijo.ebox.modular.sy.pojo.ECSY0007;
import com.fijo.ebox.modular.sy.service.ECSY0007Service;
import com.fijo.ebox.modular.sy.mapper.ECSY0007Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * createTime:2020-08-06 02:27:03
 * ECSY0007ServiceImpl
 */
@Service
public class ECSY0007ServiceImpl extends FijoBaseServiceImpl<ECSY0007, Long> implements ECSY0007Service {
    @Autowired
    private ECSY0007Mapper ecsy0007Mapper;

    @Autowired
    public ECSY0007ServiceImpl(ECSY0007Mapper ecsy0007Mapper) {
        super(ecsy0007Mapper);
        this.ecsy0007Mapper = ecsy0007Mapper;
    }

	/**
	 * 通过租户查询数据字典
	 *
	 * @param tenant 租户
	 * @return
	 */
	@Override
	public List<Map> queryListByTenant(String tenant) {
		return ecsy0007Mapper.queryListByTenant(tenant);
	}

	/**
	 * 通过租户和数据字典编码查询数据字典
	 *
	 * @param tenant 租户
	 * @param code   数据字典编码
	 * @return
	 */
	@Override
	public List<ECSY0007> queryListByTenantAndCode(String tenant, String code) {
		return ecsy0007Mapper.queryListByTenantAndCode(tenant, code);
	}

	/**
	 * 通过租户和数据字典编码查询全部的数据字典
	 * @param tenant
	 * @param ddCode
	 * @return
	 */
    @Override
    public List<ECSY0007> queryAllDataListByTenantAndCode(String tenant, String ddCode) {
		return ecsy0007Mapper.queryAllDataListByTenantAndCode(tenant, ddCode);
    }
}
