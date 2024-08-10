package com.daocao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.daocao.dto.LoginDto;
import com.daocao.entity.SysUser;
import com.daocao.dao.SysUserDao;
import com.daocao.respose.ConResult;
import com.daocao.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daocao.unils.JwtUtil;
import com.daocao.unils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2024-08-01
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements ISysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    public ConResult Login(LoginDto loginDto) {

        LambdaQueryWrapper<SysUser> query = new LambdaQueryWrapper<SysUser>();
        query.eq(SysUser::getUsername,loginDto.getAccount());
        SysUser sysUser = sysUserDao.selectOne(query);
        if(sysUser == null){
            return new ConResult(false,"用户名不存在");
        }
        if(!(sysUser.getPassword().equals(loginDto.getPassword()))){
            return new ConResult(false,"密码不正确");
        }

        Map<String,Object> claims = new HashMap<>();
        claims.put("id",sysUser.getId());
        claims.put("username",sysUser.getUsername());

        String token = JwtUtil.getToken(claims);

        return new ConResult(true,"登录成功",token);
    }

    @Override
    public ConResult UserList() {
        List<SysUser> sysUsers = sysUserDao.selectList(null);

        if(sysUsers == null){
            return new ConResult(false,"查询失败!");
        }
        return new ConResult(true,"查询成功!",sysUsers);
    }

    @Override
    public ConResult UserInfo() {
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String)map.get("username");
        LambdaQueryWrapper<SysUser> query = new LambdaQueryWrapper<>();
        query.eq(SysUser::getUsername,username);
        SysUser sysUser = sysUserDao.selectOne(query);
        return new ConResult(true,"查询成功!",sysUser);
    }
}
