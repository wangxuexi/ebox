package com.fijo.ebox.modular.sy.service.impl;
import com.fijo.ebox.base.service.impl.FijoBaseServiceImpl;
import com.fijo.ebox.modular.sy.pojo.ECSY0004;
import com.fijo.ebox.modular.sy.service.ECSY0004Service;
import com.fijo.ebox.modular.sy.mapper.ECSY0004Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
*createTime:2020-05-25 07:08:27
*ECSY0004ServiceImpl
*/
@Service
public class ECSY0004ServiceImpl extends FijoBaseServiceImpl<ECSY0004,Long> implements ECSY0004Service {
	@Autowired
	private ECSY0004Mapper ecsy0004Mapper;

	@Autowired
	public ECSY0004ServiceImpl(ECSY0004Mapper ecsy0004Mapper){
		super(ecsy0004Mapper);
		this.ecsy0004Mapper = ecsy0004Mapper;
	}

	/**
	 * 首页表查询
	 * @param ecsy0004
	 * @return
	 */
	@Override
	public List<ECSY0004> queryBy(ECSY0004 ecsy0004) {
		List<ECSY0004> ecsy0004List = ecsy0004Mapper.queryBy(ecsy0004);
		return ecsy0004List;
	}
}
