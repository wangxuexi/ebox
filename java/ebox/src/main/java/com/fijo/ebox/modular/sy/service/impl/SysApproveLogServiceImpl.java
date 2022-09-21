package com.fijo.ebox.modular.sy.service.impl;
import com.fijo.ebox.modular.sy.pojo.SysApproveLog;
import com.fijo.ebox.modular.sy.service.SysApproveLogService;
import com.fijo.ebox.modular.sy.mapper.SysApproveLogMapper;
import com.fijo.ebox.base.util.common.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
*createTime:2019-12-16 15:28:23
*SysApproveLogServiceImpl
*/
@Service
public class SysApproveLogServiceImpl implements SysApproveLogService {
	@Autowired
	private SysApproveLogMapper sysapprovelogMapper;

	@Override
	public List<SysApproveLog> queryAll() {
		return sysapprovelogMapper.queryAll();
	}

	@Override
	public List<SysApproveLog> queryList(SysApproveLog sysapprovelog) {
		return sysapprovelogMapper.queryList(sysapprovelog);
	}

	@Override
	public SysApproveLog query(SysApproveLog sysapprovelog) {
		return sysapprovelogMapper.query(sysapprovelog);
	}

	@Override
	public void insert(SysApproveLog sysapprovelog) {
		try {
			String time = DateUtils.getNowDateYMDHMS();
			sysapprovelog.setCreateTime(time);
			sysapprovelog.setOperateTime(time);
			sysapprovelog.setUpdateTime(time);
			sysapprovelog.setUpdateUserId(sysapprovelog.getCreateUserId());
			sysapprovelog.setUpdateUserName(sysapprovelog.getCreateUserName());
			sysapprovelogMapper.insert(sysapprovelog);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void update(SysApproveLog sysapprovelog) {
		try {
			sysapprovelogMapper.update(sysapprovelog);
		} catch (Exception e) {
			throw e
;		}
	}

	@Override
	@Transactional
	public void delete(String id) {
		try {
			String[] ids = id.split(",");
			for(String _id : ids){
				sysapprovelogMapper.delete(_id);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 跟据用户ID查询本人审批记录
	 * @param uId   用户ID
	 * @return
	 */
    @Override
    public Map querySysApproveLogByUid(String uId) {
    	Map resultMap = new HashMap();//返回数据
		//查询本月审批数量
		Integer monthSize = sysapprovelogMapper.queryMonthSizeByUid(uId);
		//查询本年审批数量
		Integer	yearSize = sysapprovelogMapper.queryYearSizeByUid(uId);
		//查询本人最近五条审批记录
		List<Map> sysApproveLogList = sysapprovelogMapper.querySysApproveLogTopFiveByUid(uId);
		resultMap.put("monthSize",monthSize);
		resultMap.put("yearSize",yearSize);
		resultMap.put("sysApproveLogList",sysApproveLogList);
        return resultMap;
    }

}
