package com.fijo.ebox.modular.sy.controller;
import com.fijo.ebox.base.util.plat.ExcelUtil;
import com.fijo.ebox.base.util.plat.FileUtil;
import com.fijo.ebox.dto.ResultDto;
import com.fijo.ebox.modular.sy.pojo.ECSY0004;
import com.fijo.ebox.modular.sy.service.ECSY0004Service;
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
*SYS_DDVALUE_CONFIG
*ECSY0004
**/
@RestController
@RequestMapping("/ECSY0004")
@Slf4j
public class ECSY0004Controller {
	@Autowired
	private ECSY0004Service ecsy0004Service;
	/**
	 * 查询
	 * @param orgCode
	 * @return
	 */
	@PostMapping(value = "/queryAll")
	public String queryAll(String orgCode){
		List<ECSY0004> resultList = ecsy0004Service.queryAll();
		return ResultDto.SUCCESS(resultList);
	}

	/**
	 * 条件查询
	 * @param ecsy0004
	 * @return
	 */
	@PostMapping(value = "/queryBy")
	public String queryBy(ECSY0004 ecsy0004){
		try {
			List<ECSY0004> ecsy0004List = ecsy0004Service.queryBy(ecsy0004);
			log.info("【数据字典记录】条件查询，查询成功！共{}",ecsy0004List.size()+"条数据！");
			return ResultDto.SUCCESS("【首页表配置】条件查询，查询成功！",ecsy0004List);
		}catch (Exception e){
			log.info("【数据字典记录】条件查询，查询失败！原因",e.getMessage());
			return ResultDto.SUCCESS("【数据字典记录】条件查询，查询失败！原因",e.toString());
		}
	}
	/**
	*新增
	* @param ecsy0004
	* @return
	*/
	@PostMapping(value = "/insert")
	public String insert(ECSY0004 ecsy0004){
		try {
			ecsy0004Service.insert(ecsy0004);
			log.info("【新增数据字典记录】 新增成功!");
			return ResultDto.SUCCESS("新增成功！");
		} catch (Exception e) {
			log.error("【新增数据字典记录】 新增失败，失败原因：{}",e.getMessage());
			return ResultDto.ERROR("【新增数据字典记录】新增失败！",e.getMessage());
		}
	}

	/**
	*更新
	* @param ecsy0004
	* @return
	*/
	@PostMapping(value = "/update")
	public String update(ECSY0004 ecsy0004){
		try {
			ecsy0004Service.update(ecsy0004);
			log.info("【更新数据字典记录】 更新成功!");
			return ResultDto.SUCCESS("更新成功！");
		} catch (Exception e) {
			log.error("【更新数据字典记录】 更新失败，失败原因：{}",e.getMessage());
			return ResultDto.ERROR("【更新数据字典记录】更新失败！",e.getMessage());
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
			ecsy0004Service.deleteById(id);
			log.info("【删除数据字典记录】 删除成功!");
			return ResultDto.SUCCESS("删除成功！");
		} catch (Exception e) {
			log.error("【删除数据字典记录】 删除失败，失败原因：{}",e.getMessage());
			return ResultDto.ERROR("【删除数据字典记录】删除失败！",e.getMessage());
		}
	}
	/**
	 * 导出
	 * @param uploadFrom 查询参数
	 * @param columnList 表格列参数
	 * @param response
	 * @return
	 */
	@PostMapping(value = "/exportTable")
	public String exportTable(ECSY0004 ecsy0004,String columnList,HttpServletResponse response,String uploadFrom){
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
			if(columnArr_field[i].equals("isConnectStr")){
				columnArr_field[i]="isConnectStrString";
			}
			columnArr_title[i] = String.valueOf(columnMapList.get(i).get("title"));
		}
		List<ECSY0004> resultList = new ArrayList();
		ecsy0004 = JsonUtil.json2Bean(uploadFrom,ECSY0004.class);
		resultList = ecsy0004Service.queryBy(ecsy0004);

		for (ECSY0004 item : resultList){
			if (item.getIsConnectStr() != null && item.getIsConnectStr() == 1){
				item.setIsConnectStrString("是");
			} else if (item.getIsConnectStr() != null && item.getIsConnectStr() == 0){
				item.setIsConnectStrString("否");
			}
		}

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


