package com.daocao.service;

import com.daocao.dto.LoginDto;
import com.daocao.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.daocao.respose.ConResult;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2024-08-01
 */
public interface ISysUserService extends IService<SysUser> {
    ConResult Login(LoginDto loginDto);

    ConResult UserList();
}
