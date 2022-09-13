package com.fijo.ebox.modular.sy.mapper;
import com.fijo.ebox.base.mapper.FijoBaseMapper;
import com.fijo.ebox.modular.sy.pojo.ECSY0006;

import java.util.List;


/**
* createTime:2020-05-26 03:34:35
*ECSY0006Mapper
*/
public interface ECSY0006Mapper extends FijoBaseMapper<ECSY0006,Long> {
    //系统标签详情根据tagTypeId查询
    List<ECSY0006> queryBytagTypeCode(String tagTypeCode);

}
