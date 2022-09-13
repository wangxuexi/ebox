package com.fijo.ebox.modular.sy.service.impl;


import com.fijo.ebox.base.service.impl.FijoBaseServiceImpl;
import com.fijo.ebox.base.util.common.StringUtil;
import com.fijo.ebox.modular.sy.mapper.ECSY000601Mapper;
import com.fijo.ebox.modular.sy.pojo.ECSY0006;
import com.fijo.ebox.modular.sy.pojo.ECSY000601;
import com.fijo.ebox.modular.sy.service.ECSY000601Service;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 *ORG_TAG_CONFIG
 *ECSY000601
 **/
@Service
public class ECSY000601ServiceImpl extends FijoBaseServiceImpl<ECSY000601,Long> implements ECSY000601Service {
	@Autowired
	private ECSY000601Mapper ecsy000601Mapper;

	@Autowired
	public ECSY000601ServiceImpl(ECSY000601Mapper ecsy000601Mapper){
		super(ecsy000601Mapper);
		this.ecsy000601Mapper = ecsy000601Mapper;
	}

	/**
	 * 分页查询
	 * @param orgCode 组织编码
	 * @param pageNo 页码
	 * @param pageSize 页长
	 * @return
	 */
	@Override
	public Map<String, Object> queryAllByPage(String orgCode,Integer pageNo,Integer pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		orgCode = StringUtil.str2SqlStr(orgCode);
		List<ECSY000601> resultList =  ecsy000601Mapper.queryAll();
		Map<String, Object> dataMap = wrapQueryResult(resultList);
		return dataMap;
	}

	/**
	 * 查询组织挑选的标签
	 * @param orgCode			组织编码
	 * @param tagTypeCode		标签类型CODE
	 * @param tagName			标签名称
	 * @return
	 */
	@Override
	public List<ECSY0006> queryOrgChooseTag(String orgCode, String tagTypeCode, String tagName) {
		List<ECSY0006> ecsy0006List = ecsy000601Mapper.queryOrgChooseTag(orgCode,tagTypeCode,tagName);
		return ecsy0006List;
	}

	/**
	 * 查询未挑选的标签
	 *
	 * @param tagTypeCode 标签类型CODE
	 * @param tagIds    标签ID集合
	 * @param tagName   标签名称
	 * @return
	 */
    @Override
    public List<ECSY0006> queryUnselectedTag(String tagTypeCode, String tagIds, String tagName) {
		List<ECSY0006> ecsy0006List = ecsy000601Mapper.queryUnselectedTag(tagTypeCode,tagIds,tagName);
    	return ecsy0006List;
    }

	/**
	 * 移除组织选择的标签
	 * @param orgCode	组织编码
	 * @param tagId	标签Id集合
	 * @return
	 */
	@Override
	public void deleteTagOrgConfig(String orgCode, Long tagId) {
		ecsy000601Mapper.deleteTagOrgConfig(orgCode,tagId);
	}

	/**
	 * 查询组织下的标签
	 * @param orgCode		组织编码
	 * @param tagName		标签名称
	 * @param tagTypeName	标签类别名称
	 * @return
	 */
	@Override
	public List<ECSY0006> queryOrgInTagDataList(String orgCode, String tagTypeName, String tagName) {
		List<ECSY0006> ecsy0006List = ecsy000601Mapper.queryOrgInTagDataList(orgCode,tagTypeName,tagName);
		return ecsy0006List;
	}


	/**
	 * 查询标签数据
	 * @param tagCode   标签编码
	 * @return
	 */
    @Override
    public ECSY0006 queryTagDataByTagCode(String tagCode) {
    	ECSY0006 ecsy0006 = ecsy000601Mapper.queryTagDataByTagCode(tagCode);
        return ecsy0006;
    }

}

