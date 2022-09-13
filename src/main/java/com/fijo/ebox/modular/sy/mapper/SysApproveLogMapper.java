package com.fijo.ebox.modular.sy.mapper;
import com.fijo.ebox.modular.sy.pojo.SysApproveLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
* createTime:2019-12-16 15:28:23
*SysApproveLogMapper
*/
public interface SysApproveLogMapper {
	List<SysApproveLog> queryAll();
	List<SysApproveLog> queryList(SysApproveLog sysapprovelog);
	SysApproveLog query(SysApproveLog sysapprovelog);

	void insert(SysApproveLog sysapprovelog);

	void update(SysApproveLog sysapprovelog);

	void delete(String id);

	//查询本月审批数量
	Integer queryMonthSizeByUid(@Param("uId") String uId);

	//查询本年审批数量
	Integer queryYearSizeByUid(@Param("uId") String uId);

	//查询本人最近五条审批记录
	List<Map> querySysApproveLogTopFiveByUid(@Param("uId") String uId);
}
