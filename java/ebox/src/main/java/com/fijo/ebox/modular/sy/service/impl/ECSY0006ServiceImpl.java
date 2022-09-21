package com.fijo.ebox.modular.sy.service.impl;
import com.fijo.ebox.base.service.impl.FijoBaseServiceImpl;
import com.fijo.ebox.modular.sy.pojo.ECSY0006;
import com.fijo.ebox.modular.sy .service.ECSY0006Service;
import com.fijo.ebox.modular.sy .mapper.ECSY0006Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
*createTime:2020-05-26 03:34:35
*ECSY0006ServiceImpl
*/
@Service
public class ECSY0006ServiceImpl extends FijoBaseServiceImpl<ECSY0006,Long> implements ECSY0006Service {
	@Autowired
	private ECSY0006Mapper ecsy0006Mapper;

	@Autowired
	public ECSY0006ServiceImpl(ECSY0006Mapper ecsy0006Mapper){
		super(ecsy0006Mapper);
		this.ecsy0006Mapper = ecsy0006Mapper;
	}

	/**
	 * 系统标签详情根据tagTypeCode查询
	 * @param tagTypeCode
	 * @return
	 */
	@Override
	public List<ECSY0006> queryBytagTypeCode(String tagTypeCode) {
		List<ECSY0006> ecsy0006List = ecsy0006Mapper.queryBytagTypeCode(tagTypeCode);
		return ecsy0006List;
	}

}
