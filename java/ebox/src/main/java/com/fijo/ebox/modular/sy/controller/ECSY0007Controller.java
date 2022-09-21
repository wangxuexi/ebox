package com.fijo.ebox.modular.sy.controller;

import com.fijo.ebox.base.util.common.StringUtil;
import com.fijo.ebox.base.util.plat.ExcelUtil;
import com.fijo.ebox.base.util.plat.FileUtil;
import com.fijo.ebox.dto.ResultDto;
import com.fijo.ebox.modular.sy.pojo.ECSY0007;
import com.fijo.ebox.modular.sy.pojo.ECSY0008;
import com.fijo.ebox.modular.sy.service.ECSY0007Service;
import com.fijo.ebox.modular.sy.service.ECSY0008Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import com.fijo.ebox.base.util.common.DateUtils;
import com.fijo.ebox.base.util.plat.JsonUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;

/**
 * SYS_DATADICTIONARY
 * ECSY0007
 **/
@RestController
@RequestMapping("/ECSY0007")
@Slf4j
public class ECSY0007Controller {
    @Autowired
    private ECSY0007Service ecsy0007Service;

    @Autowired
    private ECSY0008Service ecsy0008Service;

    /**
     * 查询
     *
     * @param orgCode
     * @return
     */
    @PostMapping(value = "/queryAll")
    public String queryAll(String orgCode) {
        List<ECSY0007> resultList = ecsy0007Service.queryAll();
        return ResultDto.SUCCESS(resultList);
    }

    /**
     * 通过租户查询数据字典列表
     *
     * @param tenant 租户
     * @return
     */
    @PostMapping(value = "/queryListByTenant")
    public String queryListByTenant(String tenant) {
        List<Map> resultList = ecsy0007Service.queryListByTenant(tenant);
        return ResultDto.SUCCESS(resultList);
    }

    /**
     * 查询
     *
     * @param id 数据字典id
     * @return
     */
    @PostMapping(value = "/queryById")
    public String queryById(Long id) {
        ECSY0007 ecsy0007 = ecsy0007Service.queryById(id);
        return ResultDto.SUCCESS(ecsy0007);
    }

    /**
     * 新增
     *
     * @param ecsy0007
     * @return
     */
    @PostMapping(value = "/insert")
    public String insert(ECSY0007 ecsy0007) {
        try {
            List<ECSY0007> ecsy0007List = ecsy0007Service.queryListByTenantAndCode(ecsy0007.getTenant(), ecsy0007.getCode());
            if (ecsy0007List.size() > 0) {
                return ResultDto.ERROR("新增失败！原因：编码重复！");
            }
            ecsy0007Service.insert(ecsy0007);
            log.info("【新增数据字典成功】 新增成功!");
            return ResultDto.SUCCESS("新增成功！");
        } catch (Exception e) {
            log.error("【新增数据字典失败】 新增失败，失败原因：{}", e);
            return ResultDto.ERROR("【新增数据字典失败】新增失败！", e.toString());
        }
    }

    /**
     * 更新
     *
     * @param ecsy0007
     * @return
     */
    @PostMapping(value = "/update")
    public String update(ECSY0007 ecsy0007) {
        try {
            ecsy0007Service.update(ecsy0007);
            log.info("【更新数据字典成功】 更新成功!");
            return ResultDto.SUCCESS("更新成功！");
        } catch (Exception e) {
            log.error("【更新数据字典失败】 更新失败，失败原因：{}", e);
            return ResultDto.ERROR("【更新数据字典失败】更新失败！", e.toString());
        }
    }

    /**
     * 删除
     *
     * @param id 数据字典id
     * @return
     */
    @PostMapping(value = "/deleteById")
    public String deleteById(String id) {
        try {
            List<ECSY0008> ecsy0008List = ecsy0008Service.queryDDvalueListByDdid(Long.valueOf(id));
            if (ecsy0008List.size() > 0) {
                return ResultDto.ERROR("删除失败！此节点已存在数据字典值！");
            }

            ecsy0007Service.deleteById(id);
            log.info("【删除数据字典】 删除成功!");
            return ResultDto.SUCCESS("删除成功！");
        } catch (Exception e) {
            log.error("【删除数据字典】 删除失败，失败原因：{}", e);
            return ResultDto.ERROR("【删除数据字典】删除失败！", e.toString());
        }
    }

    /**
     * 导出
     *
     * @param queryStr   查询参数
     * @param columnList 表格列参数
     * @param response
     * @return
     */
    @PostMapping(value = "/exportTable")
    public String exportTable(String queryStr, String columnList, HttpServletResponse response) {
        ECSY0007 ecsy0007 = new ECSY0007();
        ecsy0007 = JsonUtil.json2Bean(queryStr, ECSY0007.class);
        String sheetName = "_SY";
        //处理表头
        List<Map> columnMapList = JsonUtil.json2List(columnList, Map.class);
        if (columnList == null) {
            return ResultDto.ERROR("导出失败，表格列头为空");
        }
        columnMapList.remove(0);
        String[] columnArr_field = new String[columnMapList.size()];
        String[] columnArr_title = new String[columnMapList.size()];
        for (int i = 0; i < columnMapList.size(); i++) {
            columnArr_field[i] = String.valueOf(columnMapList.get(i).get("field"));
            columnArr_title[i] = String.valueOf(columnMapList.get(i).get("title"));
        }

        List<ECSY0007> resultList = new ArrayList();
        resultList = ecsy0007Service.queryAll();

        //获取处理好的工作薄对象，为导出做准备
        XSSFWorkbook xssfWorkbook = ExcelUtil.initExport(sheetName, sheetName, columnArr_field, columnArr_title, resultList,null);
        //获取导出文件名
        String fileName = DateUtils.getNowDateYMD() + sheetName + UUID.randomUUID()+ ".xlsx";
        String result = null;
        try {
            result = FileUtil.downloadExcel(response, fileName, xssfWorkbook);
            return ResultDto.SUCCESS(result);
        } catch (Exception e) {
            log.error("【导出文件】失败，失败原因：{}", e);
            return ResultDto.ERROR(e);
        }

    }

    /**
     * 根据字典编码查询数据字典数据
     * @param orgCode
     * @param ddCode
     * @return
     */
    @PostMapping(value = "/queryAllDdValuesDataByCode")
    public String queryAllDdValuesDataByCode(String tenant, String orgCode, String ddCode){
        try {
            List<ECSY0008> mapList = new ArrayList<>();
            //查询数据字典
            List<ECSY0007> ecsy0007List = ecsy0007Service.queryAllDataListByTenantAndCode(tenant, ddCode);
            //查询数据字典值
            ecsy0007List.stream().forEach(data->{
                //查询数据字典值数据
                List<ECSY0008> ecsy0008List = ecsy0008Service.queryAllDDvalueListByDdidAndOrgCode(data.getId(), StringUtil.str2SqlStr(orgCode));
                ecsy0008List.stream().forEach(ecsy0008 -> {
                    mapList.add(ecsy0008);
                });
            });
            log.info("【查询数据字典】查询成功!");
            return ResultDto.SUCCESS("查询成功！",mapList);
        } catch (Exception e) {
            log.error("【查询数据字典】查询失败，失败原因：{}", e);
            return ResultDto.ERROR("【查询数据字典】查询失败！", e.toString());
        }
    }

}


