package com.daocao.controller;

import com.daocao.respose.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2024-08-01
 */
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {

    @GetMapping("test")
    public Result test(){
        return new Result(200,"test");
    }
}

