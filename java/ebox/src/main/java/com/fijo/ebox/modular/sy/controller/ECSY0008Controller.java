package com.fijo.ebox.modular.sy.controller;

import com.fijo.ebox.base.util.plat.ExcelUtil;
import com.fijo.ebox.base.util.plat.FileUtil;
import com.fijo.ebox.dto.ResultDto;
import com.fijo.ebox.enums.SysEnum.IsToVoidEnum;
import com.fijo.ebox.modular.sy.pojo.ECSY0008;
import com.fijo.ebox.modular.sy.service.ECSY0008Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.UUID;

import com.fijo.ebox.base.util.common.DateUtils;
import com.fijo.ebox.base.util.plat.JsonUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;

/**
 * SYS_DDVALUES
 * ECSY0008
 **/
@RestController
@RequestMapping("/ECSY0008")
@Slf4j
public class ECSY0008Controller {
    @Autowired
    private ECSY0008Service ecsy0008Service;

    /**
     * 通过数据字典id查询数据字典值列表
     *
     * @param ddid    数据字典id
     * @param orgCode 组织编码
     * @return
     */
    @PostMapping(value = "/queryDDvalueListByDdidAndOrgCode")
    public String queryDDvalueListByDdidAndOrgCode(String ddid, String orgCode) {
        List<ECSY0008> resultList = ecsy0008Service.queryDDvalueListByDdidAndOrgCode(ddid, orgCode);
        return ResultDto.SUCCESS(resultList);
    }

    /**
     * 通过数据字典id查询数据字典值列表
     *
     * @param ddid    数据字典id
     * @param orgCode 组织编码
     * @return
     */
    @PostMapping(value = "/getParentValue")
    public String getParentValue(String ddid, String orgCode) {
        List<ECSY0008> resultList = ecsy0008Service.getParentValue(ddid, orgCode);
        return ResultDto.SUCCESS(resultList);
    }

    /**
     * 分享全部数据字典值到某个组织
     *
     * @param orgCode    组织编码
     * @param nowOrgCode 当前组织编码
     * @return
     */
    @PostMapping(value = "/shareAllDdvalueList2Org")
    public String shareAllDdvalueList2Org(String orgCode, String nowOrgCode) {
        try {
            ecsy0008Service.shareAllDdvalueList2Org(orgCode, nowOrgCode);
            return ResultDto.SUCCESS("分享成功！");
        } catch (Exception e) {
            log.error("分享失败！失败原因：{}", e.toString());
            return ResultDto.ERROR("分享失败！");
        }
    }

    /**
     * 分享选中数据字典值到某个组织
     *
     * @param orgCode 组织编码
     * @param ids     数据字典值ids
     * @return
     */
    @PostMapping(value = "/shareSelectDdvalueList2Org")
    public String shareSelectDdvalueList2Org(String orgCode, String ids) {
        try {
            ecsy0008Service.shareSelectDdvalueList2Org(orgCode, ids);
            log.info("分享成功！");
            return ResultDto.SUCCESS("分享成功！");
        } catch (Exception e) {
            log.error("分享失败！失败原因：{}", e.toString());
            return ResultDto.ERROR("分享失败！");
        }
    }

    /**
     * 查询
     *
     * @param orgCode
     * @return
     */
    @PostMapping(value = "/queryAll")
    public String queryAll(String orgCode) {
        List<ECSY0008> resultList = ecsy0008Service.queryAll();
        return ResultDto.SUCCESS(resultList);
    }

    /**
     * 通过code查询字典值,不返回父级信息
     *
     * @param code    字典code
     * @param orgCode 组织编码
     * @return
     */
    @PostMapping(value = "/queryDDValuesByCode")
    public String queryDDValuesByCode(String code, String orgCode) {
        List<Map> resultList = ecsy0008Service.queryDDValuesByCode(code, orgCode);
        return ResultDto.SUCCESS(resultList);
    }

    /**
     * 新增业务信息时用到的数据字典下拉框，需传具体的组织,不返回父级信息
     *
     * @param code    字典code
     * @param orgCode 组织编码
     * @return
     */
    @PostMapping(value = "/queryDDValuesByCodeForInsert")
    public String queryDDValuesByCodeForInsert(String code, String orgCode) {
        List<Map> resultList = ecsy0008Service.queryDDValuesByCodeForInsert(code, orgCode);

        if (resultList.toString().equals("[null]")) {
            List result = new ArrayList();
            return ResultDto.SUCCESS(result);
        } else {
            return ResultDto.SUCCESS(resultList);
        }
    }

    /**
     * 通过数据字典编码和组织编码批量查询字典值,不返回父级信息
     *
     * @param code    数据字典编码(多个)
     * @param orgCode 组织编码(多个)
     * @return
     */
    @PostMapping(value = "/batchQueryDDValuesByCode")
    public String batchQueryDDValuesByCode(String code, String orgCode) {
        Map result = ecsy0008Service.batchQueryDDValuesByCode(code, orgCode);
        return ResultDto.SUCCESS(result);
    }

    /**
     * 新增业务信息时用到的数据字典下拉框，需传具体的组织,不返回父级信息
     *
     * @param code    数据字典编码(多个)
     * @param orgCode 组织编码(多个)
     * @return
     */
    @PostMapping(value = "/batchQueryDDValuesByCodeForInsert")
    public String batchQueryDDValuesByCodeForInsert(String code, String orgCode) {
        Map result = ecsy0008Service.batchQueryDDValuesByCodeForInsert(code, orgCode);
        return ResultDto.SUCCESS(result);
    }

    /**
     * 查询
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/queryById")
    public String queryById(Long id) {
        ECSY0008 ecsy0008 = ecsy0008Service.queryById(id);
        return ResultDto.SUCCESS(ecsy0008);
    }

    /**
     * 新增
     *
     * @param ecsy0008
     * @return
     */
    @PostMapping(value = "/insert")
    public String insert(ECSY0008 ecsy0008) {
        try {
            ecsy0008Service.insert(ecsy0008);
            log.info("【新增数据字典值】 新增成功!");
            return ResultDto.SUCCESS("新增成功！");
        } catch (Exception e) {
            log.error("【新增数据字典值】 新增失败，失败原因：{}", e);
            return ResultDto.ERROR("【新增数据字典值】新增失败！", e.toString());
        }
    }

    /**
     * 更新
     *
     * @param ecsy0008
     * @return
     */
    @PostMapping(value = "/update")
    public String update(ECSY0008 ecsy0008) {
        try {
            ecsy0008Service.update(ecsy0008);
            log.info("【更新数据字典值】 更新成功!");
            return ResultDto.SUCCESS("更新成功！");
        } catch (Exception e) {
            log.error("【更新数据字典值】 更新失败，失败原因：{}", e);
            return ResultDto.ERROR("【更新数据字典值】更新失败！", e.toString());
        }
    }

    /**
     * 作废数据字典值
     *
     * @param id 数据字典id
     * @return
     */
    @PostMapping(value = "/toVoidDDvalue")
    public String toVoidDDvalue(String id) {
        try {
            String[] ids = id.split(",");
            for (String _id : ids) {
                ECSY0008 ecsy0008 = new ECSY0008();
                ecsy0008.setIsToVoid(IsToVoidEnum.TO_VOID.getCode());
                ecsy0008.setId(Long.valueOf(_id));
                ecsy0008Service.update(ecsy0008);
            }
            log.info("【更新数据字典值】 更新成功!");
            return ResultDto.SUCCESS("更新成功！");
        } catch (Exception e) {
            log.error("【更新数据字典值】 更新失败，失败原因：{}", e);
            return ResultDto.ERROR("【更新数据字典值】更新失败！", e.toString());
        }
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteById")
    public String deleteById(String id) {
        try {
            ecsy0008Service.deleteById(id);
            log.info("【删除xxx】 删除成功!");
            return ResultDto.SUCCESS("删除成功！");
        } catch (Exception e) {
            log.error("【删除xxx】 删除失败，失败原因：{}", e);
            return ResultDto.ERROR("【删除xxx】删除失败！", e.toString());
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
        ECSY0008 ecsy0008 = new ECSY0008();
        ecsy0008 = JsonUtil.json2Bean(queryStr, ECSY0008.class);
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

        List<ECSY0008> resultList = new ArrayList();
        resultList = ecsy0008Service.queryAll();

        //获取处理好的工作薄对象，为导出做准备
        XSSFWorkbook xssfWorkbook = ExcelUtil.initExport(sheetName, sheetName, columnArr_field, columnArr_title, resultList,null);
        //获取导出文件名
        String fileName = DateUtils.getNowDateYMD() + sheetName + UUID.randomUUID() + ".xlsx";
        String result = null;
        try {
            result = FileUtil.downloadExcel(response, fileName, xssfWorkbook);
            return ResultDto.SUCCESS(result);
        } catch (Exception e) {
            log.error("【导出文件】失败，失败原因：{}", e);
            return ResultDto.ERROR(e);
        }

    }
}


