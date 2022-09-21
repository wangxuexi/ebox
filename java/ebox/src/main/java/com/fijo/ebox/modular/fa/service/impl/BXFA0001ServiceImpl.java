package com.fijo.ebox.modular.fa.service.impl;
import com.fijo.ebox.base.service.impl.FijoBaseServiceImpl;
import com.fijo.ebox.modular.fa.pojo.BXFA0001;
import com.fijo.ebox.modular.fa.service.BXFA0001Service;
import com.fijo.ebox.modular.fa.mapper.BXFA0001Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.fijo.ebox.base.util.common.StringUtil;
import java.util.List;
import java.util.Map;


/**
 *FA_BASE_INFO
 *BXFA0001
 **/
@Service
public class BXFA0001ServiceImpl extends FijoBaseServiceImpl<BXFA0001,Long> implements BXFA0001Service {
	@Autowired
	private BXFA0001Mapper bxfa0001Mapper;

	@Autowired
	public BXFA0001ServiceImpl(BXFA0001Mapper bxfa0001Mapper){
		super(bxfa0001Mapper);
		this.bxfa0001Mapper = bxfa0001Mapper;
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
		List<BXFA0001> resultList =  bxfa0001Mapper.queryAll();
		Map<String, Object> dataMap = wrapQueryResult(resultList);
		return dataMap;
	}

}

