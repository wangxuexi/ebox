package com.fijo.ebox.modular.wt.mapper;
import com.fijo.ebox.base.mapper.FijoBaseMapper;
import com.fijo.ebox.modular.wt.pojo.QUWT0001;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *CM_PROBLEM_HANDLE_RECORD
 *QUWT0001
 **/
public interface QUWT0001Mapper extends FijoBaseMapper<QUWT0001,Long>{

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
     * @return
     */
    List<QUWT0001> queryDataList(@Param("orgCode") String orgCode,
                                 @Param("entityTypeCode") String entityTypeCode,
                                 @Param("problemName") String problemName,
                                 @Param("happenStratTime") String happenStratTime,
                                 @Param("happenEndTime") String happenEndTime,
                                 @Param("state") Integer state,
                                 @Param("problemSource") Integer problemSource,
                                 @Param("problemTypeId") Long problemTypeId,
                                 @Param("problemSmallTypeId") Long problemSmallTypeId,
                                 @Param("submitName") String submitName);

    /**
     * 问题处理
     * @param id
     * @param state		状态
     * @param endTime		结案时间
     * @param endName		结案人
     * @param endExplain	结案说明
     * @return
     */
    void quesHandle(@Param("id") Long id,
                    @Param("state") Integer state,
                    @Param("stateName") String stateName,
                    @Param("endTime") String endTime,
                    @Param("endUserId") Long endUserId,
                    @Param("endName") String endName,
                    @Param("endExplain") String endExplain,
                    @Param("userId") Long userId,
                    @Param("userName") String userName,
                    @Param("operateTime") String operateTime);

    /**
     * 微信端查询数据列表
     * @param orgCode				组织编码
     * @param entityTypeCode		实体类编码
     * @param keyword				关键字（提交人，问题标题）
     * @param problemSource		数据来源
     * @param state				状态
     * @param problemTypeId		问题大类
     * @param problemSmallTypeId	问题小类
     * @return
     */
    List<QUWT0001> queryDataListByWx(@Param("orgCode") String orgCode,
                                     @Param("entityTypeCode") String entityTypeCode,
                                     @Param("keyword") String keyword,
                                     @Param("problemSource") Integer problemSource,
                                     @Param("state") Integer state,
                                     @Param("problemTypeId") Long problemTypeId,
                                     @Param("problemSmallTypeId") Long problemSmallTypeId,
                                     @Param("happenStratTime") String happenStratTime,
                                     @Param("happenEndTime") String happenEndTime);

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
    List<Map> queryDataNumberBySate(@Param("orgCode") String orgCode,
                                    @Param("entityTypeCode") String entityTypeCode,
                                    @Param("keyword") String keyword,
                                    @Param("problemSource") Integer problemSource,
                                    @Param("state") Integer state,
                                    @Param("problemTypeId") Long problemTypeId,
                                    @Param("problemSmallTypeId") Long problemSmallTypeId,
                                    @Param("happenStratTime") String happenStratTime,
                                    @Param("happenEndTime") String happenEndTime);

    /**
     * 查询个大类的数量
     * @param orgCode
     * @param year
     * @param ddCode
     * @return
     */
    List<Map> queryPuestionChartData(@Param("orgCode") String orgCode,
                                     @Param("year") Integer year,
                                     @Param("ddCode") String ddCode);

    /**
     * 查询各状态的数据量
     * @param orgCode    组织编码
     * @param state     状态
     * @param year      年份
     * @return
     */
    List<Map> queryDataNumberBySateForApi(@Param("orgCode") String orgCode,
                                          @Param("state") Integer state,
                                          @Param("year") Integer year);

    /**
     * 查询问题数据前几条数据
     * @param orgCode   组织编码
     * @param dataSize  数据条数
     * @param year      年份
     * @return
     */
    List<Map> queryQuesDataListByTop(@Param("orgCode") String orgCode,
                                     @Param("dataSize") Integer dataSize,
                                     @Param("year") Integer year);
}

