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
@TableName("cireport")
public class Cireport implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 检查项报告主键
     */
    @TableId(value = "cirId", type = IdType.AUTO)
    private Integer cirId;

    /**
     * 检查项编号
     */
    @TableField("ciId")
    private Integer ciId;

    /**
     * 检查项名称
     */
    @TableField("ciName")
    private String ciName;

    /**
     * 所属预约编号
     */
    @TableField("orderId")
    private Integer orderId;
}
