package com.fijo.ebox.modular.sy.service;
import com.fijo.ebox.modular.sy.pojo.SysApproveLog;

import java.util.List;
import java.util.Map;


/**
*createTime:2019-12-16 15:28:23
*SysApproveLogService
*/
public interface SysApproveLogService {
	List<SysApproveLog> queryAll();
	List<SysApproveLog> queryList(SysApproveLog sysapprovelog);
	SysApproveLog query(SysApproveLog sysapprovelog);

	void insert(SysApproveLog sysapprovelog) throws Exception;

	void update(SysApproveLog sysapprovelog) throws Exception;

	void delete(String id) throws Exception;

	/**
	 * 跟据用户ID查询本人审批记录
	 * @param uId   用户ID
	 * @return
	 */
    Map querySysApproveLogByUid(String uId);
}
