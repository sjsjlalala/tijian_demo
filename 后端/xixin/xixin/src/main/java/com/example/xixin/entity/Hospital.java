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
@TableName("hospital")
public class Hospital implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 医院编号
     */
    @TableId(value = "hpId", type = IdType.AUTO)
    private Integer hpId;

    /**
     * 医院名称
     */
    @TableField("name")
    private String name;

    /**
     * 医院图片
     */
    @TableField("picture")
    private String picture;

    /**
     * 医院电话
     */
    @TableField("telephone")
    private String telephone;

    /**
     * 医院地址
     */
    @TableField("address")
    private String address;

    /**
     * 营业时间
     */
    @TableField("businessHours")
    private String businessHours;

    /**
     * 采血截止时间
     */
    @TableField("deadline")
    private String deadline;

    /**
     * 预约人数规则
     */
    @TableField("rule")
    private String rule;

    /**
     * 医院状态（1：正常；2：其他）
     */
    @TableField("state")
    private Integer state;
}
