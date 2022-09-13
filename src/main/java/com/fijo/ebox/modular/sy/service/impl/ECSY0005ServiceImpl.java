package com.fijo.ebox.modular.sy.service.impl;
import com.fijo.ebox.base.service.impl.FijoBaseServiceImpl;
import com.fijo.ebox.modular.sy.pojo.ECSY0005;
import com.fijo.ebox.modular.sy .service.ECSY0005Service;
import com.fijo.ebox.modular.sy .mapper.ECSY0005Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
*createTime:2020-05-26 03:32:19
*ECSY0005ServiceImpl
*/
@Service
public class ECSY0005ServiceImpl extends FijoBaseServiceImpl<ECSY0005,Long> implements ECSY0005Service {
	@Autowired
	private ECSY0005Mapper ecsy0005Mapper;

	@Autowired
	public ECSY0005ServiceImpl(ECSY0005Mapper ecsy0005Mapper){
		super(ecsy0005Mapper);
		this.ecsy0005Mapper = ecsy0005Mapper;
	}

	/**
	 * 系统标签类型条件查询
	 * @param tagTypeCode  标签类型编码
	 * @param tagTypeName  标签类型名称
	 * @return
	 */
	@Override
	public List<ECSY0005> queryBy(String tagTypeCode, String tagTypeName) {
		List<ECSY0005> ecsy0005List = ecsy0005Mapper.queryBy(tagTypeCode,tagTypeName);
		return ecsy0005List;
	}

	/**
	 * 系统标签类型根据code查询
	 * @param tagTypeCode 标签类型编码
	 * @return
	 */
	@Override
	public ECSY0005 queryBYCode(String tagTypeCode) {
		ECSY0005 ecsy0005 = ecsy0005Mapper.queryBYCode(tagTypeCode);
		return ecsy0005;
	}

	//根据标签CODE查询企业标签
    @Override
    public List<Map> queryDetailsByCode(String tagTypeCode) {
		List<Map> mapList = ecsy0005Mapper.queryDetailsByCode(tagTypeCode);
        return mapList;
    }
}
