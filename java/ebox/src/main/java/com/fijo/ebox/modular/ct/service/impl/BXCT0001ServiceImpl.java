package com.fijo.ebox.modular.ct.service.impl;
import com.fijo.ebox.base.service.impl.FijoBaseServiceImpl;
import com.fijo.ebox.modular.ct.pojo.BXCT0001;
import com.fijo.ebox.modular.ct.service.BXCT0001Service;
import com.fijo.ebox.modular.ct.mapper.BXCT0001Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.fijo.ebox.base.util.common.StringUtil;
import java.util.List;
import java.util.Map;


/**
 *CIGARETTE_BASE_INFO
 *BXCT0001
 **/
@Service
public class BXCT0001ServiceImpl extends FijoBaseServiceImpl<BXCT0001,Long> implements BXCT0001Service {
	@Autowired
	private BXCT0001Mapper bxct0001Mapper;

	@Autowired
	public BXCT0001ServiceImpl(BXCT0001Mapper bxct0001Mapper){
		super(bxct0001Mapper);
		this.bxct0001Mapper = bxct0001Mapper;
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
		List<BXCT0001> resultList =  bxct0001Mapper.queryAll();
		Map<String, Object> dataMap = wrapQueryResult(resultList);
		return dataMap;
	}

}

