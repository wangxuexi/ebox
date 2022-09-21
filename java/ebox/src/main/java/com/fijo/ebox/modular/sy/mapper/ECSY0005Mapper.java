package com.fijo.ebox.modular.sy.mapper;
import com.fijo.ebox.base.mapper.FijoBaseMapper;
import com.fijo.ebox.modular.sy.pojo.ECSY0005;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
* createTime:2020-05-26 03:32:19
*ECSY0005Mapper
*/
public interface ECSY0005Mapper extends FijoBaseMapper<ECSY0005,Long> {
    //系统标签类型条件查询
    List<ECSY0005> queryBy(String tagTypeCode, String tagTypeName);

    //系统标签类型根据code查询
    ECSY0005 queryBYCode(String tagTypeCode);

    //根据标签CODE查询企业标签
    List<Map> queryDetailsByCode(@Param("tagTypeCode") String tagTypeCode);
}
