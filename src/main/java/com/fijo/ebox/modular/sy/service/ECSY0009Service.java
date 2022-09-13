package com.fijo.ebox.modular.sy.service;
import com.fijo.ebox.base.service.FijoBaseService;
import com.fijo.ebox.modular.sy.pojo.ECSY0009;

import java.util.List;

/**
 *SYS_MSG
 *ECSY0009
 **/
public interface ECSY0009Service extends FijoBaseService<ECSY0009,Long>{

    Integer updateState(Long Id,Long userId);
    /**
     *更新短信发送状态
     * @param updateTime
     */
    void updateMsgState(String updateTime, String id);

    List<ECSY0009> queryMsgBySrare(ECSY0009 ecsy0009);

    List<ECSY0009>  getMsgListByUser(ECSY0009 ecsy0009);


    void updateMsgReadState(String readDate, String id);
}

