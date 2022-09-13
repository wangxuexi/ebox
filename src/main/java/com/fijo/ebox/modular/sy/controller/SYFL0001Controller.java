package com.fijo.ebox.modular.sy.controller;



import com.fijo.ebox.modular.sy.service.SYFL0001Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 控制器
 * 党群党员
 *
 * @author
 * @Date 2019-07-16 15:34:58
 */
@RestController
@Slf4j
@RequestMapping(value = "/SYFL0001")
public class SYFL0001Controller {
    @Autowired
   private SYFL0001Service service;
}


