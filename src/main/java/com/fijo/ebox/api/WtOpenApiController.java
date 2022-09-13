package com.fijo.ebox.api;

import com.fijo.ebox.base.util.common.StringUtil;
import com.fijo.ebox.dto.ResultDto;
import com.fijo.ebox.modular.wt.service.QUWT0001Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/wtOpenApi")
public class WtOpenApiController {

    @Autowired
    private QUWT0001Service quwt0001Service;

    /**
     * 查询问题图表数据
     * @param orgCode
     * @param year
     * @return
     */
    @PostMapping(value = "/queryPuestionChartData")
    public String queryPuestionChartData(String orgCode, Integer year, String ddCode){
        try {
            orgCode = StringUtil.str2SqlStr(orgCode);
            Map resultMap = quwt0001Service.queryPuestionChartData(orgCode,year,ddCode);
            return ResultDto.SUCCESS("查询成功！",resultMap);
        }catch (Exception e){
            e.printStackTrace();
            return ResultDto.ERROR("查询失败！",e.toString());
        }
    }


    /**
     * 查询各状态的数据量
     * @param orgCode    组织编码
     * @param state     状态
     * @param year      年份
     * @return
     */
    @PostMapping(value = "/queryDataNumberBySateForApi")
    public String queryDataNumberBySateForApi(String orgCode,Integer state,Integer year){
        try {
            orgCode = StringUtil.str2SqlStr(orgCode);
            List<Map> result = quwt0001Service.queryDataNumberBySateForApi(orgCode,state,year);
            return ResultDto.SUCCESS(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultDto.ERROR("查询失败！",e.toString());
        }
    }


    /**
     * 查询问题数据前几条数据
     * @param orgCode   组织编码
     * @param dataSize  数据条数
     * @param year      年份
     * @return
     */
    @PostMapping(value = "/queryQuesDataListByTop")
    public String queryQuesDataListByTop(String orgCode, Integer dataSize, Integer year){
        try {
            orgCode = StringUtil.str2SqlStr(orgCode);
            List<Map> result = quwt0001Service.queryQuesDataListByTop(orgCode,dataSize,year);
            return ResultDto.SUCCESS(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultDto.ERROR("查询失败！",e.toString());
        }
    }


}
