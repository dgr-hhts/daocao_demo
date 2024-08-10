package com.daocao.controller;


import com.daocao.dto.LoginDto;
import com.daocao.respose.Result;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2024-08-01
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

    @GetMapping("test")
    public Result test(){
        return new Result(200,"test","1212121");
    }
}

