package com.fijo.ebox.modular.ct.service;
import com.fijo.ebox.base.service.FijoBaseService;
import com.fijo.ebox.modular.ct.pojo.BXCT0001;
import java.util.List;
import java.util.Map;

/**
 *CIGARETTE_BASE_INFO
 *BXCT0001
 **/
public interface BXCT0001Service extends FijoBaseService<BXCT0001,Long>{

    /**
     * 分页查询
     * @param orgCode 组织编码
     * @param pageNo 页码
     * @param pageSize 页长
     * @return
     */
    Map<String,Object> queryAllByPage(String orgCode,Integer pageNo,Integer pageSize);
}

