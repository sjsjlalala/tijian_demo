package com.example.xixin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author moki
 * @since 2024-06-13
 */
@Getter
@Setter
@TableName("users")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号（手机号码）
     */
    @TableId("userId")
    private String userId;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 真实姓名
     */
    @TableField("realName")
    private String realName;

    /**
     * 用户性别（1：男；0女）
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 身份证号
     */
    @TableField("identityCard")
    private String identityCard;

    /**
     * 出生日期
     */
    @TableField("birthday")
    private LocalDate birthday;

    /**
     * 用户类型（1：普通用户；2：东软内部员工；3：其他）
     */
    @TableField("userType")
    private Integer userType;
}
