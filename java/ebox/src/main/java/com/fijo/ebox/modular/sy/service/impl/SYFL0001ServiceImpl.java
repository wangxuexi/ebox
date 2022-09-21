package com.fijo.ebox.modular.sy.service.impl;

import com.fijo.ebox.base.service.impl.FijoBaseServiceImpl;
import com.fijo.ebox.base.util.common.DateUtils;
import com.fijo.ebox.base.util.plat.PlatUtil;
import com.fijo.ebox.dto.ResultUserDto;
import com.fijo.ebox.modular.sy.mapper.SYFL0001Mapper;
import com.fijo.ebox.modular.sy.pojo.SYFL0001;
import com.fijo.ebox.modular.sy.service.SYFL0001Service;
import com.mysql.jdbc.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * createTime:2019-10-12 17:02:50
 * SYFL0001ServiceImpl
 */
@Service
@Slf4j
public class SYFL0001ServiceImpl extends FijoBaseServiceImpl<SYFL0001,Long> implements SYFL0001Service {
    @Autowired
    private SYFL0001Mapper syfl0001Mapper;
    @Autowired
    HttpServletRequest request;

    @Autowired
    public SYFL0001ServiceImpl(SYFL0001Mapper syfl0001Mapper){
        super(syfl0001Mapper);
        this.syfl0001Mapper = syfl0001Mapper;
    }
    /**
     * 查询业务实体对应的附件列表
     * @param orgCode
     * @param entityTypeCode
     * @param objectId
     */
    @Override
    public List<SYFL0001> queryFile(String orgCode, String entityTypeCode, Long objectId) {
        return syfl0001Mapper.queryFile(orgCode, entityTypeCode, objectId);
    }


    /**
     * 查询业务实体对应的附件列表
     * @param orgCode
     * @param entityTypeCode
     * @param objectId
     * @param fileClass 文件类型
     */
    @Override
    public List<SYFL0001> queryFileByFileClass(String orgCode, String entityTypeCode, Long objectId, String fileClass) {
        return syfl0001Mapper.queryFileByFileClass(orgCode, entityTypeCode, objectId, fileClass);
    }

    /**
     * 逻辑批量删除附件
     * @param orgCode 组织编码
     * @param entityTypeCode    实体类编码
     * @param objectIds  来源ID集合
     * @param fileClass 文件类型
     */
    @Override
    public void logicDeleteFile(String orgCode, String entityTypeCode, String objectIds, String fileClass) {
        ResultUserDto loginUser = PlatUtil.getLoginUser(request);
        loginUser = loginUser == null ? new ResultUserDto() : loginUser;
        Long userId = loginUser.getUId() == null ? new Long(-1) : loginUser.getUId();
        String userName = StringUtils.isNullOrEmpty(loginUser.getNickName()) ? "*" : loginUser.getNickName();
        String operateTime = DateUtils.getNowDateYMDHMS();
        syfl0001Mapper.logicDeleteFile(orgCode, entityTypeCode, objectIds, fileClass,false,true,userId,userName,operateTime);
    }


}
