package com.fijo.ebox.modular.wt.controller;;

import com.fijo.ebox.base.annotation.InterfaceLimitAnnotation;
import com.fijo.ebox.enums.ModularEnum.ProblemSourceEnum;
import com.fijo.ebox.enums.ModularEnum.QuesSateEnum;
import com.fijo.ebox.modular.sy.pojo.SYFL0001;
import com.fijo.ebox.modular.sy.service.SYFL0001Service;
import com.fijo.ebox.modular.wt.pojo.QUWT0001;
import com.fijo.ebox.modular.wt.service.QUWT0001Service;

import com.fijo.ebox.base.util.plat.*;
import com.fijo.ebox.base.util.common.*;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

import com.fijo.ebox.dto.ResultDto;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
/**
 *CM_PROBLEM_HANDLE_RECORD
 *QUWT0001
 **/
@RestController
@RequestMapping("/QUWT0001")
@Slf4j
public class QUWT0001Controller {
	@Autowired
	private QUWT0001Service quwt0001Service;

	@Autowired
	private SYFL0001Service syfl0001Service;

	/**
	 * 查询
	 * @param orgCode
	 * @return
	 */
	@PostMapping(value = "/queryAll")
	public String queryAll(String orgCode){
		List<QUWT0001> resultList = quwt0001Service.queryAll();
		return ResultDto.SUCCESS(resultList);
	}


	/**
	 * 分页查询
	 * @param orgCode 组织编码
	 * @param pageNo 页码
	 * @param pageSize 页长
	 * @return
	 */
	@PostMapping(value = "/queryAllByPage")
	public String queryAllByPage(String orgCode,Integer pageNo,Integer pageSize){
		try {
			Map result =  quwt0001Service.queryAllByPage(orgCode,pageNo,pageSize);
			return ResultDto.SUCCESS(result);
		} catch (Exception e) {
			return ResultDto.ERROR("查询失败！",e.toString());
		}

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
	@PostMapping(value = "/queryDataListByPage")
	public String queryDataListByPage(String orgCode, String entityTypeCode, String problemName, String happenStratTime, String happenEndTime,
									  Integer state, Integer problemSource, Long problemTypeId, Long problemSmallTypeId, String submitName,
									  Integer pageNo, Integer pageSize){
		try {
			Map result =  quwt0001Service.queryDataListByPage(orgCode,entityTypeCode,problemName,happenStratTime,happenEndTime,
															  state,problemSource,problemTypeId,problemSmallTypeId,submitName,
															  pageNo,pageSize);
			return ResultDto.SUCCESS(result);
		} catch (Exception e) {
			return ResultDto.ERROR("查询失败！",e.toString());
		}

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
	@PostMapping(value = "/queryDataList")
	public String queryDataList(String orgCode, String entityTypeCode, String problemName, String happenStratTime, String happenEndTime,
								Integer state,Integer problemSource, Long problemTypeId, Long problemSmallTypeId,String submitName){
		try {
			List<QUWT0001> resultList =  quwt0001Service.queryDataList(orgCode,entityTypeCode,problemName,happenStratTime,happenEndTime,
																	   state,problemSource,problemTypeId,problemSmallTypeId,submitName);
			return ResultDto.SUCCESS(resultList);
		} catch (Exception e) {
			return ResultDto.ERROR("查询失败！",e.toString());
		}

	}

	/**
	 * 查询
	 * @param id
	 * @return
	 */
	@PostMapping(value = "/queryById")
	public String queryById(Long id){
		QUWT0001 quwt0001 = quwt0001Service.queryById(id);
		return ResultDto.SUCCESS(quwt0001);
	}

	/**
	 * 查询
	 * @param id
	 * @return
	 */
	@PostMapping(value = "/queryDataById")
	public String queryDataById(Long id){
		Map map = new HashMap();
		QUWT0001 quwt0001 = quwt0001Service.queryById(id);
		//查询问题附件
		List<SYFL0001> syfl0001List = new ArrayList<>();
		if(quwt0001 != null){
			syfl0001List = syfl0001Service.queryFile(quwt0001.getOrgCode(),quwt0001.getEntityTypeCode(),quwt0001.getId());
		}
		map.put("dataInfo",quwt0001);
		map.put("fileInfo",syfl0001List);
		return ResultDto.SUCCESS(map);
	}

	/**
	* 新增
	* @param quwt0001
	* @return
	*/
	@PostMapping(value = "/insert")
	public String insert(QUWT0001 quwt0001){
		try {
			//新增问题数据
			quwt0001Service.insert(quwt0001);
			//校验是否有附件
			if(!StringUtil.isNull(quwt0001.getImageDataStr())){
				List<SYFL0001> syfl0001List = JsonUtil.json2List(quwt0001.getImageDataStr(),SYFL0001.class);
				//新增附件
				syfl0001List.stream().forEach(file -> {
					file.setId(null);
					file.setObjectId(quwt0001.getId());
					file.setEntityTypeCode(quwt0001.getEntityTypeCode());
					file.setOrgCode(quwt0001.getOrgCode());
					try {
						syfl0001Service.insert(file);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			}
			log.info("【新增问题记录】 新增成功!");
			return ResultDto.SUCCESS("新增成功！");
		} catch (DuplicateKeyException duplicateKeyException){
			log.error("【新增问题记录】 新增失败，失败原因：主键冲突，异常详细：",duplicateKeyException.toString());
			duplicateKeyException.printStackTrace();
			return ResultDto.ERROR("新增失败，请确认数据是否已存在！","请确认数据是否已存在！");
		} catch (Exception e) {
			log.error("【新增问题记录】 新增失败，失败原因：{}",e.toString());
			e.printStackTrace();
			return ResultDto.ERROR("【新增问题记录】新增失败！",e.toString());
		}
	}

	/**
	*更新
	* @param quwt0001
	* @return
	*/
	@PostMapping(value = "/update")
	public String update(QUWT0001 quwt0001){
		try {
			//更新问题基本信息
			quwt0001Service.update(quwt0001);
			//删除原有附件
			syfl0001Service.logicDeleteFile(quwt0001.getOrgCode(),quwt0001.getEntityTypeCode(), StringUtil.str2SqlStr(quwt0001.getId().toString()),null);
			//重新上传最新的附件
			//校验是否有附件
			if(!StringUtil.isNull(quwt0001.getImageDataStr())){
				List<SYFL0001> syfl0001List = JsonUtil.json2List(quwt0001.getImageDataStr(),SYFL0001.class);
				//新增附件
				syfl0001List.stream().forEach(file -> {
					file.setId(null);
					file.setObjectId(quwt0001.getId());
					file.setEntityTypeCode(quwt0001.getEntityTypeCode());
					file.setOrgCode(quwt0001.getOrgCode());
					try {
						syfl0001Service.insert(file);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			}
			log.info("【更新问题记录】 更新成功!");
			return ResultDto.SUCCESS("更新成功！");
		} catch (DuplicateKeyException duplicateKeyException){
			log.error("【更新问题记录】 更新失败，失败原因：主键冲突，异常详细：",duplicateKeyException.toString());
			duplicateKeyException.printStackTrace();
			return ResultDto.ERROR("更新失败，请确认数据是否已存在！","请确认数据是否已存在！");
		}catch (Exception e) {

			log.error("【更新问题记录】 更新失败，失败原因：{}",e.toString());
			e.printStackTrace();
			return ResultDto.ERROR("【更新问题记录】更新失败！",e.toString());
		}
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
	@PostMapping(value = "/quesHandle")
	public String quesHandle(Long id, Integer state, String endTime, Long endUserId, String endName, String endExplain, String imageDataStr){
		try {
			//状态名称
			String stateName = QuesSateEnum.getEnumMsgByCode(state);
			quwt0001Service.quesHandle(id,state,stateName,endTime,endUserId,endName,endExplain);
			//查询详情
			QUWT0001 quwt0001 = quwt0001Service.queryById(id);

			//校验是否有附件
			if(!StringUtil.isNull(imageDataStr)){
				List<SYFL0001> syfl0001List = JsonUtil.json2List(imageDataStr,SYFL0001.class);
				//新增附件
				syfl0001List.stream().forEach(file -> {
					file.setId(null);
					file.setObjectId(id);
					file.setEntityTypeCode(quwt0001.getEntityTypeCode());
					file.setOrgCode(quwt0001.getOrgCode());
					try {
						syfl0001Service.insert(file);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			}
			log.info("【问题处理】 处理成功!");
			return ResultDto.SUCCESS("处理成功！");
		} catch (Exception e) {
			log.error("【问题处理】 处理失败，失败原因：{}",e.toString());
			e.printStackTrace();
			return ResultDto.ERROR("【问题处理】处理失败！",e.toString());
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
			quwt0001Service.deleteById(id);
			log.info("【删除问题记录】 删除成功!");
			return ResultDto.SUCCESS("删除成功！");
		} catch (Exception e) {
			log.error("【删除问题记录】 删除失败，失败原因：{}",e.toString());
			e.printStackTrace();
			return ResultDto.ERROR("【删除问题记录】删除失败！",e.toString());
		}
	}

	/**
	 *删除
	 * @param id
	 * @return
	 */
	@PostMapping(value = "/logicDeleteById")
	public String logicDeleteById(String id){
		try {
			quwt0001Service.logicDeleteById(id);
			log.info("【删除问题记录】 删除成功!");
			return ResultDto.SUCCESS("删除成功！");
		} catch (Exception e) {
			log.error("【删除问题记录】 删除失败，失败原因：{}",e.toString());
			e.printStackTrace();
			return ResultDto.ERROR("【删除问题记录】删除失败！",e.toString());
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
		try {
			QUWT0001 quwt0001 =  new QUWT0001();
			quwt0001 = JsonUtil.json2Bean(queryStr,QUWT0001.class);
			String entityTypeCode = null;
			if(quwt0001.getEntityTypeCode() != "" && quwt0001.getEntityTypeCode() != null){
				entityTypeCode = quwt0001.getEntityTypeCode();
			}
			String sheetName = "问题记录导出excel";

			Type type = new TypeToken<Map<String,String>>(){}.getType();
			Map<String,String> paramMap = JsonUtil.json2BeanByType(queryStr,type);
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

			List<QUWT0001> resultList = new ArrayList();
			resultList = quwt0001Service.queryDataList(StringUtil.str2SqlStr(quwt0001.getOrgCode()),entityTypeCode,quwt0001.getProblemName(),
					quwt0001.getHappenStratTime(),quwt0001.getHappenEndTime(),quwt0001.getState(),quwt0001.getProblemSource(),quwt0001.getProblemTypeId(),
					quwt0001.getProblemSmallTypeId(),quwt0001.getSubmitName());

			//如果输出结果，存在需要进行类型转换的，例如属性枚举类型的，需要将枚举转为对应字符串的参考如下配置
			Map<String,Map<String,String>> outPutConfig = new HashMap(); //定义配置项
			//如果是枚举类型的，可以直接使用如下util直接转换
			outPutConfig.put("problemSource",EnumUtil.getEnumMap(ProblemSourceEnum.class));//获取枚举的key，value 类型的map
			//如果不是枚举类型，可以自定义转换配置，
			//Map<String,String> outPutFieldConfig = new HashMap<>();
			//outPutFieldConfig.put("1","男");
			//outPutFieldConfig.put("0","女");

			XSSFWorkbook xssfWorkbook = ExcelUtil.initExport(sheetName, sheetName, columnArr_field, columnArr_title, resultList,null,outPutConfig);


			//获取处理好的工作薄对象，为导出做准备
			//XSSFWorkbook xssfWorkbook = ExcelUtil.initExport(sheetName, sheetName, columnArr_field, columnArr_title, resultList,null);
			//获取导出文件名
			String fileName = DateUtils.getNowDateYMD()+sheetName+".xlsx";
			String result = null;

			result = FileUtil.downloadExcel(response, fileName, xssfWorkbook);
			return ResultDto.SUCCESS( result);
		} catch (Exception e) {
			log.error("【导出文件】失败，失败原因：{}",e.toString());
			e.printStackTrace();
			return ResultDto.ERROR(e.toString());
		}

	}

	/**
	 * 导出全量模板
	 *
	 * @param response
	 * @return
	 */
	@PostMapping(value = "/downloadTemplate")
	public String downloadTemplate(HttpServletResponse response){
		String sheetName = "导入模板";
		try {
			List<String> ignoreFieldList = Arrays.asList("id","sort","remark");//需要忽略的字段
			Map<String,String[]> fieldMap = PlatPojoUtil.getClazzFieldsInfo(QUWT0001.class,ignoreFieldList);//TODO
			String [] columnArr_field = fieldMap.get(PlatPojoUtil.FIELD_EN_KEY);
			String [] columnArr_title = fieldMap.get(PlatPojoUtil.FIELD_CN_KEY);
			Map<String ,String[]> filedOptionConfig = new HashMap<>();
			//filedOptionConfig.put("xxx",new String[] {"aaa","bbb","ccc"}); key 为字段名，value 为下拉列表选项值的字符串数组
			//如果下拉选项数据源为枚举，可以直接使用一下方法处理
			//filedOptionConfig.put("xxx",EnumUtil.getEnumValue(SexEnum.class));
			//获取处理好的工作薄对象，为导出做准备
			XSSFWorkbook xssfWorkbook = ExcelUtil.initExport(sheetName, sheetName, columnArr_field, columnArr_title, new ArrayList<>(),filedOptionConfig);
			XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
			//获取导出文件名
			String fileName = DateUtils.getNowDateYMD()+sheetName+".xlsx";
			String result = null;
			try {
				result = FileUtil.downloadExcel(response, fileName, xssfWorkbook);
				return ResultDto.SUCCESS( result);
			} catch (Exception e) {

				log.error("【导出文件】失败，失败原因：{}",e.toString());
				e.printStackTrace();
				return ResultDto.ERROR(e.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultDto.ERROR("下载模板失败！",e.toString());
		}
	}

	/**
	 * 查询自定义字段属性列表
	 * @return
	 */
	@PostMapping(value = "/queryTemplateFields")
	public String queryTemplateFields() {
		List<String> ignoreFieldList = Arrays.asList("id","sort","remark");//需要忽略的字段
		Map<String,String[]> fieldMap = null;
		List<Map> resultList = new ArrayList<>();
		try {
			fieldMap = PlatPojoUtil.getClazzFieldsInfo(QUWT0001.class,ignoreFieldList);
			String [] columnArr_field = fieldMap.get(PlatPojoUtil.FIELD_EN_KEY);
			String [] columnArr_title = fieldMap.get(PlatPojoUtil.FIELD_CN_KEY);
			for(int i = 0 ; i < columnArr_field.length; i++){
				Map fieldConfig = new HashMap();
				fieldConfig.put(PlatPojoUtil.FIELD_EN_KEY,columnArr_field[i]);
				fieldConfig.put(PlatPojoUtil.FIELD_CN_KEY,columnArr_title[i]);
				resultList.add(fieldConfig);
			}
		} catch (Exception e) {
			log.info("获取实体字段失败，失败原因：{}",e.toString());
			e.printStackTrace();
			return ResultDto.ERROR("获取实体字段失败",e.toString());
		}
		return ResultDto.SUCCESS(resultList);
	}



	/**
	 * 导出自定义模板
	 * @param fieldConfigStr 自定义字段配置json串
	 * @param response
	 * @return
	 */
	@PostMapping(value = "/downloadCustomTemplate")
	public String downloadCustomTemplate(String fieldConfigStr,HttpServletResponse response){
		String sheetName = "导入模板";

		List<Map<String,String>> fieldConfigList  = (List<Map<String,String>>)JsonUtil.json2BeanList(fieldConfigStr,Map.class);
		String [] columnArr_field = fieldConfigList.stream()
										.map(_field -> _field.get(PlatPojoUtil.FIELD_EN_KEY))
										.collect(Collectors.toList())
										.stream().toArray(fieldEn -> new String[fieldEn]);

		String [] columnArr_title = fieldConfigList.stream()
										.map(_title -> _title.get(PlatPojoUtil.FIELD_CN_KEY))
										.collect(Collectors.toList())
										.stream().toArray(fieldCN -> new String[fieldCN]);
		try {
			Map<String ,String[]> filedOptionConfig = new HashMap<>();
			//filedOptionConfig.put("xxx",new String[] {"aaa","bbb","ccc"}); key 为字段名，value 为下拉列表选项值的字符串数组
			//获取处理好的工作薄对象，为导出做准备
			XSSFWorkbook xssfWorkbook = ExcelUtil.initExport(sheetName, sheetName, columnArr_field, columnArr_title, new ArrayList<>(),filedOptionConfig);
			XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
			//获取导出文件名
			String fileName = DateUtils.getNowDateYMD()+sheetName+".xlsx";
			String result = null;
			try {
				result = FileUtil.downloadExcel(response, fileName, xssfWorkbook);
				return ResultDto.SUCCESS( result);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("【导出文件】失败，失败原因：{}",e);
				return ResultDto.ERROR(e.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultDto.ERROR("下载模板失败！",e.toString());
		}
	}

	/**
	 * 导入模板
	 * @param file
	 * @return
	 */
	@PostMapping(value = "/importFile")
	@InterfaceLimitAnnotation
	public String importFile( MultipartFile file) {
			log.info(file.getOriginalFilename());
			List<QUWT0001> errorList = new ArrayList<>();
			try {
				//1.定义枚举类配置
				Map<String,Class> enumConfig = new HashMap<>();
				//enumConfig.put("reqType", ReqTypeEnum.class);//定义枚举类型的字段，map中的key为字段名，value为枚举对应的枚举类 枚举类中必须定义getEnumCodeByMsg方法
				List resultList = PlatExeclUtil.readExecl(file,QUWT0001.class,0,1,3,enumConfig);
				resultList.stream().forEach( t ->{
					QUWT0001 quwt0001 = (QUWT0001) t;
					try {
						quwt0001Service.insert(quwt0001);
					} catch (Exception e) {
						errorList.add(quwt0001);
						e.printStackTrace();
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
				return ResultDto.ERROR("文件导入失败！",e.toString());
			}
			if(errorList.size()>0){
				return ResultDto.ERROR("存在"+errorList.size()+"条数据导入失败!",errorList);
			}else{
				return ResultDto.SUCCESS("导入成功");
			}
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
	@PostMapping(value = "/queryDataListByWx")
	public String queryDataListByWx(String orgCode,String entityTypeCode,String keyword,Integer problemSource, Integer state,
									Long problemTypeId, Long problemSmallTypeId, String happenStratTime, String happenEndTime, Integer pageNo, Integer pageSize){
		try {
			orgCode = StringUtil.str2SqlStr(orgCode);
			Map result = quwt0001Service.queryDataListByWx(orgCode,entityTypeCode,keyword,problemSource,state,problemTypeId,problemSmallTypeId,happenStratTime,happenEndTime,pageNo,pageSize);
			return ResultDto.SUCCESS(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultDto.ERROR("查询失败！",e.toString());
		}
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
	@PostMapping(value = "/queryDataNumberBySate")
	public String queryDataNumberBySate(String orgCode,String entityTypeCode,String keyword,Integer problemSource, Integer state,
										Long problemTypeId, Long problemSmallTypeId, String happenStratTime, String happenEndTime){
		try {
			orgCode = StringUtil.str2SqlStr(orgCode);
			List<Map> result = quwt0001Service.queryDataNumberBySate(orgCode,entityTypeCode,keyword,problemSource,state,problemTypeId,problemSmallTypeId,happenStratTime,happenEndTime);
			return ResultDto.SUCCESS(result);
		}catch (Exception e){
			e.printStackTrace();
			return ResultDto.ERROR("查询失败！",e.toString());
		}
	}

	/**
	 * 新增
	 * @param quwt0001
	 * @return
	 */
	@PostMapping(value = "/insertForWx")
	public String insertForWx(@RequestBody QUWT0001 quwt0001){
		try {
			String stateName = QuesSateEnum.getEnumMsgByCode(quwt0001.getState());
			quwt0001.setStateName(stateName);
			//新增问题数据
			quwt0001Service.insert(quwt0001);
			//校验是否有附件
			if(!StringUtil.isNull(quwt0001.getImageDataStr())){
				List<SYFL0001> syfl0001List = JsonUtil.json2List(quwt0001.getImageDataStr(),SYFL0001.class);
				//新增附件
				syfl0001List.stream().forEach(file -> {
					file.setId(null);
					file.setObjectId(quwt0001.getId());
					file.setEntityTypeCode(quwt0001.getEntityTypeCode());
					file.setOrgCode(quwt0001.getOrgCode());
					try {
						syfl0001Service.insert(file);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			}
			log.info("【新增问题记录】 新增成功!");
			return ResultDto.SUCCESS("新增成功！");
		} catch (DuplicateKeyException duplicateKeyException){
			log.error("【新增问题记录】 新增失败，失败原因：主键冲突，异常详细：",duplicateKeyException.toString());
			duplicateKeyException.printStackTrace();
			return ResultDto.ERROR("新增失败，请确认数据是否已存在！","请确认数据是否已存在！");
		} catch (Exception e) {
			log.error("【新增问题记录】 新增失败，失败原因：{}",e.toString());
			e.printStackTrace();
			return ResultDto.ERROR("【新增问题记录】新增失败！",e.toString());
		}
	}

	/**
	 *更新
	 * @param quwt0001
	 * @return
	 */
	@PostMapping(value = "/updateForWx")
	public String updateForWx(@RequestBody QUWT0001 quwt0001){
		try {
			//更新问题基本信息
			quwt0001Service.update(quwt0001);
			//删除原有附件
			syfl0001Service.logicDeleteFile(quwt0001.getOrgCode(),quwt0001.getEntityTypeCode(), StringUtil.str2SqlStr(quwt0001.getId().toString()),null);
			//重新上传最新的附件
			//校验是否有附件
			if(!StringUtil.isNull(quwt0001.getImageDataStr())){
				List<SYFL0001> syfl0001List = JsonUtil.json2List(quwt0001.getImageDataStr(),SYFL0001.class);
				//新增附件
				syfl0001List.stream().forEach(file -> {
					file.setId(null);
					file.setObjectId(quwt0001.getId());
					file.setEntityTypeCode(quwt0001.getEntityTypeCode());
					file.setOrgCode(quwt0001.getOrgCode());
					try {
						syfl0001Service.insert(file);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			}
			log.info("【更新问题记录】 更新成功!");
			return ResultDto.SUCCESS("更新成功！");
		} catch (DuplicateKeyException duplicateKeyException){
			log.error("【更新问题记录】 更新失败，失败原因：主键冲突，异常详细：",duplicateKeyException.toString());
			duplicateKeyException.printStackTrace();
			return ResultDto.ERROR("更新失败，请确认数据是否已存在！","请确认数据是否已存在！");
		}catch (Exception e) {

			log.error("【更新问题记录】 更新失败，失败原因：{}",e.toString());
			e.printStackTrace();
			return ResultDto.ERROR("【更新问题记录】更新失败！",e.toString());
		}
	}
}



