package com.example.tianjian.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiestrong
 * @since 2024-06-13
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("cidetailedreport")
public class Cidetailedreport implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 检查项明细报告主键
     */
    @TableId(value = "cidrId", type = IdType.AUTO)
    private Integer cidrId;

    /**
     * 检查项明细名称
     */
    @TableField("name")
    private String name;

    /**
     * 检查项明细单位
     */
    @TableField("unit")
    private String unit;

    /**
     * 检查项细明正常值范围中的最小值
     */
    @TableField("minrange")
    private Double minrange;

    /**
     * 检查项细明正常值范围中的最大值
     */
    @TableField("maxrange")
    private Double maxrange;

    /**
     * 检查项细明正常值（非数字型）
     */
    @TableField("normalValue")
    private String normalValue;

    /**
     * 检查项验证范围说明文字
     */
    @TableField("normalValueString")
    private String normalValueString;

    /**
     * 检查项明细类型（1：数值范围验证型；2：数值相等验证型；3：无需验证型；4：描述型；5：其它）
     */
    @TableField("type")
    private Integer type;

    /**
     * 检查项目明细值
     */
    @TableField("value")
    private String value;

    /**
     * 此项是否异常（0：无异常；1：异常）
     */
    @TableField("isError")
    private Integer isError;

    /**
     * 所属检查项报告编号
     */
    @TableField("ciId")
    private Integer ciId;

    /**
     * 所属预约编号
     */
    @TableField("orderId")
    private Integer orderId;


}
