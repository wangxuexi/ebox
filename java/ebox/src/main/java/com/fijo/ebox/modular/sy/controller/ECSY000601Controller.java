package com.fijo.ebox.modular.sy.controller;


import com.fijo.ebox.base.annotation.InterfaceLimitAnnotation;
import com.fijo.ebox.base.util.common.DateUtils;
import com.fijo.ebox.base.util.common.StringUtil;
import com.fijo.ebox.base.util.plat.*;
import com.fijo.ebox.dto.ResultDto;
import com.fijo.ebox.modular.sy.pojo.ECSY0005;
import com.fijo.ebox.modular.sy.pojo.ECSY0006;
import com.fijo.ebox.modular.sy.pojo.ECSY000601;
import com.fijo.ebox.modular.sy.service.ECSY0005Service;
import com.fijo.ebox.modular.sy.service.ECSY000601Service;
import com.fijo.ebox.modular.sy.service.ECSY0006Service;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

;

/**
 *ORG_TAG_CONFIG
 *ECSY000601
 **/
@RestController
@RequestMapping("/ECSY000601")
@Slf4j
public class ECSY000601Controller {
	@Autowired
	private ECSY000601Service ecsy000601Service;

	@Autowired
	private ECSY0006Service ecsy0006Service;

	@Autowired
	private ECSY0005Service ecsy0005Service;

	/**
	 * 查询
	 * @param orgCode
	 * @return
	 */
	@PostMapping(value = "/queryAll")
	public String queryAll(String orgCode){
		List<ECSY000601> resultList = ecsy000601Service.queryAll();
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
			Map result =  ecsy000601Service.queryAllByPage(orgCode,pageNo,pageSize);
			return ResultDto.SUCCESS(result);
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
		ECSY000601 ecsy000601 = ecsy000601Service.queryById(id);
		return ResultDto.SUCCESS(ecsy000601);
	}
	/**
	* 新增
	* @param ecsy000601
	* @return
	*/
	@PostMapping(value = "/insert")
	public String insert(ECSY000601 ecsy000601){
		try {
			ecsy000601Service.insert(ecsy000601);
			log.info("【新增xxx】 新增成功!");
			return ResultDto.SUCCESS("新增成功！");
		} catch (DuplicateKeyException duplicateKeyException){
			log.error("【新增xxx】 新增失败，失败原因：主键冲突，异常详细：",duplicateKeyException.toString());
			duplicateKeyException.printStackTrace();
			return ResultDto.ERROR("新增失败，请确认数据是否已存在！","请确认数据是否已存在！");
		} catch (Exception e) {
			log.error("【新增xxx】 新增失败，失败原因：{}",e.toString());
			e.printStackTrace();
			return ResultDto.ERROR("【新增xxx】新增失败！",e.toString());
		}
	}

	/**
	*更新
	* @param ecsy000601
	* @return
	*/
	@PostMapping(value = "/update")
	public String update(ECSY000601 ecsy000601){
		try {
			ecsy000601Service.update(ecsy000601);
			log.info("【更新xxx】 更新成功!");
			return ResultDto.SUCCESS("更新成功！");
		} catch (DuplicateKeyException duplicateKeyException){
			log.error("【更新xxx】 更新失败，失败原因：主键冲突，异常详细：",duplicateKeyException.toString());
			duplicateKeyException.printStackTrace();
			return ResultDto.ERROR("更新失败，请确认数据是否已存在！","请确认数据是否已存在！");
		}catch (Exception e) {

			log.error("【更新xxx】 更新失败，失败原因：{}",e.toString());
			e.printStackTrace();
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
			ecsy000601Service.deleteById(id);
			log.info("【删除xxx】 删除成功!");
			return ResultDto.SUCCESS("删除成功！");
		} catch (Exception e) {
			log.error("【删除xxx】 删除失败，失败原因：{}",e.toString());
			e.printStackTrace();
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
		try {
			ECSY000601 ecsy000601 =  new ECSY000601();
			ecsy000601 = JsonUtil.json2Bean(queryStr,ECSY000601.class);
			String sheetName = "excel名字 xxx";

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

			List<ECSY000601> resultList = new ArrayList();
			resultList = ecsy000601Service.queryAll();

			//如果输出结果，存在需要进行类型转换的，例如属性枚举类型的，需要将枚举转为对应字符串的参考如下配置
			//Map<String,Map<String,String>> outPutConfig = new HashMap(); //定义配置项
			//如果是枚举类型的，可以直接使用如下util直接转换
			//outPutConfig.put("sex",EnumUtil.getEnumMap(SexEnum.class));//获取枚举的key，value 类型的map
			//如果不是枚举类型，可以自定义转换配置，
			//Map<String,String> outPutFieldConfig = new HashMap<>();
			//outPutFieldConfig.put("1","男");
			//outPutFieldConfig.put("0","女");

			//XSSFWorkbook xssfWorkbook = ExcelUtil.initExport(sheetName, sheetName, columnArr_field, columnArr_title, resultList,null,outPutConfig);


			//获取处理好的工作薄对象，为导出做准备
			XSSFWorkbook xssfWorkbook = ExcelUtil.initExport(sheetName, sheetName, columnArr_field, columnArr_title, resultList,null);
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
			Map<String,String[]> fieldMap = PlatPojoUtil.getClazzFieldsInfo(ECSY000601.class,ignoreFieldList);//TODO
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
			fieldMap = PlatPojoUtil.getClazzFieldsInfo(ECSY000601.class,ignoreFieldList);
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
			List<ECSY000601> errorList = new ArrayList<>();
			try {
				//1.定义枚举类配置
				Map<String,Class> enumConfig = new HashMap<>();
				//enumConfig.put("reqType", ReqTypeEnum.class);//定义枚举类型的字段，map中的key为字段名，value为枚举对应的枚举类 枚举类中必须定义getEnumCodeByMsg方法
				List resultList = PlatExeclUtil.readExecl(file,ECSY000601.class,0,1,3,enumConfig);
				resultList.stream().forEach( t ->{
					ECSY000601 ecsy000601 = (ECSY000601) t;
					try {
						ecsy000601Service.insert(ecsy000601);
					} catch (Exception e) {
						errorList.add(ecsy000601);
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
	 * 查询组织挑选的标签
	 * @param orgCode			组织编码
	 * @param tagTypeCode		标签类型CODE
	 * @param tagName			标签名称
	 * @return
	 */
	@PostMapping(value = "/queryOrgChooseTag")
	public String queryOrgChooseTag(String orgCode,String tagTypeCode,String tagName){
		try {
			if(!StringUtil.isNull(orgCode)){
				orgCode = StringUtil.str2SqlStr(orgCode);
			}
			if(!StringUtil.isNull(tagName)){
				tagName = StringUtil.str2SqlStr(tagName);
			}
			List<ECSY0006> ecsy0006List = ecsy000601Service.queryOrgChooseTag(orgCode,tagTypeCode,tagName);
			log.info("【查询组织挑选的标签】查询成功！");
			return ResultDto.SUCCESS("【查询组织挑选的标签】查询成功！",ecsy0006List);
		}catch (Exception e){
			log.error("【查询组织挑选的标签】查询失败！原因：",e.getMessage());
			return ResultDto.ERROR("【查询组织挑选的标签】查询失败！原因：",e.toString());
		}
	}

	/**
	 * 查询未挑选的标签
	 *
	 * @param tagTypeCode 标签类型CODE
	 * @param tagIds    标签ID集合
	 * @param tagName   标签名称
	 * @return
	 */
	@PostMapping(value = "/queryUnselectedTag")
	public String queryUnselectedTag(String tagTypeCode,String tagIds, String tagName){
		try {
			if(!StringUtil.isNull(tagIds)){
				tagIds = StringUtil.str2SqlStr(tagIds);
			}
			if(!StringUtil.isNull(tagName)){
				tagName = StringUtil.str2SqlStr(tagName);
			}
			List<ECSY0006> ecsy0006List = ecsy000601Service.queryUnselectedTag(tagTypeCode,tagIds,tagName);
			log.info("【查询未挑选的标签】查询成功！");
			return ResultDto.SUCCESS("【查询未挑选的标签】查询成功！",ecsy0006List);
		}catch (Exception e){
			log.error("【查询未挑选的标签】查询失败！原因：",e.getMessage());
			return ResultDto.ERROR("【查询未挑选的标签】查询失败！原因：",e.toString());
		}
	}


	/**
	 * 组织挑选标签
	 * @param tagIds		标签ID
	 * @param orgCode		组织编码
	 * @param tagTypeCode	标签类型编码
	 * @return
	 */
	@PostMapping(value = "/addChooseTag")
	public String addChooseTag(String tagIds, String orgCode, String tagTypeCode){
		try {
			//处理选中的ID数据
			String[] idArr = tagIds.split(",");
			//查询标签类型信息
			ECSY0005 ecsy0005 = ecsy0005Service.queryBYCode(tagTypeCode);
			//查询该类型下的标签
			List<ECSY0006> ecsy0006List = ecsy0006Service.queryBytagTypeCode(ecsy0005.getTagTypeCode());
			//开始处理数据
			for (String _id : idArr) {
				ecsy0006List.stream().forEach(ecsy0006 -> {
					//如果是选中数据则进行新增
					if(_id.equals(ecsy0006.getId().toString())){
						ECSY000601 ecsy000601 = new ECSY000601();
						ecsy000601.setOrgCode(orgCode);	//组织编码
						ecsy000601.setTagId(ecsy0006.getId());	//标签ID
						ecsy000601.setTagCode(ecsy0006.getTagCode()); //标签编码
						ecsy000601.setTagTypeCode(ecsy0005.getTagTypeCode());	//标签类型编码
						//开始新增
						try {
							ecsy000601Service.insert(ecsy000601);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			log.info("【组织挑选标签】挑选成功！");
			return ResultDto.SUCCESS("【组织挑选标签】挑选成功！");
		}catch (Exception e){
			log.error("【组织挑选标签】挑选失败！原因：",e.getMessage());
			return ResultDto.ERROR("【组织挑选标签】挑选失败！原因：",e.toString());
		}
	}

	/**
	 * 移除组织选择的标签
	 * @param orgCode	组织编码
	 * @param tagIds	标签Id集合
	 * @return
	 */
	@PostMapping(value = "/removeChooseTag")
	public String removeChooseTag(String orgCode,String tagIds){
		try {
			//处理选中的ID数据
			String[] idArr = tagIds.split(",");
			//开始处理数据
			for (String _id : idArr) {
				ecsy000601Service.deleteTagOrgConfig(orgCode,Long.valueOf(_id));
			}
			log.info("【组织移除标签】移除成功！");
			return ResultDto.SUCCESS("【组织移除标签】移除成功！");
		}catch (Exception e){
			log.error("【组织移除标签】移除失败！原因：",e.getMessage());
			return ResultDto.ERROR("【组织移除标签】移除失败！原因：",e.toString());
		}
	}

	/**
	 * 查询组织下的标签
	 * @param orgCode		组织编码
	 * @param tagName		标签名称
	 * @param tagTypeName	标签类别名称
	 * @return
	 */
	@PostMapping(value = "/queryOrgInTagDataList")
	public String queryOrgInTagDataList(String orgCode,String tagName, String tagTypeName){
		try {
			if(!StringUtil.isNull(orgCode)){
				orgCode = StringUtil.str2SqlStr(orgCode);
			}
			List<ECSY0006> ecsy0006List = ecsy000601Service.queryOrgInTagDataList(orgCode,tagTypeName,tagName);
			log.info("【查询组织下的标签】查询成功！");
			return ResultDto.SUCCESS("【查询组织下的标签】查询成功！",ecsy0006List);
		}catch (Exception e){
			log.error("【查询组织下的标签】查询失败！原因：",e.toString());
			return ResultDto.ERROR("【查询组织下的标签】查询失败！原因：",e.toString());
		}
	}

}



