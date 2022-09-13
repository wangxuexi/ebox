package com.fijo.ebox.modular.sy.service.impl;
import com.fijo.ebox.base.service.impl.FijoBaseServiceImpl;
import com.fijo.ebox.modular.sy.pojo.ECSY0009;
import com.fijo.ebox.modular.sy.service.ECSY0009Service;
import com.fijo.ebox.modular.sy.mapper.ECSY0009Mapper;
import com.fijo.ebox.base.util.common.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *SYS_MSG
 *ECSY0009
 **/
@Service
public class ECSY0009ServiceImpl extends FijoBaseServiceImpl<ECSY0009,Long> implements ECSY0009Service {
	@Autowired
	private ECSY0009Mapper ecsy0009Mapper;

	@Autowired
	public ECSY0009ServiceImpl(ECSY0009Mapper ecsy0009Mapper){
		super(ecsy0009Mapper);
		this.ecsy0009Mapper = ecsy0009Mapper;
	}

	@Override
	public Integer updateState(Long Id,Long userId) {

		return ecsy0009Mapper.updateState(DateUtils.getNowDateYMDHMS(),Id,userId);
	}

	@Override
	public void updateMsgState(String updateTime, String id) {
		ecsy0009Mapper.updateMsgState(updateTime,id);
	}

    @Override
    public List<ECSY0009> queryMsgBySrare(ECSY0009 ecsy0009) {
        return ecsy0009Mapper.queryMsgBySrare(ecsy0009);
    }

    @Override
    public List<ECSY0009> getMsgListByUser(ECSY0009 ecsy0009) {

		return ecsy0009Mapper.getMsgListByUser(ecsy0009);
    }

	@Override
	public void updateMsgReadState(String readDate, String id) {
		ecsy0009Mapper.updateMsgReadState(readDate,id);
	}
}

