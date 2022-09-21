package com.fijo.ebox.modular.sy.controller;;
import com.fijo.ebox.base.util.plat.ExcelUtil;
import com.fijo.ebox.base.util.plat.FileUtil;
import com.fijo.ebox.modular.sy.pojo.ECSY0009;
import com.fijo.ebox.modular.sy.service.ECSY0009Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import com.fijo.ebox.dto.ResultDto;
import com.fijo.ebox.base.util.common.DateUtils;
import com.fijo.ebox.base.util.plat.JsonUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.servlet.http.HttpServletResponse;
/**
 *SYS_MSG
 *ECSY0009
 **/
@RestController
@RequestMapping("/ECSY0009")
@Slf4j
public class ECSY0009Controller {
	@Autowired
	private ECSY0009Service ecsy0009Service;

	/**
	 * 查询
	 * @param orgCode
	 * @return
	 */
	@PostMapping(value = "/queryAll")
	public String queryAll(String orgCode){
		List<ECSY0009> resultList = ecsy0009Service.queryAll();
		return ResultDto.SUCCESS(resultList);
	}
	@PostMapping(value = "/queryList")
	public String queryList(ECSY0009 ecsy0009){
		List<ECSY0009> resultList = ecsy0009Service.queryList(ecsy0009);
		return ResultDto.SUCCESS(resultList);
	}
	/**
	 * 查询
	 * @param id
	 * @return
	 */
	@PostMapping(value = "/queryById")
	public String queryById(Long id){
		ECSY0009 ecsy0009 = ecsy0009Service.queryById(id);
		return ResultDto.SUCCESS(ecsy0009);
	}
	/**
	* 新增
	* @param ecsy0009
	* @return
	*/
	@PostMapping(value = "/insert")
	public String insert(ECSY0009 ecsy0009){
		try {
			ecsy0009.setState(false);
			if(ecsy0009.getPhoneMsg()){
				ecsy0009.setPhoneMsgState(false);
			}
			ecsy0009Service.insert(ecsy0009);
			log.info("【新增xxx】 新增成功!");
			return ResultDto.SUCCESS("新增成功！");
		} catch (Exception e) {
			log.error("【新增xxx】 新增失败，失败原因：{}",e);
			return ResultDto.ERROR("【新增xxx】新增失败！",e.toString());
		}
	}

	/**
	*更新
	* @param ecsy0009
	* @return
	*/
	@PostMapping(value = "/update")
	public String update(ECSY0009 ecsy0009){
		try {
			ecsy0009Service.update(ecsy0009);
			log.info("【更新xxx】 更新成功!");
			return ResultDto.SUCCESS("更新成功！");
		} catch (Exception e) {
			log.error("【更新xxx】 更新失败，失败原因：{}",e);
			return ResultDto.ERROR("【更新xxx】更新失败！",e.toString());
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
			ecsy0009Service.deleteById(id);
			log.info("【删除xxx】 删除成功!");
			return ResultDto.SUCCESS("删除成功！");
		} catch (Exception e) {
			log.error("【删除xxx】 删除失败，失败原因：{}",e);
			return ResultDto.ERROR("【删除xxx】删除失败！",e.toString());
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
		ECSY0009 ecsy0009 =  new ECSY0009();
		ecsy0009 = JsonUtil.json2Bean(queryStr,ECSY0009.class);
		String sheetName = "excel名字 xxx";
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

		List<ECSY0009> resultList = new ArrayList();
		resultList = ecsy0009Service.queryAll();

		//获取处理好的工作薄对象，为导出做准备
		XSSFWorkbook xssfWorkbook = ExcelUtil.initExport(sheetName, sheetName, columnArr_field, columnArr_title, resultList,null);
		//获取导出文件名
		String fileName = DateUtils.getNowDateYMD()+sheetName+".xlsx";
		String result = null;
		try {
			result = FileUtil.downloadExcel(response, fileName, xssfWorkbook);
			return ResultDto.SUCCESS( result);
		} catch (Exception e) {
			log.error("【导出文件】失败，失败原因：{}",e);
			return ResultDto.ERROR(e);
		}

	}

	/**
	 * 获取消息列表
	 * @param ecsy0009
	 * @return
	 */
	@PostMapping(value = "getMsgList")
	public String getMsgList(ECSY0009 ecsy0009){
		try{
			List<Map> mapList=new ArrayList<>();
			List<ECSY0009> ecsy0009List=ecsy0009Service.queryMsgBySrare(ecsy0009);
			ECSY0009 ecsy00091=new ECSY0009();
			ecsy00091.setEntitiyTypeCode("ECKD0001");
			List<ECSY0009> ecsy0009s=ecsy0009Service.queryMsgBySrare(ecsy00091);
			ecsy0009List.addAll(ecsy0009s);
			for(ECSY0009 ecsy00091s:ecsy0009List){
				Map map=new HashMap();
				map.put("id",ecsy00091s.getId());
				map.put("objectId",ecsy00091s.getObjectId());
				map.put("entityTypeCode",ecsy00091s.getEntitiyTypeCode());
				map.put("msgFrom",ecsy00091s.getMsgFrom());
				mapList.add(map);
			}
			return ResultDto.SUCCESS(mapList);
		}catch (Exception e){
			return ResultDto.ERROR("查询失败");
		}
	}


	/**
	 * 更新阅读状态
	 * @return
	 */
	@PostMapping(value = "updateState")
	public String updateState(Long id,Long userId){
		try{
			ecsy0009Service.updateState(id,userId);
			return ResultDto.SUCCESS("更新状态成功");
		}catch (Exception e){
			return ResultDto.ERROR("查询失败");
		}
	}


	@PostMapping(value = "updateStateList")
	public String updateStateList(String id){
		try{
			String[] ids = id.split(",");
			for(String _id : ids){
				ecsy0009Service.updateState(Long.valueOf(_id),null);
			}
			return ResultDto.SUCCESS("更新状态成功");
		}catch (Exception e){
			log.error("修改状态失败，失败原因【】"+e);
			return ResultDto.ERROR("修改状态失败");
		}
	}


	@PostMapping(value = "getMsgListByUser")
	public String getMsgListByUser(Long userId,int msgFrom){
		try{
			ECSY0009 ecsy0009=new ECSY0009();
			ecsy0009.setUserId(userId);
			ecsy0009.setMsgFrom(msgFrom);
			List<ECSY0009> ecsy0009List=ecsy0009Service.getMsgListByUser(ecsy0009);
			return ResultDto.SUCCESS(ecsy0009List);
		}catch (Exception e){
			return ResultDto.ERROR("查询失败");
		}
	}



	/**
	 * 更新阅读状态
	 * @param Id
	 * @return
	 */
	@PostMapping(value = "updateMsgState")
	public String updateMsgState(String id){
		try{
			ecsy0009Service.updateMsgReadState(DateUtils.getNowDateYMDHMS(),id);
			return ResultDto.SUCCESS("更新状态成功");
		}catch (Exception e){
			return ResultDto.ERROR("查询失败");
		}
	}

}



