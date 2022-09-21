package com.fijo.ebox.modular.sy.service;
import com.fijo.ebox.base.service.FijoBaseService;
import com.fijo.ebox.modular.sy.pojo.ECSY0005;

import java.util.List;
import java.util.Map;


/**
*createTime:2020-05-26 03:32:19
*ECSY0005Service
*/
public interface ECSY0005Service extends FijoBaseService<ECSY0005,Long> {
    //系统标签类型条件查询
    List<ECSY0005> queryBy(String tagTypeCode, String tagTypeName);

    //系统标签类型根据code查询
    ECSY0005 queryBYCode(String tagTypeCode);

    //根据标签CODE查询企业标签
    List<Map> queryDetailsByCode(String tagTypeCode);
}
