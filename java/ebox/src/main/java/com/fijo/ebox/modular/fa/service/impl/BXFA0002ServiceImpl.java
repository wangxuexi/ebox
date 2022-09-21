package com.fijo.ebox.modular.fa.service.impl;
import com.fijo.ebox.base.service.impl.FijoBaseServiceImpl;
import com.fijo.ebox.base.util.common.DateUtils;
import com.fijo.ebox.base.util.plat.PlatUtil;
import com.fijo.ebox.dto.ResultUserDto;
import com.fijo.ebox.modular.fa.pojo.BXFA0002;
import com.fijo.ebox.modular.fa.service.BXFA0002Service;
import com.fijo.ebox.modular.fa.mapper.BXFA0002Mapper;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.fijo.ebox.base.util.common.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 *POLICY_BASE_INFO
 *BXFA0002
 **/
@Service
public class BXFA0002ServiceImpl extends FijoBaseServiceImpl<BXFA0002,Long> implements BXFA0002Service {
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private BXFA0002Mapper bxfa0002Mapper;

	@Autowired
	public BXFA0002ServiceImpl(BXFA0002Mapper bxfa0002Mapper){
		super(bxfa0002Mapper);
		this.bxfa0002Mapper = bxfa0002Mapper;
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
		List<BXFA0002> resultList =  bxfa0002Mapper.queryAll();
		Map<String, Object> dataMap = wrapQueryResult(resultList);
		return dataMap;
	}


	/**
	 * 根据是否分配类型，查询保单
	 * @param isDistributeFa
	 * @return
	 */
	@Override
	public List<BXFA0002> queryListByDistributeFaType(Boolean isDistributeFa) {
		return bxfa0002Mapper.queryListByDistributeFaType(isDistributeFa);
	}


	/**
	 * 批量更新分配信息
	 * @param ids
	 * @param distributeFaId
	 * @param distributeFaNo
	 * @param distributeFaName
	 */
	@Override
	public void batchUpdateDistributeInfo(String ids, Long distributeFaId, String distributeFaNo, String distributeFaName) {
		ids = StringUtil.str2SqlStr(ids);
		ResultUserDto loginUser = PlatUtil.getLoginUser(request);
		loginUser = loginUser == null ? new ResultUserDto() : loginUser;
		Long userId = loginUser.getUId() == null ? new Long(-1) : loginUser.getUId();
		String userName = StringUtils.isNullOrEmpty(loginUser.getNickName()) ? "*" : loginUser.getNickName();
		String operateTime = DateUtils.getNowDateYMDHMS();
		bxfa0002Mapper.batchUpdateDistributeInfo(ids, distributeFaId, distributeFaNo, distributeFaName,userId,userName,operateTime);
	}

	/**
	 * 批量设置是否分配fa
	 * @param ids
	 * @param isDistributeFa
	 */
	@Override
	public void batchSetIsDistributeFa(String ids, Boolean isDistributeFa) {
		ids = StringUtil.str2SqlStr(ids);
		ResultUserDto loginUser = PlatUtil.getLoginUser(request);
		loginUser = loginUser == null ? new ResultUserDto() : loginUser;
		Long userId = loginUser.getUId() == null ? new Long(-1) : loginUser.getUId();
		String userName = StringUtils.isNullOrEmpty(loginUser.getNickName()) ? "*" : loginUser.getNickName();
		String operateTime = DateUtils.getNowDateYMDHMS();
		bxfa0002Mapper.batchSetIsDistributeFa(ids, isDistributeFa,userId,userName,operateTime);

	}

}

