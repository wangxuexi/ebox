package com.fijo.ebox.controller;

import com.fijo.ebox.base.client.BasePlatClient;
import com.fijo.ebox.base.pojo.FJSYSCT0004;
import com.fijo.ebox.dto.ResultDto;
import com.fijo.ebox.base.util.plat.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class BasePlatController {
    @Autowired
    private BasePlatClient basePlatClient;
    @Autowired
    HttpServletRequest request;


    /**
     * 查询版本
     *
     * @param tenant          租户编码
     * @param applicationCode 应用编码
     * @return
     */
    @PostMapping(value = "/queryVersion")
    String queryVersion(String tenant, String applicationCode) {
        String result = basePlatClient.queryVersion(tenant, applicationCode);
        return result;
    }

    /**
     * 根据租户编码和角色编码，获取权限菜单
     *
     * @param tenant      租户编码
     * @param authorityId 权限组id
     * @return
     */
    @PostMapping(value = "/queryAuthorityMenu")
    public String queryAuthorityMenu(String tenant, String applicationCode, String authorityId) {
        String result = basePlatClient.queryAuthorityMenu(tenant, applicationCode, authorityId);
        return result;
    }

    /**
     * 查询用户id查询数据权限
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "/queryOrgTreeByUserId")
    public String queryOrgTreeByUserId(Long userId) {
        String resultList = basePlatClient.queryOrgTreeByUserId(userId);
        return resultList;
    }

    /**
     * 根据租户查询组织
     *
     * @param tenant
     * @return
     */
    @PostMapping(value = "/queryAllEffectiveOrg")
    public String queryAllEffectiveOrg(String tenant) {
        return basePlatClient.queryAllEffectiveOrg(tenant);
    }

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/queryUserInfoById")
    public String queryUserInfoById(String id) {
        return basePlatClient.queryUserInfoById(id);
    }

    /**
     * 记录菜单点击
     *
     * @param tenant    租户编码
     * @param loginUser 操作人姓名
     * @param userId    操作人账号
     * @param menuName  菜单名
     * @return
     */
    @PostMapping(value = "/collectMenuClick")
    public String collectMenuClick(String tenant, String loginUser, Integer userId, String menuName) {

        String result = basePlatClient.collectMenuClick(tenant, loginUser, userId, menuName);
        log.info("【新增操作记录】操作结果：{}", result);
        return result;
    }

    /**
     * 查询自定义字段集
     *
     * @param tenant
     * @param orgCode        组织编码,只能传一个(镇级)
     * @param webCode        页面号
     * @param entityTypeCode 实体类型编码
     * @return
     */
    @PostMapping(value = "/queryByTeAndOrgAndWebAndEntity")
    public String queyByTeAndOrgAndWebAndEntity(String tenant, String orgCode, String webCode, String entityTypeCode, String objectId) {
        String result = basePlatClient.queyByTeAndOrgAndWebAndEntity(tenant, orgCode, webCode, entityTypeCode, objectId);
        return result;
    }


    /**
     * 保存自定义字段值
     *
     * @param fjsysct0004
     * @return
     */
    @PostMapping(value = "/saveCustomFieldValue")
    public String saveCustomFieldValue(FJSYSCT0004 fjsysct0004) {
        String result = null;
        try {
            result = basePlatClient
                    .saveCustomFieldValue(
                            String.valueOf(fjsysct0004.getId()),
                            fjsysct0004.getTenant(),
                            fjsysct0004.getOrgCode(),
                            fjsysct0004.getEntyTypeId(),
                            fjsysct0004.getCustomFieldId(),
                            String.valueOf(fjsysct0004.getObjectId()),
                            String.valueOf(fjsysct0004.getFieldSeq()),
                            fjsysct0004.getValue(),
                            fjsysct0004.getCustomFieldName()
                    );
            return result;
        } catch (Exception e) {
            return ResultDto.ERROR("保存失败", e.getMessage());
        }
    }

    /**
     * 根据租户编码和模块编码查询组合查询相关配置信息
     *
     * @param tenant    租户编码
     * @param modelCode 模块编码
     * @return
     */
    @PostMapping(value = "/queryCQConfigByTenantAndModelCode")
    public String queryCQConfigByTenantAndModelCode(String tenant, String modelCode) {
        String result = basePlatClient.queryCQConfigByTenantAndModelCode(tenant, modelCode);
        ResultDto resultDto = JsonUtil.json2Bean(result, ResultDto.class);
        return ResultDto.SUCCESS(resultDto.getData());
    }

    /**
     * 构建企业来源数据结构
     *
     * @param orgCode 组织编码
     * @param tenant  租户编码
     * @return
     */
    // TODO
    @PostMapping(value = "/queryAllUserTreeByOrg")
    public String queryAllUserTreeByOrg(String orgCode, String tenant) {
        return basePlatClient.queryAllUserTreeByOrg(tenant, orgCode);
    }

    /**
     * 查询组织用户树形结构
     *
     * @param tenant
     * @param orgCode
     * @return
     */
    @PostMapping(value = "/queryUserByTenantAndOrgCode")
    public String queryUserByTenantAndOrgCode(String tenant, String orgCode) {
        return basePlatClient.queryUserByTenantAndOrgCode(tenant, orgCode);
    }

    /**
     * 查询权限内待处理页面资源详情
     *
     * @param tenant          租户编码
     * @param applicationCode 应用编码
     * @param webPageCode     页面号
     * @param authorityId     权限组id
     * @return
     */
    @PostMapping(value = "/openApi/queryAuthorityWebResourceInfo")
    public String queryAuthorityWebResourceInfo(String tenant, String applicationCode, String webPageCode, String authorityId) {
        return basePlatClient.queryAuthorityWebResourceInfo(tenant, applicationCode, webPageCode, authorityId);
    }

    /**
     * 通过租户和权限组id查询查询菜单
     *
     * @param tenant          租户
     * @param applicationCode 应用编码
     * @param authorityId     角色id
     * @return
     */
    @PostMapping(value = "/queryAuthorityMenuInfo")
    public String queryAuthorityMenuInfo(String tenant, String applicationCode, String authorityId) {
        String result = basePlatClient.queryAuthorityMenuInfo(tenant, applicationCode, authorityId);
        return result;
    }

    /**
     * 查询组织树
     *
     * @param tenant 租户编码
     * @return
     */
    @PostMapping(value = "/queryByTenant")
    public String queryByTenant(String tenant) {
        String result = basePlatClient.queryByTenant(tenant);
        return result;
    }

    @PostMapping(value = "/queryAllEffectiveChildOrg")
    public String queryAllEffectiveChildOrg(String tenant, String orgCode) {
        String result = basePlatClient.queryAllEffectiveChildOrg(tenant, orgCode);
        return result;
    }

    /**
     * 审批后置事件执行
     *
     * @param secretKey 秘钥
     * @param params    参数，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @return
     */
    @PostMapping(value = "/approveStateJumpDoPostEvent")
    public String approveStateJumpDoPostEvent(String secretKey, String params) {
        String result = basePlatClient.approveStateJumpDoPostEvent(secretKey, params);
        return result;
    }

    /**
     * 查询组织权限内的树形结构用户（优先使用此接口）
     *
     * @param tenant
     * @param orgCode
     * @return
     */
    @PostMapping(value = "/queryAllUserByTenantAndOrgCode")
    public String queryAllUserByTenantAndOrgCode(String tenant, String orgCode) {
        String result = basePlatClient.queryAllUserByTenantAndOrgCode(tenant, orgCode);
        return result;
    }

    /**
     * 指派人
     *
     * @param tenant         租户编码
     * @param entityTypeCode 实体类型编码
     * @param orgCode        组织编码
     * @param eventCode      触发事件编码
     * @param uId            用户id
     * @param pboId          单据id
     * @param stateCode      目标状态状态
     * @return
     */
    @PostMapping(value = "/approveAppointUser")
    public String approveAppointUser(String tenant, String entityTypeCode, String orgCode, String eventCode, String uId, String pboId, String stateCode) {
        String result = basePlatClient.approveAppointUser(tenant, entityTypeCode, orgCode, eventCode, uId, pboId, stateCode);
        return result;
    }

    /**
     * 指派人处理完成后清空指派关系
     *
     * @param tenant         租户编码
     * @param entityTypeCode 实体类型编码
     * @param orgCode        组织编码
     * @param eventCode      触发事件编码
     * @param uId            用户id
     * @param pboId          单据id
     * @param stateCode      目标状态状态
     * @return
     */
    @PostMapping(value = "/clearApproveAppoint")
    public String clearApproveAppoint(String tenant, String entityTypeCode, String orgCode, String eventCode, Long uId, String pboId, String stateCode) {
        String result = basePlatClient.clearApproveAppoint(tenant, entityTypeCode, orgCode, eventCode, uId, pboId, stateCode);
        return result;
    }

    @PostMapping(value = "/queryApproveAuthority")
    public String queryApproveAuthority(String tenant, String entityTypeCode, String dbTableName, Long uId, String orgCode, String headerTableReName, String states) {
        String result = basePlatClient.queryApproveAuthority(tenant, entityTypeCode, dbTableName, uId, orgCode, headerTableReName, states);
        return result;
    }

    /**
     * 根据业务id查询该实体的预警消息
     *
     * @param tenant         租户编码
     * @param orgCode        组织编码
     * @param entityTypeCode 实体类型编码
     * @param businessId     业务ID
     */
    @PostMapping(value = "/queryEarlyWarningMsgByBusinessId")
    public String queryEarlyWarningMsgByBusinessId(String tenant, String orgCode, String entityTypeCode, String businessId) {
        return basePlatClient.queryEarlyWarningMsgByBusinessId(tenant, orgCode, entityTypeCode, businessId);
    }

    /**
     * 查询用户基本信息
     *
     * @param id 用户id
     */
    @PostMapping(value = "/queryUserBaseInfo")
    public String queryUserBaseInfo(String id) {
        return basePlatClient.queryUserBaseInfo(id);
    }
    /**
     * 更新平台用户基本信息
     * @param id 用户id
     * @param orgDes 组织描述
     * @param nickName 昵称
     * @param headImgUrl 头像地址
     * @param phone 手机号码
     * @param state 用户状态
     * @param expirationTime 过期时间
     * @return
     */
    @PostMapping(value = "/updatePlatUserBaseInfo")
    public String updatePlatUserBaseInfo(String id,String orgDes,String loginName ,String nickName ,
                                         String headImgUrl,String phone,String mail,String state,String expirationTime){
            return  basePlatClient.updatePlatUserBaseInfo(id,orgDes,loginName,nickName,headImgUrl,phone,mail,state,expirationTime);
    }
}
