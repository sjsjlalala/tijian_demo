package com.example.xixin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("doctor")
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 医生编号
     */
    @TableId(value = "docId", type = IdType.AUTO)
    private Integer docId;

    /**
     * 医生编码
     */
    @TableField("docCode")
    private String docCode;

    /**
     * 真实姓名
     */
    @TableField("realName")
    private String realName;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 性别
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 所属科室（1：检验科；2：内科；3：外科）
     */
    @TableField("deptno")
    private Integer deptno;
}
