package com.daocao.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.daocao.dto.LoginDto;
import com.daocao.dto.PageDto;
import com.daocao.entity.SysUser;
import com.daocao.dao.SysUserDao;
import com.daocao.respose.ConResult;
import com.daocao.respose.PageResult;
import com.daocao.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daocao.unils.JwtUtil;
import com.daocao.unils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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

    @Override
    public ConResult selectlist(PageDto pageDto) {
        IPage<SysUser> page = new Page<>(pageDto.getPageNum(),pageDto.getPageSize());
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();

        queryWrapper.like(!StringUtils.isEmpty(pageDto.getUsername()),"username",pageDto.getUsername());
        queryWrapper.like(!StringUtils.isEmpty(pageDto.getNickname()),"nickname",pageDto.getNickname());
        queryWrapper.like(!StringUtils.isEmpty(pageDto.getMobile()),"mobile",pageDto.getMobile());

        IPage<SysUser> sysUserIPage = sysUserDao.selectPage(page, queryWrapper);
        return new ConResult(true,"查询成功!",new PageResult<>(sysUserIPage.getRecords(),(int)sysUserIPage.getTotal()));
    }

    @Override
    public ConResult AddUser(SysUser user) {
        Map<String,Object> map = ThreadLocalUtil.get();
        user.setCreator((String)map.get("username"));
        user.setUpdater((String)map.get("username"));
        int insert = sysUserDao.insert(user);
        if(insert <= 0){
            return new ConResult(false,"添加失败!");
        }
        return new ConResult(true,"添加成功!",true);
    }

    @Override
    public ConResult UserInfoByid(Integer id) {
        SysUser sysUser = sysUserDao.selectById(id);
        return new ConResult(true,sysUser);
    }

    @Override
    public ConResult UpdateUser(SysUser user) {
        int i = sysUserDao.updateById(user);
        if(i <= 0){
            return new ConResult(false,"修改失败!");
        }
        return new ConResult(true,"修改成功!",true);
    }

    @Override
    public ConResult DeleteUser(Integer id) {
        int i = sysUserDao.deleteById(id);
        if(i <= 0){
            return new ConResult(false,"删除失败!");
        }
        return new ConResult(true,"删除成功!",true);
    }


}
