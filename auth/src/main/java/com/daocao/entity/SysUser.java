package com.daocao.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;

import com.daocao.config.Excle.ExcelExport;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

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
    @ExcelExport("用户id")
    private Integer id;

    /**
     * 用户账号
     */
    @NotNull
    @ExcelExport("用户账号")
    private String username;

    /**
     * 用户昵称
     */
    @ExcelExport("用户昵称")
    private String nickname;

    /**
     * 用户邮箱
     */
    @Email(message = "邮箱格式错误")
    @ExcelExport("用户邮箱")
    private String email;

    /**
     * 手机号码
     */
    @ExcelExport("手机号码")
    private String mobile;

    /**
     * 用户性别（0男 1女 2未知）
     */
    @ExcelExport(value = "用户性别", kv = "0-男;1-女")
    @Range(min = 0, max = 2, message = "性别错误")
    private Integer sex;

    /**
     * 头像地址
     */
    private String avatar;

    @ExcelExport("头像图片")
    @TableField(exist = false)
    private URL url;
    /**
     * 密码
     */
    @ExcelExport("密码")
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    @ExcelExport(value = "帐号状态", kv = "0-正常;1-停用")
    private Integer status;

    /**
     * 创建者
     */
    @ExcelExport("创建者")
    private String creator;

    /**
     * 创建时间
     */
    @ExcelExport("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新者
     */
    @ExcelExport("更新者")
    private String updater;

    /**
     * 更新时间
     */
    @ExcelExport("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 备注
     */
    @ExcelExport("备注")
    private String remark;

    @TableLogic
    private Integer deleted;




}
