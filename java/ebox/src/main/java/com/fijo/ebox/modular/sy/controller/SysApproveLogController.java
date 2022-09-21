package com.fijo.ebox.modular.sy.controller;

import com.fijo.ebox.dto.ResultDto;
import com.fijo.ebox.modular.sy.service.SysApproveLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/SysApproveLog")
@Slf4j
public class SysApproveLogController {

    @Autowired
    private SysApproveLogService sysApproveLogService;


    /**
     * 跟据用户ID查询本人审批记录
     * @param uId   用户ID
     * @return
     */
    @PostMapping(value = "/querySysApproveLogByUid")
    public String querySysApproveLogByUid(String uId){
        try {
            Map resultMap = sysApproveLogService.querySysApproveLogByUid(uId);
            log.info("【查询本人审批记录】 查询成功!");
            return ResultDto.SUCCESS("查询成功！",resultMap);
        } catch (Exception e) {
            log.error("【查询本人审批记录】 查询失败，失败原因：{}",e.getMessage());
            return ResultDto.ERROR("【查询本人审批记录】查询失败！",e.toString());
        }
    }
}
