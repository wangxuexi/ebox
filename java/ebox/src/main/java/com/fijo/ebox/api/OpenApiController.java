package com.fijo.ebox.api;

import com.alibaba.fastjson.JSONObject;
import com.fijo.ebox.base.client.BasePlatClient;
import com.fijo.ebox.base.client.EfastClient;
import com.fijo.ebox.base.config.ApprovalConfig;
import com.fijo.ebox.base.util.common.StringUtil;
import com.fijo.ebox.base.util.plat.HttpClientUtil;
import com.fijo.ebox.base.util.plat.JsonUtil;
import com.fijo.ebox.config.WxapiConfig;
import com.fijo.ebox.dto.ResultDto;
import com.fijo.ebox.enums.ModularEnum.NtFileTypeEnum;
import com.fijo.ebox.enums.SysEnum.ResultEnum;

import com.fijo.ebox.model.FileUploadNew;
import com.fijo.ebox.modular.sy.pojo.ECSY0007;
import com.fijo.ebox.modular.sy.pojo.ECSY0008;
import com.fijo.ebox.modular.sy.pojo.SysApproveLog;
import com.fijo.ebox.modular.sy.service.ECSY0007Service;
import com.fijo.ebox.modular.sy.service.ECSY0008Service;
import com.fijo.ebox.modular.sy.service.SysApproveLogService;

import com.fijo.ebox.util.UploadFileUtil;
import com.fijo.ebox.util.WxApiUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.fijo.ebox.dto.ResultDto.SUCCESS;

@Slf4j
@RestController
@RequestMapping("/openApi")
public class OpenApiController {
    @Autowired
    private BasePlatClient basePlatClient;
    @Autowired
    private EfastClient efastClient;

    @Autowired
    private SysApproveLogService sysApproveLogService;

    @Autowired
    private ECSY0008Service ecsy0008Service;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private RedisTemplate redisTemplate;

    //Redis的nameSpace
    private static final String nameSpace = "fijo:approve:";

    @Autowired
    private ApprovalConfig approvalConfig;

    @Autowired
    private UploadFileUtil uploadFileUtil;

    @Autowired
    private WxApiUtil wxApiUtil;

    @Autowired
    private WxapiConfig wxapiConfig;

    @Autowired
    private ECSY0007Service ecsy0007Service;

    /**
     * 查询审批记录
     *
     * @param id             单据ID
     * @param entityTypeCode 实体类编码
     * @return
     */
    @PostMapping(value = "/queryApproveLog")
    public String queryApproveLog(Long id, String entityTypeCode) {
        try {
            SysApproveLog sysApproveLog = new SysApproveLog();
            sysApproveLog.setTypeCode(entityTypeCode);
            sysApproveLog.setObjectId(id);
            sysApproveLog.setOrderByClause("createTime desc");
            List<SysApproveLog> result = sysApproveLogService.queryList(sysApproveLog);
            return ResultDto.SUCCESS(result);
        } catch (Exception e) {
            log.error("【查询审批记录】 /queryApproveLog, {}", e.toString());
            return ResultDto.ERROR("查询审批记录失败!" , e.toString());
        }
    }

    /**
     * PC查询待我审批所有单据
     * @param tenant    租户
     * @param orgCode 组织编码
     * @param uId     用户ID
     * @param limitSize    分页条数
     * @param isRefresh   是否强制刷新
     * @return
     */
    @PostMapping(value = "/queryAllPendingApprovalGroup")
    public String queryAllPendingApprovalGroup(String tenant, String orgCode, String uId, Integer limitSize, Boolean isRefresh) {
        //查询结果
        String result = null;
        //校验是否强制刷新
        if(isRefresh != null && isRefresh == true){
            //强制刷新则直接调平台接口
            result = basePlatClient.queryAllPendingApprovalGroup(tenant, orgCode, uId, limitSize);
            //将查询的结果存入Redis
            //设置key
            String key = nameSpace  + tenant + ":" + uId;
            //序列化后存入Redis
            RedisSerializer stringSerializer = new StringRedisSerializer();
            redisTemplate.setKeySerializer(stringSerializer);
            redisTemplate.opsForValue().set(key,result,approvalConfig.getExpirationTime(), TimeUnit.MILLISECONDS);
        }else {
            //否则取缓存的数据返回
            RedisSerializer stringSerializer = new StringRedisSerializer();
            redisTemplate.setKeySerializer(stringSerializer);
            Object value = redisTemplate.opsForValue().get(nameSpace  + tenant + ":" + uId);
            //校验缓存是否有
            if (value != null){
                result = value.toString();
            }else {
                //否则调平台接口查询
                result = basePlatClient.queryAllPendingApprovalGroup(tenant, orgCode, uId, limitSize);
                //将查询的结果存入Redis
                //设置key
                String key = nameSpace  + tenant + ":" + uId;
                //存入Redis
                redisTemplate.opsForValue().set(key,result,approvalConfig.getExpirationTime(), TimeUnit.MILLISECONDS);
            }
        }
        return result;
    }


    /**
     * 查询上级组织节点组织编码
     *
     * @param tenant  租户
     * @param orgCode 组织编码
     * @return 上级组织编码
     */
    @PostMapping(value = "/queryApproveStateForShow")
    public String queryApproveStateForShow(Long approveTypeId, String tenant, String orgCode) {
        String retData = basePlatClient.queryApproveStateForShow(approveTypeId, tenant, orgCode);
        return retData;
    }

    /**
     * 根据当前审批状态，获取后置触发事件编码、名称
     *
     * @param approveTypeId 审批类型id
     * @param orgCode       组织编码
     * @param stateCode     单据状态
     * @return
     */
    @PostMapping(value = "/queryApproveStateJumpEventByState")
    public String queryApproveStateJumpEventByState(String approveTypeId, String tenant, String orgCode, String stateCode) {
        String retData = basePlatClient.queryApproveStateJumpEventByState(approveTypeId, tenant, orgCode, stateCode);
        return retData;
    }



    /**
     * 查询审批类型id
     *
     * @param tenant         租户编码
     * @param entityTypeCode 实体类型编码
     * @param dbTableName    数据库表名
     * @return
     */
    @PostMapping(value = "/queryApproveTypeId")
    public String queryApproveTypeId(String tenant, String entityTypeCode, String dbTableName) {
        String retData = basePlatClient.queryApproveTypeId(tenant, entityTypeCode, dbTableName);
        return retData;
    }

    /**
     * 查询表头配置
     *
     * @param tenant          租户编码
     * @param applicationCode 应用编码
     * @param webPageCode     页面编号
     * @param tableDomId      表格domId
     * @param orgCode         组织编码
     * @return
     */
    @PostMapping("queryTableColSettings")
    public String queryTableColSettings(String tenant, String applicationCode, String webPageCode, String tableDomId, String orgCode) {
        return basePlatClient.queryTableColSettings(tenant, applicationCode, webPageCode, tableDomId, orgCode);
    }


    /**
     * 查询页面注册的所有图表配置
     *
     * @param tenant
     * @param applicationCode
     * @param webPageCode
     * @return
     */
    @PostMapping("queryWebChartRegistConfig")
    public String queryWebChartRegistConfig(String tenant, String applicationCode, String webPageCode) {
        return efastClient.queryWebChartRegistConfig(tenant, applicationCode, webPageCode);
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
     * 查询预警项下拉框数据
     *
     * @param tenant  租户编码
     * @param orgCode 一级组织编码
     * @return
     */
    @PostMapping(value = "/queryEarlyWarningType")
    public String queryEarlyWarningType(String tenant, String orgCode) {
        try {
            String resultData = basePlatClient.queryEarlyWarningType(tenant, orgCode);
            ResultDto resultDto = JSONObject.parseObject(resultData, ResultDto.class);
            List<Map> resultMapList = (List<Map>) resultDto.getData();
            log.info("【预警消息】 查询预警项下拉框数据成功!");
            return ResultDto.SUCCESS("【预警消息】查询预警项下拉框数据成功！", resultMapList);
        } catch (Exception e) {
            log.error("【预警消息】 查询预警项下拉框数据失败，失败原因：{}", e);
            return ResultDto.ERROR("【预警消息】查询预警项下拉框数据失败！", e.toString());
        }
    }

    /**
     * 查询预警项档位下拉框数据
     *
     * @param earlyWarningId 预警项id
     * @return
     */
    @PostMapping(value = "/queryEarlyWarningGear")
    public String queryEarlyWarningGear(Long earlyWarningId) {
        try {
            String resultData = basePlatClient.queryEarlyWarningGear(earlyWarningId);
            ResultDto resultDto = JSONObject.parseObject(resultData, ResultDto.class);
            List<Map> resultMapList = (List<Map>) resultDto.getData();
            log.info("【预警消息】 查询预警项档位下拉框数据");
            return ResultDto.SUCCESS("【预警消息】查询预警项档位下拉框数据", resultMapList);
        } catch (Exception e) {
            log.error("【预警消息】 查询预警项档位下拉框数据，失败原因：{}", e);
            return ResultDto.ERROR("【预警消息】查询预警项档位下拉框数据", e.toString());
        }
    }

    /**
     * 分页查询预警消息
     *
     * @param tenant         租户
     * @param orgCode        数据权限内组织编码
     * @param entityTypeCode 实体类型编码
     * @param chargUserId    处理人id
     * @param earlyWarningId 预警项id
     * @param gearValue      档位值
     * @param warningInfo    预警信息关键字
     * @param state          消息状态
     * @param isUserOffLamp  是否人工消灯
     * @param pageNo         页号
     * @param pageSize       页长
     * @return
     */
    @PostMapping(value = "/queryEarlyWarningMsg")
    public String queryEarlyWarningMsg(String tenant, String orgCode, String entityTypeCode, Long chargUserId, Long earlyWarningId, Integer gearValue, String warningInfo, Integer state, Integer isUserOffLamp, Integer pageNo, Integer pageSize) {
        try {
            String resultData = basePlatClient.queryEarlyWarningMsg(tenant, orgCode, entityTypeCode, chargUserId, earlyWarningId, gearValue, warningInfo, state, isUserOffLamp, pageNo, pageSize);
            ResultDto resultDto = JSONObject.parseObject(resultData, ResultDto.class);
            Map resultMap = (Map) resultDto.getData();
            log.info("【预警消息】 查询预警消息");
            return ResultDto.SUCCESS("【预警消息】查询预警消息", resultMap);
        } catch (Exception e) {
            log.error("【预警消息】 查询预警消息，失败原因：{}", e);
            return ResultDto.ERROR("【预警消息】查询预警消息", e.toString());
        }
    }

    /**
     * 更新预警信息状态
     *
     * @param id
     * @param state
     * @param updateUserName
     * @param updateUserId
     * @return
     */
    @PostMapping(value = "/updateEarlyWarningState")
    public String updateEarlyWarningState(Long id, Integer state, String updateUserName, Long updateUserId) {
        try {
            String resultData = basePlatClient.updateEarlyWarningState(id, state, updateUserName, updateUserId);
            ResultDto resultDto = JSONObject.parseObject(resultData, ResultDto.class);
            if (Integer.parseInt(resultDto.getCode()) == ResultEnum.RESULT_CODE_SUCCESS.getCode()) {
                return ResultDto.SUCCESS("【预警消息】更新预警信息状态成功！");
            } else {
                return ResultDto.ERROR("【预警消息】更新预警信息状态失败,原因：", resultDto.getMsg());
            }
        } catch (Exception e) {
            log.error("【预警消息】 更新预警信息状态失败，失败原因：{}", e);
            return ResultDto.ERROR("【预警消息】更新预警信息状态失败", e.toString());
        }
    }


    /**
     * 跟据用户组编码查询用户组人员
     *
     * @param tenant
     * @param orgCode
     * @param groupCode
     * @return
     */
    @PostMapping(value = "/queryUserInfoByGroupCode")
    public String queryUserInfoByGroupCode(String tenant, String orgCode, String groupCode) {
        try {
            String resultData = basePlatClient.queryUserInfoByGroupCode(tenant, orgCode, groupCode);
            ResultDto resultDto = JsonUtil.json2Bean(resultData, ResultDto.class);
            log.info("【查询用户组】 查询成功!");
            return ResultDto.SUCCESS("【查询用户组】 查询成功!", resultDto);
        } catch (Exception e) {
            log.error("【查询用户组】 查询失败，失败原因：{}", e);
            return ResultDto.ERROR("查询失败", e.toString());
        }
    }



    /**
     * 菜单点击记录
     * @param tenant
     * @param applicationCode
     * @param userId
     * @param loginName
     * @param menuId
     * @param menuName
     * @return
     */
    @PostMapping(value = "/menuClick")
    public String menuClick(String tenant,String applicationCode,Long userId,String loginName,Long menuId,String menuName) {
        try {
            basePlatClient.menuClick(tenant,applicationCode,userId,loginName,menuId,menuName);
            log.info("【菜单点击计数】 计数成功!");
            return ResultDto.SUCCESS("计数成功！");
        } catch (Exception e) {
            log.error("【菜单点击计数】 计数失败，失败原因：{}", e.getMessage());
            return ResultDto.ERROR("【菜单点击计数】计数失败！", e.toString());
        }
    }

    /**
     * ueditor附件本地化
     * @param appId     应用ID
     * @param secret    密钥
     * @param tenant    租户
     * @param orgCode   组织编码
     * @param action    操作类型
     * @param request
     * @return
     */
    @PostMapping(value = "/ueditorFileLocalization")
    public String ueditorFileLocalization(String appId, String secret, String tenant, String orgCode, String action, HttpServletRequest request){
        try {
            //获取accessToken
            String restleStr1 = wxApiUtil.getAccessToken(appId, secret);
            ResultDto restleDto1 = JsonUtil.json2Bean(restleStr1, ResultDto.class);
            Map restleMap1 = JsonUtil.json2Bean(restleDto1.getData().toString(), Map.class);
            //返回参数
            String  resultJson = null;
            //校验舒服获取到access_token
            if(restleMap1.get("access_token") != null){
                String accessToken = restleMap1.get("access_token").toString();
                //附件数据
                List<MultipartFile> fileDataList = new ArrayList<>();
                if(action.equals("config")){
                    //当 action == "config" 时，将 config.json 的内容输出给客户端。
                    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                    fileDataList = multipartRequest.getFiles("fileData");
                    //上传到本地服务器
                    for (MultipartFile fileData : fileDataList){
                        //开始处理数据
                        FileUploadNew fileUpload = new FileUploadNew();
                        fileUpload.setTenant(tenant);
                        fileUpload.setOrgCode(orgCode);
                        try {
                            //拿到本地化的附件
                            FileUploadNew fileUploadNew = uploadFileUtil.resultUploadNew(fileData, fileUpload);
                            //图片上传到微信
                            File oldFileData = wxApiUtil.obtainFileByPath(fileUploadNew.getFilePath());
                            InputStream inputStream = new FileInputStream(oldFileData);
                            MultipartFile multipartFile =
                                    new MockMultipartFile(oldFileData.getName(), oldFileData.getName(), null, inputStream);
                            inputStream.close();
                            //调用接口上传
                            String restleStr2 = wxApiUtil.getInsertImg(accessToken, multipartFile, NtFileTypeEnum.NT_FILE_TYPE_IMG.getCode());
                            ResultDto restleDto2 = JsonUtil.json2Bean(restleStr2, ResultDto.class);
                            Map restleMap2 = JsonUtil.json2Bean(restleDto2.getData().toString(), Map.class);
                            String fileUrl = null;
                            if(restleMap2.get("url") != null){
                                fileUrl = restleMap2.get("url").toString();
                            }
                            //如果获取到了有效的imageUrl
                            if (fileUrl != null && fileUrl.substring(0, 4).equals("http")) {
                                resultJson = "{\"state\":\"SUCCESS\", \"url\":\"%s\", \"title\":\"%s\", \"original\":\"%s\"}";
                                resultJson = String.format(resultJson, fileUrl, fileUploadNew.getFileNewName(), fileUploadNew.getFileNewName());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.error("文件上传失败！");
                        }
                    }
                }else if(action.equals("uploadimage")){
                    //当 action == "uploadimage" 时，就是上传文件，将上传的文件用 context.Request.Files[0] 取出来，保存，记录到数据库
                    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                    fileDataList = multipartRequest.getFiles("fileData");
                    //上传到本地服务器
                    for (MultipartFile fileData : fileDataList){
                        //开始处理数据
                        FileUploadNew fileUpload = new FileUploadNew();
                        fileUpload.setTenant(tenant);
                        fileUpload.setOrgCode(orgCode);
                        try {
                            //拿到本地化的附件
                            FileUploadNew fileUploadNew = uploadFileUtil.resultUploadNew(fileData, fileUpload);
                            //图片上传到微信
                            File oldFileData = wxApiUtil.obtainFileByPath(fileUploadNew.getFilePath());
                            InputStream inputStream = new FileInputStream(oldFileData);
                            MultipartFile multipartFile =
                                    new MockMultipartFile(oldFileData.getName(), oldFileData.getName(), null, inputStream);
                            inputStream.close();
                            //调用接口上传
                            String restleStr2 = wxApiUtil.getInsertImg(accessToken, multipartFile, NtFileTypeEnum.NT_FILE_TYPE_IMG.getCode());
                            ResultDto restleDto2 = JsonUtil.json2Bean(restleStr2, ResultDto.class);
                            Map restleMap2 = JsonUtil.json2Bean(restleDto2.getData().toString(), Map.class);
                            String fileUrl = null;
                            if(restleMap2.get("url") != null){
                                fileUrl = restleMap2.get("url").toString();
                            }
                            //如果获取到了有效的imageUrl
                            if (fileUrl != null && fileUrl.substring(0, 4).equals("http")) {
                                resultJson = "{\"state\":\"SUCCESS\", \"url\":\"%s\", \"title\":\"%s\", \"original\":\"%s\"}";
                                resultJson = String.format(resultJson, fileUrl, fileUploadNew.getFileNewName(), fileUploadNew.getFileNewName());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.error("文件上传失败！");
                        }
                    }
                }else if(action.equals("catchimage")){
                    //当 action == ”catchimage“时, 就是远程抓取图片转本地
                    String[] fileDataArr = request.getParameterValues("fileData[]");
                    //本地附件路径
                    List urlList = new ArrayList();
                    if(null != fileDataArr && fileDataArr.length > 0){
                        for (String fileUrl : fileDataArr){
                            File fileData = wxApiUtil.obtainFileByPath(fileUrl);
                            InputStream inputStream = new FileInputStream(fileData);
                            MultipartFile multipartFile =
                                    new MockMultipartFile(fileData.getName(), fileData.getName(), null, inputStream);
                            inputStream.close();
                            //开始处理数据
                            FileUploadNew fileUpload = new FileUploadNew();
                            fileUpload.setTenant(tenant);
                            fileUpload.setOrgCode(orgCode);
                            //拿到本地化的附件
                            FileUploadNew fileUploadNew = uploadFileUtil.resultUploadNew(multipartFile, fileUpload);
                            //图片上传到微信
                            File oldFileData = wxApiUtil.obtainFileByPath(fileUploadNew.getFilePath());
                            InputStream inputStreamData = new FileInputStream(oldFileData);
                            MultipartFile multipartFileData =
                                    new MockMultipartFile(oldFileData.getName(), oldFileData.getName(), null, inputStreamData);
                            inputStreamData.close();
                            //调用接口上传
                            String restleStr2 = wxApiUtil.getInsertImg(accessToken, multipartFile, NtFileTypeEnum.NT_FILE_TYPE_IMG.getCode());
                            ResultDto restleDto2 = JsonUtil.json2Bean(restleStr2, ResultDto.class);
                            Map restleMap2 = JsonUtil.json2Bean(restleDto2.getData().toString(), Map.class);
                            String fileUrlData = null;
                            if(restleMap2.get("url") != null){
                                fileUrlData = restleMap2.get("url").toString();
                            }
                            //处理返回map
                            Map urlMap = new HashMap();
                            urlMap.put("url",fileUrlData);
                            urlMap.put("source",fileUrl);
                            urlMap.put("state","SUCCESS");
                            urlList.add(urlMap);
                        }
                        //返回数据
                        Map jsonMap = new HashMap();
                        jsonMap.put("state","SUCCESS");
                        jsonMap.put("list",urlList);
                        JSONObject jsonObject = new JSONObject();
                        Iterator<Map.Entry<String, String>> entries = jsonMap.entrySet().iterator();
                        while (entries.hasNext()) {
                            Map.Entry<String, String> entry = entries.next();
                            jsonObject.put(entry.getKey(),entry.getValue());
                        }
                        resultJson = jsonObject.toString();
                    }
                }
            }
            return resultJson;
        }catch (Exception e){
            log.info("【fileUplodTo】/文件上传, {}", e.getMessage());
            return ResultDto.ERROR("文件上传失败");
        }
    }

    /**
     * 获取 accessToken
     * @param appId
     * @param secret
     * @return
     */
    @PostMapping(value = "/getAccessToken")
    public String getAccessToken(String appId, String secret){
        try {
            //处理请求路径
            String url = wxapiConfig.getGetAccessToken() + "&appid=" + appId + "&secret=" + secret;
            //请求接口
            String restleStr = HttpClientUtil.doGet(url);
            Map restleMap = new HashMap();
            if(restleStr != null){
                restleMap = JsonUtil.json2Bean(restleStr,Map.class);
            }
            return ResultDto.SUCCESS("获取成功！",restleMap);
        }catch (Exception e){
            e.printStackTrace();
            return ResultDto.ERROR("获取失败！",e.toString());
        }
    }

    /**
     * 查询用户当前页面的实体数据权限
     * @param tenant        租户编码
     * @param applicationCode   应用编码
     * @param webpageCode       页面号
     * @param userId        用户id
     * @return
     */
    @PostMapping(value = "/queryOrgTreeByUserIdAndAuthorEntity")
    public String queryOrgTreeByUserIdAndAuthorEntity(String tenant,String applicationCode,String webpageCode,String userId){
        try {
            return basePlatClient.queryOrgTreeByUserIdAndAuthorEntity(tenant, applicationCode, webpageCode, userId);
        }catch (Exception e){
            log.error("【查询用户当前页面的实体数据权限】查询失败！原因：",e.toString());
            return ResultDto.ERROR("【查询用户当前页面的实体数据权限】查询失败！原因：",e.toString());
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
