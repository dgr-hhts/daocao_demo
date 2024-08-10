package com.daocao.controller;


import com.daocao.dto.LoginDto;
import com.daocao.respose.ConResult;
import com.daocao.respose.Result;
import com.daocao.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2024-08-01
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserServiceImpl sysUserService;

    @GetMapping("/userlist")
    public Result userList(){
        ConResult conResult = sysUserService.UserList();
        if(!conResult.getIsOk()){
            return new Result(201,conResult.getMsg());
        }
        return new Result(200,conResult.getMsg(),conResult.getData());
    }

    @GetMapping("/userinfo")
    public Result userinfo(){
        ConResult conResult = sysUserService.UserInfo();
        if(!conResult.getIsOk()){
            return new Result(201,conResult.getMsg());
        }
        return new Result(200,conResult.getMsg(),conResult.getData());
    }
}

