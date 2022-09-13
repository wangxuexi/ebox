package com.fijo.ebox.modular.wt.service;
import com.fijo.ebox.base.service.FijoBaseService;
import com.fijo.ebox.modular.wt.pojo.QUWT0001;
import java.util.List;
import java.util.Map;

/**
 *CM_PROBLEM_HANDLE_RECORD
 *QUWT0001
 **/
public interface QUWT0001Service extends FijoBaseService<QUWT0001,Long>{

    /**
     * 分页查询
     * @param orgCode 组织编码
     * @param pageNo 页码
     * @param pageSize 页长
     * @return
     */
    Map<String,Object> queryAllByPage(String orgCode,Integer pageNo,Integer pageSize);

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
    Map queryDataListByPage(String orgCode, String entityTypeCode, String problemName, String happenStratTime, String happenEndTime,
                            Integer state,Integer problemSource, Long problemTypeId, Long problemSmallTypeId,
                            String submitName, Integer pageNo, Integer pageSize);

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
    List<QUWT0001> queryDataList(String orgCode, String entityTypeCode, String problemName, String happenStratTime, String happenEndTime,
                                 Integer state,Integer problemSource, Long problemTypeId, Long problemSmallTypeId,String submitName);

    /**
     * 问题处理
     * @param id
     * @param state		状态
     * @param endTime		结案时间
     * @param endName		结案人
     * @param endExplain	结案说明
     * @return
     */
    void quesHandle(Long id, Integer state, String stateName, String endTime, Long endUserId, String endName, String endExplain);

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
    Map queryDataListByWx(String orgCode, String entityTypeCode, String keyword, Integer problemSource, Integer state, Long problemTypeId, Long problemSmallTypeId, String happenStratTime, String happenEndTime, Integer pageNo, Integer pageSize);

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
    List<Map> queryDataNumberBySate(String orgCode, String entityTypeCode, String keyword, Integer problemSource, Integer state, Long problemTypeId, Long problemSmallTypeId, String happenStratTime, String happenEndTime);

    /**
     * 查询问题图表数据
     * @param orgCode
     * @param year
     * @return
     */
    Map queryPuestionChartData(String orgCode, Integer year, String ddCode);

    /**
     * 查询各状态的数据量
     * @param orgCode    组织编码
     * @param state     状态
     * @param year      年份
     * @return
     */
    List<Map> queryDataNumberBySateForApi(String orgCode, Integer state, Integer year);

    /**
     * 查询问题数据前几条数据
     * @param orgCode   组织编码
     * @param dataSize  数据条数
     * @param year      年份
     * @return
     */
    List<Map> queryQuesDataListByTop(String orgCode, Integer dataSize, Integer year);
}

