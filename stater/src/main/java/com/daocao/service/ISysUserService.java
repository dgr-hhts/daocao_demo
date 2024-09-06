package com.daocao.service;

import com.daocao.dto.LoginDto;
import com.daocao.dto.PageDto;
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

    ConResult UserInfo();

    ConResult selectlist(PageDto pageDto);

    ConResult AddUser(SysUser user);

    ConResult UserInfoByid(Integer id);

    ConResult UpdateUser(SysUser user);

    ConResult DeleteUser(Integer id);
}
