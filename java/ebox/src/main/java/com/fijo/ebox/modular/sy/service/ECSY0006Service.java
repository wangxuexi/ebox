package com.fijo.ebox.modular.sy.service;
import com.fijo.ebox.base.service.FijoBaseService;
import com.fijo.ebox.modular.sy.pojo.ECSY0006;

import java.util.List;


/**
*createTime:2020-05-26 03:34:35
*ECSY0006Service
*/
public interface ECSY0006Service extends FijoBaseService<ECSY0006,Long> {
    //系统标签详情根据tagTypeId查询
    List<ECSY0006> queryBytagTypeCode(String tagTypeCode);

}
