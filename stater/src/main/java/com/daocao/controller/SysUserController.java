package com.daocao.controller;


import com.daocao.dto.LoginDto;
import com.daocao.dto.PageDto;
import com.daocao.entity.SysUser;
import com.daocao.respose.ConResult;
import com.daocao.respose.Result;
import com.daocao.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/userlist")
    public Result userList(@RequestBody PageDto pageDto){
        ConResult conResult = sysUserService.selectlist(pageDto);
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

    @PostMapping("/adduser")
    public Result addUser(@Validated @RequestBody SysUser sysUser){
        ConResult conResult = sysUserService.AddUser(sysUser);
        if(!conResult.getIsOk()){
            return new Result(201,conResult.getMsg());
        }
        return new Result(200,conResult.getMsg(),conResult.getData());
    }

    @GetMapping("/userinfobyid/{id}")
    public Result userinfobyid(@PathVariable Integer id){
        ConResult conResult = sysUserService.UserInfoByid(id);
        return new Result(200,conResult.getData());
    }

    @PutMapping("/updateuser")
    public Result updateUser(@Validated @RequestBody SysUser sysUser){
        ConResult conResult = sysUserService.UpdateUser(sysUser);
        if(!conResult.getIsOk()){
            return new Result(201,conResult.getMsg());
        }
        return new Result(200,conResult.getMsg(),conResult.getData());
    }

    @DeleteMapping("/deleteuser/{id}")
    public Result deleteUser(@PathVariable Integer id){
        ConResult conResult = sysUserService.DeleteUser(id);
        if(!conResult.getIsOk()){
            return new Result(201,conResult.getMsg());
        }
        return new Result(200,conResult.getMsg(),conResult.getData());
    }
}

