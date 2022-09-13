package com.fijo.ebox.modular.wt.service.impl;
import com.fijo.ebox.base.service.impl.FijoBaseServiceImpl;
import com.fijo.ebox.base.util.common.DateUtils;
import com.fijo.ebox.base.util.plat.PlatUtil;
import com.fijo.ebox.dto.ResultUserDto;
import com.fijo.ebox.modular.wt.pojo.QUWT0001;
import com.fijo.ebox.modular.wt.service.QUWT0001Service;
import com.fijo.ebox.modular.wt.mapper.QUWT0001Mapper;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.fijo.ebox.base.util.common.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *CM_PROBLEM_HANDLE_RECORD
 *QUWT0001
 **/
@Service
public class QUWT0001ServiceImpl extends FijoBaseServiceImpl<QUWT0001,Long> implements QUWT0001Service {
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private QUWT0001Mapper quwt0001Mapper;

	@Autowired
	public QUWT0001ServiceImpl(QUWT0001Mapper quwt0001Mapper){
		super(quwt0001Mapper);
		this.quwt0001Mapper = quwt0001Mapper;
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
		List<QUWT0001> resultList =  quwt0001Mapper.queryAll();
		Map<String, Object> dataMap = wrapQueryResult(resultList);
		return dataMap;
	}

	/**
	 * 分页查询数据列表
	 * @param orgCode				组织编码
	 * @param entityTypeCode		数据来源实体类编码
	 * @param problemName			问题标题
	 * @param happenStratTime		问题发生开始时间
	 * @param happenEndTime		问题发生结束时间
	 * @param state				状态
	 * @param problemSource		数据来源
	 * @param problemTypeId		问题大类
	 * @param problemSmallTypeId	问题小类
	 * @param submitName			提交人姓名
	 * @param pageNo				页码
	 * @param pageSize				页长
	 * @return
	 */
	@Override
	public Map queryDataListByPage(String orgCode, String entityTypeCode, String problemName, String happenStratTime, String happenEndTime,
								   Integer state,Integer problemSource, Long problemTypeId, Long problemSmallTypeId,
								   String submitName, Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		orgCode = StringUtil.str2SqlStr(orgCode);
		List<QUWT0001> resultList =  quwt0001Mapper.queryDataList(orgCode,entityTypeCode,problemName,happenStratTime,happenEndTime,
															      state,problemSource,problemTypeId,problemSmallTypeId,submitName);
		Map<String, Object> dataMap = wrapQueryResult(resultList);
		return dataMap;
	}

	/**
	 * 查询数据列表
	 * @param orgCode				组织编码
	 * @param entityTypeCode		数据来源实体类编码
	 * @param problemName			问题标题
	 * @param happenStratTime		问题发生开始时间
	 * @param happenEndTime		问题发生结束时间
	 * @param state				状态
	 * @param problemSource		数据来源
	 * @param problemTypeId		问题大类
	 * @param problemSmallTypeId	问题小类
	 * @param submitName			提交人姓名
	 * @return
	 */
	@Override
	public List<QUWT0001> queryDataList(String orgCode, String entityTypeCode, String problemName, String happenStratTime, String happenEndTime,
										Integer state,Integer problemSource, Long problemTypeId, Long problemSmallTypeId,String submitName) {
		List<QUWT0001> resultList =  quwt0001Mapper.queryDataList(orgCode,entityTypeCode,problemName,happenStratTime,happenEndTime,
																  state,problemSource,problemTypeId,problemSmallTypeId,submitName);
		return resultList;
	}

	/**
	 * 问题处理
	 * @param id
	 * @param state		状态
	 * @param endTime		结案时间
	 * @param endName		结案人
	 * @param endExplain	结案说明
	 * @return
	 */
	@Override
	public void quesHandle(Long id, Integer state, String stateName, String endTime, Long endUserId, String endName, String endExplain) {
		ResultUserDto loginUser = PlatUtil.getLoginUser(request);
		loginUser = loginUser == null ? new ResultUserDto() : loginUser;
		Long userId = loginUser.getUId() == null ? new Long(-1) : loginUser.getUId();
		String userName = StringUtils.isNullOrEmpty(loginUser.getNickName()) ? "*" : loginUser.getNickName();
		String operateTime = DateUtils.getNowDateYMDHMS();
		//开始处理
		quwt0001Mapper.quesHandle(id,state,stateName,endTime,endUserId,endName,endExplain,userId,userName,operateTime);
	}

	/**
	 * 微信端查询数据列表
	 * @param orgCode				组织编码
	 * @param entityTypeCode		实体类编码
	 * @param keyword				关键字（提交人，问题标题）
	 * @param problemSource		数据来源
	 * @param state				状态
	 * @param problemTypeId		问题大类
	 * @param problemSmallTypeId	问题小类
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
    @Override
    public Map queryDataListByWx(String orgCode, String entityTypeCode, String keyword, Integer problemSource, Integer state, Long problemTypeId, Long problemSmallTypeId, String happenStratTime, String happenEndTime, Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		List<QUWT0001> resultList =  quwt0001Mapper.queryDataListByWx(orgCode,entityTypeCode,keyword,problemSource,state,problemTypeId,problemSmallTypeId,happenStratTime,happenEndTime);
		Map<String, Object> dataMap = wrapQueryResult(resultList);
		return dataMap;
    }

	/**
	 * 查询各状态的数据量
	 * @param orgCode
	 * @param entityTypeCode
	 * @param keyword
	 * @param problemSource
	 * @param state
	 * @param problemTypeId
	 * @param problemSmallTypeId
	 * @param happenStratTime
	 * @param happenEndTime
	 * @return
	 */
    @Override
    public List<Map> queryDataNumberBySate(String orgCode, String entityTypeCode, String keyword, Integer problemSource, Integer state, Long problemTypeId, Long problemSmallTypeId, String happenStratTime, String happenEndTime) {
        List<Map> data_map = quwt0001Mapper.queryDataNumberBySate(orgCode,entityTypeCode,keyword,problemSource,state,problemTypeId,problemSmallTypeId,happenStratTime,happenEndTime);
    	return data_map;
    }

	/**
	 *查询问题图表数据
	 * @param orgCode
	 * @param year
	 * @return
	 */
    @Override
    public Map queryPuestionChartData(String orgCode, Integer year, String ddCode) {
		//查询个大类的数量
		List<Map> data_map_list = quwt0001Mapper.queryPuestionChartData(orgCode,year,ddCode);
		//处理数据
		Map chart_data_map = new HashMap();
		//图例数据
		List<String> legend_data_list = new ArrayList<>();
		//图表数据
		List<Map> series_data_list = new ArrayList<>();
		data_map_list.stream().forEach(map ->{
			//添加图例数据
			legend_data_list.add(map.get("name").toString());
			//添加图表数据
			series_data_list.add(map);
		});
		//添加返回数据
		chart_data_map.put("legend_data",legend_data_list);
		chart_data_map.put("series_data",series_data_list);
    	return chart_data_map;
    }

	/**
	 * 查询各状态的数据量
	 * @param orgCode    组织编码
	 * @param state     状态
	 * @param year      年份
	 * @return
	 */
	@Override
	public List<Map> queryDataNumberBySateForApi(String orgCode, Integer state, Integer year) {
		List<Map> data_map = quwt0001Mapper.queryDataNumberBySateForApi(orgCode,state,year);
		return data_map;
	}

	/**
	 * 查询问题数据前几条数据
	 * @param orgCode   组织编码
	 * @param dataSize  数据条数
	 * @param year      年份
	 * @return
	 */
    @Override
    public List<Map> queryQuesDataListByTop(String orgCode, Integer dataSize, Integer year) {
		List<Map> data_map = quwt0001Mapper.queryQuesDataListByTop(orgCode,dataSize,year);
		return data_map;
    }

}

