package com.daocao.controller;

import com.daocao.dto.LoginDto;
import com.daocao.respose.ConResult;
import com.daocao.respose.Result;
import com.daocao.service.ISysUserService;
import com.daocao.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private SysUserServiceImpl sysUserService;

    @PostMapping("/sys")
    public Result sysLogin(@RequestBody LoginDto loginDto){
        System.out.println(loginDto);
        ConResult login = sysUserService.Login(loginDto);
        if(!login.getIsOk()){
            return new Result(201,login.getMsg());
        }
        return new Result(200,login.getMsg(),login.getData());
    }
}
