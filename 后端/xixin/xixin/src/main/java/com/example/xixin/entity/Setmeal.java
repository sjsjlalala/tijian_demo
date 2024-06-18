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
@TableName("setmeal")
public class Setmeal implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 套餐编号
     */
    @TableId(value = "smId", type = IdType.AUTO)
    private Integer smId;

    /**
     * 套餐名称
     */
    @TableField("name")
    private String name;

    /**
     * 套餐类型（1：男士餐套；0：女士套餐）
     */
    @TableField("type")
    private Integer type;

    /**
     * 套餐价格
     */
    @TableField("price")
    private Integer price;
}
