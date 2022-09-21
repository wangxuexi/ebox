package com.fijo.ebox.modular.sy.mapper;
import com.fijo.ebox.base.mapper.FijoBaseMapper;
import com.fijo.ebox.modular.sy.pojo.ECSY0009;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *SYS_MSG
 *ECSY0009
 **/
public interface ECSY0009Mapper extends FijoBaseMapper<ECSY0009,Long>{

    /**
     * 修改阅读状态
     * @return
     */
    Integer updateState(@Param("readDate") String readDate,@Param("Id") Long Id,@Param("userId") Long userId);

    /**
     *更新短信发送状态
     * @param updateTime
     */
    void updateMsgState(@Param("updateTime")String updateTime,@Param("id")String id);

    List<ECSY0009> queryMsgBySrare(ECSY0009 ecsy0009);


    List<ECSY0009>   getMsgListByUser(ECSY0009 ecsy0009);


    void updateMsgReadState(@Param("readDate")String readDate,@Param("id")String id);




}

