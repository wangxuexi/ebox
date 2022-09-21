package com.fijo.ebox.base.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "fijo-baseplat")//value=注册在eureka上的服务名
public interface BasePlatClient {

    /**
     * 查询版本
     *
     * @param tenant          租户编码
     * @param applicationCode 应用编码
     * @return
     */
    @PostMapping(value = "/openApi/queryVersion")
    String queryVersion(@RequestParam("tenant") String tenant,
                        @RequestParam("applicationCode") String applicationCode);


    /**
     * 根据组织编码查询组织名称
     *
     * @param tenant  租户编码
     * @param orgCode 组织编码
     * @return
     */
    @PostMapping(value = "/openApi/queryOrgNameByOrgCode")
    String queryOrgNameByOrgCode(@RequestParam("tenant") String tenant,
                                 @RequestParam("orgCode") String orgCode);

    /**
     * 根据租户编码和角色编码，获取权限菜单
     *
     * @param tenant      租户编码
     * @param authorityId 权限组id
     * @return
     */
    @PostMapping(value = "/openApi/queryAuthorityMenu")
    String queryAuthorityMenu(@RequestParam("tenant") String tenant,
                              @RequestParam("applicationCode") String applicationCode,
                              @RequestParam("authorityId") String authorityId
    );

    /**
     * 根据租户编码和角色编码，获取权限菜单详情
     *
     * @param tenant      租户编码
     * @param authorityId 权限组id
     * @return
     */
    @PostMapping(value = "/openApi/queryAuthorityMenuInfo")
    String queryAuthorityMenuInfo(@RequestParam("tenant") String tenant,
                                  @RequestParam("applicationCode") String applicationCode,
                                  @RequestParam("authorityId") String authorityId
    );

    /**
     * 查询租户下有效组织
     *
     * @param tenant
     * @return
     */
    @PostMapping(value = "/openApi/queryAllEffectiveOrg")
    String queryAllEffectiveOrg(@RequestParam("tenant") String tenant);

    /**
     * 根据用户id查询该用户的数据权限集
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "/openApi/queryOrgCodeByUserId")
    String queryOrgCodeByUserId(@RequestParam("userId") Long userId);


    /**
     * 根据用户id获取人员信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/openApi/queryUserInfoByUId", method = RequestMethod.POST, consumes = "application/json")
    String queryUserInfoById(@RequestParam("id") String id);

    /**
     * 查询用户数据权限
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "/openApi/queryOrgTreeByUserId")
    String queryOrgTreeByUserId(@RequestParam("userId") Long userId);

    /**
     * 查询自定义字段集
     *
     * @param tenant
     * @param orgCode        组织编码
     * @param webCode        页面号
     * @param entityTypeCode 实体类型编码
     * @return
     */
    @PostMapping(value = "/openApi/queryCTList")
    String queyByTeAndOrgAndWebAndEntity(@RequestParam("tenant") String tenant,
                                         @RequestParam("orgCode") String orgCode,
                                         @RequestParam("webCode") String webCode,
                                         @RequestParam("entityTypeCode") String entityTypeCode,
                                         @RequestParam("objectId") String objectId
    );

    /**
     * 保存自定义字段值
     *
     * @param id            id
     * @param tenant        租户编码
     * @param orgCode       组织编码
     * @param customFieldId 自定义字段id
     * @param objectId      实体ID
     * @param fieldSeq      顺序号
     * @param value
     * @return
     */

    @PostMapping(value = "/openApi/saveCustomFieldValue")
    String saveCustomFieldValue(@RequestParam("id") String id,
                                @RequestParam("tenant") String tenant,
                                @RequestParam("orgCode") String orgCode,
                                @RequestParam("entyTypeId") Long entyTypeId,
                                @RequestParam("customFieldId") Long customFieldId,
                                @RequestParam("objectId") String objectId,
                                @RequestParam("fieldSeq") String fieldSeq,
                                @RequestParam("value") String value,
                                @RequestParam("customFieldName") String customFieldName
    );

    /**
     * 根据租户编码和模块编码查询组合查询相关配置信息
     *
     * @param tenant    租户编码
     * @param modelCode 模块编码
     * @return
     */
    @PostMapping(value = "/openApi/queryAllCqSettins")
    String queryCQConfigByTenantAndModelCode(@RequestParam("tenant") String tenant,
                                             @RequestParam("modelCode") String modelCode);

    /**
     * 查询组织用户树形结构
     *
     * @param tenant
     * @param orgCode
     * @return
     */
    @PostMapping(value = "/openApi/queryAllUserTreeByOrg")
    String queryAllUserTreeByOrg(@RequestParam("tenant") String tenant,
                                 @RequestParam("orgCode") String orgCode);

    /**
     * 组合查询查询实体id
     *
     * @param limitSql 限制条件sql字符串成
     * @return
     */

    @PostMapping(value = "/openApi/queryObjectIdByCompositeQuery")
    String queryObjectIdByCompositeQuery(@RequestParam("limitSql") String limitSql);

    /**
     * 判断是否需要走审批流
     *
     * @param tenant    租户编码
     * @param orgCode   组织编码
     * @param eventCode 事件编码
     * @return
     */
    @PostMapping(value = "/openApi/isDoApprove")
    public Boolean isDoApprove(@RequestParam("tenant") String tenant,
                               @RequestParam("orgCode") String orgCode,
                               @RequestParam("eventCode") String eventCode);

    /**
     * 审批状态跃迁(废弃)
     *
     * @param tenant         租户编码
     * @param entityTypeCode 实体类型编码
     * @param orgCode        组织编码
     * @param eventCode      触发事件编码
     * @param uId            用户id
     * @param pboId          单据id
     * @param startStateCode 原状态
     * @return
     */
    /*@PostMapping(value = "/FJSYSAP0004/approveStateJump")
    String approveStateJump(@RequestParam("tenant") String tenant,
                            @RequestParam("entityTypeCode") String entityTypeCode,
                            @RequestParam("orgCode") String orgCode,
                            @RequestParam("eventCode") String eventCode,
                            @RequestParam("uId") String uId,
                            @RequestParam("pboId") String pboId,
                            @RequestParam("startStateCode") String startStateCode);*/

    /**
     * 审批状态跃迁
     *
     * @param tenant         租户编码
     * @param entityTypeCode 实体类型编码
     * @param orgCode        组织编码
     * @param eventCode      触发事件编码
     * @param uId            用户id
     * @param pboId          单据id
     * @param startStateCode 原状态
     * @return
     */
    @PostMapping(value = "/openApi/approveStateJumpGroup")
    String approveStateJumpGroup(@RequestParam("tenant") String tenant,
                                 @RequestParam("entityTypeCode") String entityTypeCode,
                                 @RequestParam("orgCode") String orgCode,
                                 @RequestParam("eventCode") String eventCode,
                                 @RequestParam("uId") String uId,
                                 @RequestParam("pboId") String pboId,
                                 @RequestParam("startStateCode") String startStateCode);

    /**
     * 查询待我审批所有单据(废弃)
     *
     * @return
     */
    /*@PostMapping(value = "/FJSYSAP0004/queryAllPendingApproval")
    String queryAllPendingApproval(@RequestParam("tenant") String tenant,
                                   @RequestParam("orgCode") String orgCode,
                                   @RequestParam("uId") String uId);*/

    /**
     * 查询待我审批所有单据
     *
     * @return
     */
    @PostMapping(value = "/openApi/queryAllPendingApprovalGroup")
    String queryAllPendingApprovalGroup(@RequestParam("tenant") String tenant,
                                        @RequestParam("orgCode") String orgCode,
                                        @RequestParam("uId") String uId,
                                        @RequestParam("limitSize") Integer limitSize
    );


    /**
     * 批量查询自定义字段值
     *
     * @param tenant         租户编码
     * @param orgCode        组织编码，如果多个，逗号分隔
     * @param entityTypeCode 实体类型编码
     * @param objectIds      实体对象编码串
     * @return
     */
    @PostMapping(value = "/openApi/batchQueryValue")
    String batchQueryValue(@RequestParam("tenant") String tenant,
                           @RequestParam("orgCode") String orgCode,
                           @RequestParam("entityTypeCode") String entityTypeCode,
                           @RequestParam("objectIds") String objectIds
    );


    /**
     * 查询组织权限内的树形结构用户
     *
     * @param tenant
     * @param orgCode 组织编码
     * @return
     */
    @PostMapping(value = "/openApi/queryAllUserByTenantAndOrgCode")
    String queryUserByTenantAndOrgCode(@RequestParam("tenant") String tenant,
                                       @RequestParam("orgCode") String orgCode);

    /**
     * 查询组织权限内的树形结构用户（优先使用此接口）
     *
     * @param tenant
     * @param orgCode 组织编码
     * @return
     */
    @PostMapping(value = "/openApi/queryAllUserByTenantAndOrgCode")
    String queryAllUserByTenantAndOrgCode(@RequestParam("tenant") String tenant,
                                          @RequestParam("orgCode") String orgCode);

    /**
     * 记录菜单点击
     *
     * @param tenant   租户编码
     * @param userName 操作人姓名
     * @param userId   操作人账号
     * @param menuName 菜单名
     * @return
     */
    @PostMapping(value = "/openApi/collectMenuClick")
    String collectMenuClick(@RequestParam("tenant") String tenant,
                            @RequestParam("userName") String userName,
                            @RequestParam("userId") Integer userId,
                            @RequestParam("menuName") String menuName);


    /**
     * 新增组织
     *
     * @param pId     //父id
     * @param tenant  //租户编码
     * @param name    //组织名称
     * @param orgCode //组织编码
     * @param level   //组织层级
     * @param sort    //排序
     * @param remark  //备注
     * @return
     */
    @PostMapping(value = "/openApi/insert")
    String insertOrg(@RequestParam("pId") Long pId,
                     @RequestParam("tenant") String tenant,
                     @RequestParam("name") String name,
                     @RequestParam("orgCode") String orgCode,
                     @RequestParam("level") Integer level,
                     @RequestParam("sort") Integer sort,
                     @RequestParam("remark") String remark);

    /**
     * 查询上级组织节点组织id
     *
     * @param tenant  租户
     * @param orgCode 组织编码
     * @return 上级组织编码
     */
    @PostMapping(value = "/openApi/queryUperOrgId")
    String queryUperOrgId(@RequestParam("tenant") String tenant, @RequestParam("orgCode") String orgCode);


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
    public String queryAuthorityWebResourceInfo(@RequestParam("tenant") String tenant,
                                                @RequestParam("applicationCode") String applicationCode,
                                                @RequestParam("webPageCode") String webPageCode,
                                                @RequestParam("authorityId") String authorityId);

    /**
     * 查询组织树
     *
     * @param tenant 租户编码
     * @return
     */
    @PostMapping(value = "/openApi/queryOrgTreeByTenant")
    String queryByTenant(@RequestParam("tenant") String tenant);

    /**
     * 查询上级组织节点组织编码
     *
     * @param tenant  租户
     * @param orgCode 组织编码
     * @return 上级组织编码
     */
    @PostMapping(value = "/openApi/queryUperOrgCode")
    String queryUperOrgCode(@RequestParam("tenant") String tenant, @RequestParam("orgCode") String orgCode);

    /**
     * 根据用户组编码查询用户详情
     *
     * @param tenant    租户编码
     * @param orgCode   组织编码
     * @param groupCode 用户组编码
     * @return
     */
    @PostMapping(value = "/openApi/queryUserInfoByGroupCode")
    String queryUserInfoByGroupCode(@RequestParam("tenant") String tenant, @RequestParam("orgCode") String orgCode, @RequestParam("groupCode") String groupCode);

    /**
     * 根据用户组编码查询用户详情
     *
     * @param tenant  租户编码
     * @param orgCode 组织编码
     * @return
     */
    @PostMapping(value = "/openApi/queryAllEffectiveChildOrg")
    String queryAllEffectiveChildOrg(@RequestParam("tenant") String tenant, @RequestParam("orgCode") String orgCode);

    /**
     * 查询上级组织节点组织编码
     *
     * @param approveTypeId 审批类型id
     * @param tenant        租户
     * @param orgCode       组织编码
     * @return 上级组织编码
     */
    @PostMapping(value = "/openApi/queryApproveStateForShow")
    String queryApproveStateForShow(@RequestParam("approveTypeId") Long approveTypeId,
                                    @RequestParam("tenant") String tenant,
                                    @RequestParam("orgCode") String orgCode);

    /**
     * 根据当前审批状态，获取后置触发事件编码、名称
     *
     * @param approveTypeId 审批类型id
     * @param orgCode       组织编码
     * @param stateCode     单据状态
     * @return
     */
    @PostMapping(value = "/openApi/queryApproveStateJumpEventByState")
    String queryApproveStateJumpEventByState(@RequestParam("approveTypeId") String approveTypeId,
                                             @RequestParam("tenant") String tenant,
                                             @RequestParam("orgCode") String orgCode,
                                             @RequestParam("stateCode") String stateCode);

    /**
     * 查询审批类型id
     *
     * @param tenant         租户编码
     * @param entityTypeCode 实体类型编码
     * @param dbTableName    数据库表名
     * @return
     */
    @PostMapping(value = "/openApi/queryApproveTypeId")
    String queryApproveTypeId(@RequestParam("tenant") String tenant,
                              @RequestParam("entityTypeCode") String entityTypeCode,
                              @RequestParam("dbTableName") String dbTableName);

    /**
     * 审批后置事件执行
     *
     * @param secretKey 秘钥
     * @param params    参数，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @return
     */
    @PostMapping(value = "/openApi/approveStateJumpDoPostEvent")
    String approveStateJumpDoPostEvent(@RequestParam("secretKey") String secretKey, @RequestParam("params") String params);

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
    @PostMapping(value = "/openApi/queryTableColSettings")
    String queryTableColSettings(@RequestParam("tenant") String tenant,
                                 @RequestParam("applicationCode") String applicationCode,
                                 @RequestParam("webPageCode") String webPageCode,
                                 @RequestParam("tableDomId") String tableDomId,
                                 @RequestParam("orgCode") String orgCode);


    /**
     * 根据租户编码，组织编码查询预警项
     *
     * @param tenant  租户编码
     * @param orgCode 一级组织编码
     * @return
     */
    @PostMapping(value = "/openApi/queryEarlyWarningType")
    String queryEarlyWarningType(@RequestParam("tenant") String tenant,
                                 @RequestParam("orgCode") String orgCode);

    /**
     * 根据预警项ID查询档位信息
     *
     * @param earlyWarningId 预警项ID
     * @return
     */
    @PostMapping(value = "/openApi/queryEarlyWarningGear")
    String queryEarlyWarningGear(@RequestParam("earlyWarningId") Long earlyWarningId);

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
    @PostMapping(value = "/openApi/queryEarlyWarningMsg")
    String queryEarlyWarningMsg(@RequestParam("tenant") String tenant,
                                @RequestParam("orgCode") String orgCode,
                                @RequestParam("entityTypeCode") String entityTypeCode,
                                @RequestParam("chargUserId") Long chargUserId,
                                @RequestParam("earlyWarningId") Long earlyWarningId,
                                @RequestParam("gearValue") Integer gearValue,
                                @RequestParam("warningInfo") String warningInfo,
                                @RequestParam("state") Integer state,
                                @RequestParam("isUserOffLamp") Integer isUserOffLamp,
                                @RequestParam("pageNo") Integer pageNo,
                                @RequestParam("pageSize") Integer pageSize);

    /**
     * 更新预警信息状态
     *
     * @param id
     * @param state
     * @param updateUserName
     * @param updateUserId
     * @return
     */
    @PostMapping(value = "/openApi/updateEarlyWarningState")
    String updateEarlyWarningState(@RequestParam("id") Long id,
                                   @RequestParam("state") Integer state,
                                   @RequestParam("updateUserName") String updateUserName,
                                   @RequestParam("updateUserId") Long updateUserId);


    /**
     * 查询周期内，同一条业务数据，超过n条不需要人工处理的预警消息的业务id列表
     *
     * @param tenant         租户编码
     * @param orgCode        组织编码
     * @param entityTypeCode 实体类型编码
     * @param bgtime         开始时间，年月日
     * @param edtime         结束时间，年月日
     * @param maxNum         最大条数
     * @return
     */
    @PostMapping(value = "/openApi/querySeriousEarlyWarningBusinessId")
    public String querySeriousEarlyWarningBusinessId(
            @RequestParam("tenant") String tenant,
            @RequestParam("orgCode") String orgCode,
            @RequestParam("entityTypeCode") String entityTypeCode,
            @RequestParam("bgtime") String bgtime,
            @RequestParam("edtime") String edtime,
            @RequestParam("maxNum") Integer maxNum);

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
    @PostMapping(value = "/openApi/approveAppointUser")
    String approveAppointUser(@RequestParam("tenant") String tenant,
                              @RequestParam("entityTypeCode") String entityTypeCode,
                              @RequestParam("orgCode") String orgCode,
                              @RequestParam("eventCode") String eventCode,
                              @RequestParam("uId") String uId,
                              @RequestParam("pboId") String pboId,
                              @RequestParam("stateCode") String stateCode);

    /**
     * 指派人处理完成后清空指派关系
     *
     * @param tenant         租户编码
     * @param entityTypeCode 实体类型编码
     * @param orgCode        组织编码
     * @param eventCode      触发事件编码
     * @param uId            用户id
     * @param pboId          单据id
     * @param stateCode      原状态
     * @return
     */
    @PostMapping(value = "/openApi/clearApproveAppoint")
    String clearApproveAppoint(@RequestParam("tenant") String tenant,
                               @RequestParam("entityTypeCode") String entityTypeCode,
                               @RequestParam("orgCode") String orgCode,
                               @RequestParam("eventCode") String eventCode,
                               @RequestParam("uId") Long uId,
                               @RequestParam("pboId") String pboId,
                               @RequestParam("stateCode") String stateCode);


    @PostMapping(value = "/openApi/queryGroup")
    String queryGroup(@RequestParam("tenant") String tenant,
                      @RequestParam("orgCode") String orgCode);

    /**
     * 查询用户的审批权限内数据
     *
     * @param tenant         租户编码
     * @param entityTypeCode 实体类型编码
     * @param dbTableName    数据库表名
     * @param uId            用户id
     * @param states         状态 ,支持为空，如果多个状态，则逗号分隔
     * @return
     */
    @PostMapping(value = "/openApi/queryApproveAuthority")
    String queryApproveAuthority(@RequestParam("tenant") String tenant,
                                 @RequestParam("entityTypeCode") String entityTypeCode,
                                 @RequestParam("dbTableName") String dbTableName,
                                 @RequestParam("uId") Long uId,
                                 @RequestParam("orgCode") String orgCode,
                                 @RequestParam("headerTableReName") String headerTableReName,
                                 @RequestParam("states") String states);

    /**
     * 根据业务id查询该实体的预警消息
     *
     * @param tenant         租户编码
     * @param orgCode        组织编码
     * @param entityTypeCode 实体类型编码
     * @param businessId     业务ID
     */
    @PostMapping(value = "/openApi/queryEarlyWarningMsgByBusinessId")
    String queryEarlyWarningMsgByBusinessId(@RequestParam("tenant") String tenant,
                                            @RequestParam("orgCode") String orgCode,
                                            @RequestParam("entityTypeCode") String entityTypeCode,
                                            @RequestParam("businessId") String businessId);

    /**
     * 查询用户基本信息
     *
     * @param id 用户id
     */
    @PostMapping(value = "/openApi/queryUserBaseInfo")
    String queryUserBaseInfo(@RequestParam("id") String id);

    /**
     *菜单点击记录
     * @param tenant
     * @param applicationCode
     * @param userId
     * @param loginName
     * @param menuId
     * @param menuName
     * @return
     */
    @PostMapping(value = "/openApi/menuClick")
    String menuClick(@RequestParam("tenant") String tenant,
                     @RequestParam("applicationCode") String applicationCode,
                     @RequestParam("userId") Long userId,
                     @RequestParam("loginName") String loginName,
                     @RequestParam("menuId") Long menuId,
                     @RequestParam("menuName") String menuName);
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
    @PostMapping(value = "/openApi/updatePlatUserBaseInfo")
    String updatePlatUserBaseInfo(@RequestParam("id") String id,
                     @RequestParam("orgDes") String orgDes,
                     @RequestParam("loginName") String loginName,
                     @RequestParam("nickName") String nickName,
                     @RequestParam("headImgUrl") String headImgUrl,
                     @RequestParam("phone") String phone,
                     @RequestParam("mail") String mail,
                     @RequestParam("state") String state,
                     @RequestParam("expirationTime") String expirationTime
                     );

    /**
     * 新增用户
     * @param tenant    租户编码
     * @param orgCode   组织编码
     * @param loginName 登陆账号
     * @param password  密码
     * @param nickName  昵称
     * @return
     */
    @PostMapping(value = "/openApi/cratePlatUser")
    String cratePlatUser(@RequestParam("tenant") String tenant,
                         @RequestParam("orgCode") String orgCode,
                         @RequestParam("loginName") String loginName,
                         @RequestParam("password") String password,
                         @RequestParam("nickName") String nickName,
                         @RequestParam("phone") String phone);

    /**
     * 查询租户组织下权限组列表
     * @param tenant    租户编码
     * @param orgCode   组织编码
     * @return
     */
    @PostMapping(value = "/openApi/queryAuthorityListByTenantAndOrgCode")
    String queryAuthorityListByTenantAndOrgCode(@RequestParam("tenant") String tenant,
                                                @RequestParam("orgCode") String orgCode);


    /**
     * 用户绑定权限组
     * @param authorityId   权限组id
     * @param uId           用户ID
     * @return
     */
    @PostMapping(value = "/openApi/userBindAuthority")
    String userBindAuthority(@RequestParam("authorityId") Long authorityId,
                             @RequestParam("uId") Long uId);

    /**
     * 根据租户编码应用编码查询应用id
     * @param tenant    租户
     * @param code      应用编码
     * @return
     */
    @PostMapping(value = "/openApi/queryApplicationIdByTenantAndCode")
    String queryApplicationIdByTenantAndCode(@RequestParam("tenant") String tenant,
                                             @RequestParam("code") String code);

    /**
     * 用户绑定平台应用权限
     * @param applicationId
     * @param uId
     * @return
     */
    @PostMapping(value = "/openApi/userBindApplication")
    String userBindApplication(@RequestParam("applicationId") Long applicationId,
                               @RequestParam("uId") Long uId);

    /**
     * 删除用户
     * @param id
     * @return
     */
    @PostMapping(value = "/openApi/deletePlatUserById")
    String deletePlatUserById(@RequestParam("id") String id);

    /**
     * 查询用户当前页面的实体数据权限
     * @param tenant        租户编码
     * @param applicationCode   应用编码
     * @param webpageCode       页面号
     * @param userId        用户id
     * @return
     */
    @PostMapping(value = "/openApi/queryOrgTreeByUserIdAndAuthorEntity")
    String queryOrgTreeByUserIdAndAuthorEntity(@RequestParam("tenant") String tenant,
                                               @RequestParam("applicationCode") String applicationCode,
                                               @RequestParam("webpageCode") String webpageCode,
                                               @RequestParam("userId") String userId);

}
