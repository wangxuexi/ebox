package com.fijo.ebox.modular.fa.service;
import com.fijo.ebox.base.service.FijoBaseService;
import com.fijo.ebox.modular.fa.pojo.BXFA0001;
import java.util.List;
import java.util.Map;

/**
 *FA_BASE_INFO
 *BXFA0001
 **/
public interface BXFA0001Service extends FijoBaseService<BXFA0001,Long>{

    /**
     * 分页查询
     * @param orgCode 组织编码
     * @param pageNo 页码
     * @param pageSize 页长
     * @return
     */
    Map<String,Object> queryAllByPage(String orgCode,Integer pageNo,Integer pageSize);
}

