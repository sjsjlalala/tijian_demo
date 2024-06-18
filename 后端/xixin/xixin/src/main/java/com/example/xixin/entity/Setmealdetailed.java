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
@TableName("setmealdetailed")
public class Setmealdetailed implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 套餐明细编号（无意义主键）
     */
    @TableId(value = "sdId", type = IdType.AUTO)
    private Integer sdId;

    /**
     * 套餐编号
     */
    @TableField("smId")
    private Integer smId;

    /**
     * 检查项编号
     */
    @TableField("ciId")
    private Integer ciId;
}
