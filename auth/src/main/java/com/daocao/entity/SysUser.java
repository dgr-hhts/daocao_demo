package com.daocao.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 后台用户表
 * </p>
 *
 * @author ${author}
 * @since 2024-08-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 用户性别（0男 1女 2未知）
     */
    private Integer sex;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    private Integer status;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新者
     */
    private String updater;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    @TableLogic
    private Integer deleted;


}