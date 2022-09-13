package com.fijo.ebox.modular.sy.controller;
import com.fijo.ebox.base.util.plat.ExcelUtil;
import com.fijo.ebox.base.util.plat.FileUtil;
import com.fijo.ebox.dto.ResultDto;
import com.fijo.ebox.modular.sy.pojo.ECSY0005;
import com.fijo.ebox.modular.sy.service.ECSY0005Service;
import com.fijo.ebox.modular.sy.service.ECSY0006Service;
import com.fijo.ebox.base.util.common.DateUtils;

import com.fijo.ebox.base.util.plat.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
/**
*SYS_TAG_TYPE
*ECSY0005
**/
@RestController
@RequestMapping("/ECSY0005")
@Slf4j
public class ECSY0005Controller {
	@Autowired
	private ECSY0005Service ecsy0005Service;

	@Autowired
	private ECSY0006Service ecsy0006Service;
/**
	 * 查询
	 * @param tagTypeCode
	 * @return
	 */
	@PostMapping(value = "/queryAll")
	public String queryAll(String tagTypeCode){
		List<ECSY0005> resultList = ecsy0005Service.queryAll();
		return ResultDto.SUCCESS(resultList);
	}

	/**
	 * PC用系统标签类型条件查询
	 * @param tagTypeCode  标签类型编码
	 * @param tagTypeName  标签类型名称
	 * @return
	 */
	@PostMapping(value = "/queryBy")
	public String queryBy(String tagTypeCode,String tagTypeName){
		try {
			List<ECSY0005> ecsy0005List = ecsy0005Service.queryBy(tagTypeCode,tagTypeName);
			log.info("【系统标签类型】条件查询，查询成功！共{}",ecsy0005List.size()+"条数据");
			return ResultDto.SUCCESS("【系统标签类型】条件查询，查询成功！",ecsy0005List);
		}catch (Exception e){
			log.error("【系统标签类型】条件查询，查询失败！原因：",e.getMessage());
			return ResultDto.ERROR("【系统标签类型】条件查询，查询失败！原因：",e.toString());
		}
	}

	/**
	 * 系统标签类型根据code查询
	 * @param tagTypeCode 标签类型编码
	 * @return
	 */
	@PostMapping(value = "/queryBYCode")
	public String queryBYCode(String tagTypeCode){
		try {
			ECSY0005 ecsy0005 = ecsy0005Service.queryBYCode(tagTypeCode);
			log.info("【系统标签类型】根据code查询，查询成功！");
			return ResultDto.SUCCESS("【系统标签类型】根据code查询，查询成功！",ecsy0005);
		}catch (Exception e){
			log.error("【系统标签类型】根据code查询，查询失败！原因：",e.getMessage());
			return ResultDto.ERROR("【系统标签类型】根据code查询，查询失败！原因：",e.toString());
		}
	}

	/**
	 * 根据ID查询
	 * @param id 标签类型编码
	 * @return
	 */
	@PostMapping(value = "/queryById")
	public String queryById(Long id){
		try {
			ECSY0005 ecsy0005 = ecsy0005Service.queryById(id);
			log.info("【系统标签类型】根据code查询，查询成功！");
			return ResultDto.SUCCESS("【系统标签类型】根据code查询，查询成功！",ecsy0005);
		}catch (Exception e){
			log.error("【系统标签类型】根据code查询，查询失败！原因：",e.getMessage());
			return ResultDto.ERROR("【系统标签类型】根据code查询，查询失败！原因：",e.toString());
		}
	}

	/**
	 * 根据标签CODE查询企业标签
	 * @param tagTypeCode
	 * @return
	 */
	@PostMapping(value = "/queryDetailsByCode")
	public String queryDetailsByCode(String tagTypeCode){
		try {
			List<Map> mapList = ecsy0005Service.queryDetailsByCode(tagTypeCode);
			log.info("【系统标签类型】根据code查询，查询成功！");
			return ResultDto.SUCCESS("【系统标签类型】根据code查询，查询成功！",mapList);
		}catch (Exception e){
			log.error("【系统标签类型】根据code查询，查询失败！原因：",e.getMessage());
			return ResultDto.ERROR("【系统标签类型】根据code查询，查询失败！原因：",e.toString());
		}
	}

	/**
	*新增
	* @param ecsy0005
	* @return
	*/
	@PostMapping(value = "/insert")
	public String insert(ECSY0005 ecsy0005){
		try {
			ecsy0005Service.insert(ecsy0005);
			log.info("【新增系统标签类型】 新增成功!");
			return ResultDto.SUCCESS("新增成功！");
		} catch (Exception e) {
			log.error("【新增系统标签类型】 新增失败，失败原因：{}",e.getMessage());
			return ResultDto.ERROR("【新增系统标签类型】新增失败！",e.getMessage());
		}
}

	/**
	*更新
	* @param ecsy0005
	* @return
	*/
	@PostMapping(value = "/update")
	public String update(ECSY0005 ecsy0005){
		try {
			ecsy0005Service.update(ecsy0005);
			log.info("【更新系统标签类型】 更新成功!");
			return ResultDto.SUCCESS("更新成功！");
		} catch (Exception e) {
			log.error("【更新系统标签类型】 更新失败，失败原因：{}",e.getMessage());
			return ResultDto.ERROR("【更新系统标签类型】更新失败！",e.getMessage());
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
			ecsy0005Service.deleteById(id);
			log.info("【删除系统标签类型】 删除成功!");
			return ResultDto.SUCCESS("删除成功！");
		} catch (Exception e) {
			log.error("【删除系统标签类型】 删除失败，失败原因：{}",e.getMessage());
			return ResultDto.ERROR("【删除系统标签类型】删除失败！",e.getMessage());
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
		ECSY0005 ecsy0005 =  new ECSY0005();
		ecsy0005 = JsonUtil.json2Bean(queryStr,ECSY0005.class);
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

		List<ECSY0005> resultList = new ArrayList();
		resultList = ecsy0005Service.queryAll();

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


