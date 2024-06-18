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
@TableName("checkitemdetailed")
public class Checkitemdetailed implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 检查项明细编号
     */
    @TableId(value = "cdId", type = IdType.AUTO)
    private Integer cdId;

    /**
     * 检查项细明名称
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
     * 检查项明细类型（1：数值围范验证型；2：数值相等验证型；3：无需验证型；4：描述型；5：其它）
     */
    @TableField("type")
    private Integer type;

    /**
     * 所属检查项编号
     */
    @TableField("ciId")
    private Integer ciId;

    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;
}
