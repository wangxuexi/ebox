package com.fijo.ebox.modular.fa.service;
import com.fijo.ebox.base.service.FijoBaseService;
import com.fijo.ebox.modular.fa.pojo.BXFA0002;
import java.util.List;
import java.util.Map;

/**
 *POLICY_BASE_INFO
 *BXFA0002
 **/
public interface BXFA0002Service extends FijoBaseService<BXFA0002,Long>{

    /**
     * 分页查询
     * @param orgCode 组织编码
     * @param pageNo 页码
     * @param pageSize 页长
     * @return
     */
    Map<String,Object> queryAllByPage(String orgCode,Integer pageNo,Integer pageSize);

    /**
     * 根据是否分配类型，查询保单
     * @param isDistributeFa
     * @return
     */
    List<BXFA0002> queryListByDistributeFaType(Boolean isDistributeFa);

    /**
     * 批量更新分配信息
     * @param ids
     * @param distributeFaId
     * @param distributeFaNo
     * @param distributeFaName
     */
    void batchUpdateDistributeInfo(String ids,Long distributeFaId,String  distributeFaNo,String distributeFaName);

    /**
     * 批量设置是否分配fa
     * @param ids
     * @param isDistributeFa
     */
    void batchSetIsDistributeFa(String ids,Boolean isDistributeFa);
}

