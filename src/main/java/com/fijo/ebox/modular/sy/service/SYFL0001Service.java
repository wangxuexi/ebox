package com.fijo.ebox.modular.sy.service;

import com.fijo.ebox.base.service.FijoBaseService;
import com.fijo.ebox.modular.sy.pojo.SYFL0001;

import java.util.List;


/**
 * createTime:2019-10-12 17:02:50
 * SYFL0001Service
 */
public interface SYFL0001Service  extends FijoBaseService<SYFL0001,Long> {



    /**
     * 查询业务实体对应的附件列表
     * @param orgCode
     * @param entityTypeCode
     * @param objectId
     */
    List<SYFL0001> queryFile(String orgCode,String entityTypeCode,Long objectId);

    /**
     * 查询业务实体对应的附件列表
     * @param orgCode
     * @param entityTypeCode
     * @param objectId
     * @param fileClass 文件类型
     */
    List<SYFL0001> queryFileByFileClass(String orgCode,String entityTypeCode,Long objectId,String fileClass);

    /**
     * 逻辑批量删除附件
     * @param orgCode 组织编码
     * @param entityTypeCode    实体类编码
     * @param objectIds  来源ID集合
     * @param fileClass 文件类型
     */
    void logicDeleteFile(String orgCode,String entityTypeCode,String objectIds,String fileClass);


}
