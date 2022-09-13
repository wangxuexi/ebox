package com.fijo.ebox.modular.sy.controller;
import com.fijo.ebox.base.util.plat.ExcelUtil;
import com.fijo.ebox.base.util.plat.FileUtil;
import com.fijo.ebox.dto.ResultDto;
import com.fijo.ebox.modular.sy.pojo.ECSY0006;
import com.fijo.ebox.modular.sy .service.ECSY0006Service;

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
*SYS_TAG_INFO
*ECSY0006
**/
@RestController
@RequestMapping("/ECSY0006")
@Slf4j
public class ECSY0006Controller {
	@Autowired
	private ECSY0006Service ecsy0006Service;
/**
	 * 查询
	 * @param orgCode
	 * @return
	 */
	@PostMapping(value = "/queryAll")
	public String queryAll(String orgCode){
		List<ECSY0006> resultList = ecsy0006Service.queryAll();
		return ResultDto.SUCCESS(resultList);
	}

	/**
	 * 查询
	 * @param id
	 * @return
	 */
	@PostMapping(value = "/queryById")
	public String queryById(Long id){
		ECSY0006 ecsy0006 = ecsy0006Service.queryById(id);
		return ResultDto.SUCCESS(ecsy0006);
	}

	/**
	 *系统标签详情根据tagTypeCode查询
	 * @param tagTypeCode
	 * @return
	 */
	@PostMapping(value = "/queryBytagTypeCode")
	public String queryBytagTypeId(String tagTypeCode){
		try {
			List<ECSY0006> ecsy0006List = ecsy0006Service.queryBytagTypeCode(tagTypeCode);
			log.info("【系统标签详情】根据tagTypeId查询，查询成功！共{}",ecsy0006List.size()+"条数据");
			return ResultDto.SUCCESS("【系统标签详情】根据tagTypeId查询，查询成功！",ecsy0006List);
		}catch (Exception e){
			log.error("【系统标签详情】根据tagTypeId查询，查询失败！",e.getMessage());
			return ResultDto.ERROR("【系统标签详情】根据tagTypeId查询，查询失败！",e.toString());
		}
	}

	/**
	*新增
	* @param ecsy0006
	* @return
	*/
	@PostMapping(value = "/insert")
	public String insert(ECSY0006 ecsy0006){
		try {
			ecsy0006Service.insert(ecsy0006);
			log.info("【新增统标签详情】 新增成功!");
			return ResultDto.SUCCESS("新增成功！");
		} catch (Exception e) {
			log.error("【新增统标签详情】 新增失败，失败原因：{}",e.getMessage());
			return ResultDto.ERROR("【新增统标签详情】新增失败！",e.getMessage());
		}
}

	/**
	*更新
	* @param ecsy0006
	* @return
	*/
	@PostMapping(value = "/update")
	public String update(ECSY0006 ecsy0006){
		try {
			ecsy0006Service.update(ecsy0006);
			log.info("【更新系统标签详情】 更新成功!");
			return ResultDto.SUCCESS("更新成功！");
		} catch (Exception e) {
			log.error("【更新系统标签详情】 更新失败，失败原因：{}",e.getMessage());
			return ResultDto.ERROR("【更新系统标签详情】更新失败！",e.getMessage());
		}
}

	/**
	*删除
	* @param id
	* @return
	*/
	@PostMapping(value = "/deleteById")
	public String deleteById(String id){
		try {
			ecsy0006Service.deleteById(id);
			log.info("【删除系统标签详情】 删除成功!");
			return ResultDto.SUCCESS("删除成功！");
		} catch (Exception e) {
			log.error("【删除系统标签详情】 删除失败，失败原因：{}",e.getMessage());
			return ResultDto.ERROR("【删除系统标签详情】删除失败！",e.getMessage());
		}
	}
	/**
	 * 导出
	 * @param queryStr 查询参数
	 * @param columnList 表格列参数
	 * @param response
	 * @return
	 */
	@PostMapping(value = "/exportTable")
	public String exportTable(String queryStr,String columnList,HttpServletResponse response){
		ECSY0006 ecsy0006 =  new ECSY0006();
		ecsy0006 = JsonUtil.json2Bean(queryStr,ECSY0006.class);
		String sheetName = "_SY";
		//处理表头
		List<Map> columnMapList =  JsonUtil.json2List(columnList,Map.class);
		if(columnList == null){
			return ResultDto.ERROR("导出失败，表格列头为空");
		}
		columnMapList.remove(0);
		String [] columnArr_field = new String[columnMapList.size()];
		String [] columnArr_title = new String[columnMapList.size()];
		for(int i = 0 ; i < columnMapList.size();i++){
			columnArr_field[i] = String.valueOf(columnMapList.get(i).get("field"));
			columnArr_title[i] = String.valueOf(columnMapList.get(i).get("title"));
		}

		List<ECSY0006> resultList = new ArrayList();
		resultList = ecsy0006Service.queryAll();

		//获取处理好的工作薄对象，为导出做准备
		XSSFWorkbook xssfWorkbook = ExcelUtil.initExport(sheetName, sheetName, columnArr_field, columnArr_title, resultList,null);
		//获取导出文件名
		String fileName = DateUtils.getNowDateYMD()+sheetName+ UUID.randomUUID()+".xlsx";
		String result = null;
		try {
			result = FileUtil.downloadExcel(response, fileName, xssfWorkbook);
			return ResultDto.SUCCESS( result);
		} catch (Exception e) {
			log.error("【导出文件】失败，失败原因：{}",e);
			return ResultDto.ERROR(e);
		}

	}}


