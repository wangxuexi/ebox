package com.fijo.ebox.modular.sy.mapper;
import com.fijo.ebox.base.mapper.FijoBaseMapper;
import com.fijo.ebox.modular.sy.pojo.ECSY0004;

import java.util.List;


/**
* createTime:2020-05-25 07:08:27
*ECSY0004Mapper
*/
public interface ECSY0004Mapper extends FijoBaseMapper<ECSY0004,Long> {
    //首页表查询
    List<ECSY0004> queryBy(ECSY0004 ecsy0004);
}
