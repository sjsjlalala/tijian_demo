package com.example.xixin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    @TableId(value = "orderId", type = IdType.AUTO)
    private Integer orderId;

    /**
     * 预约日期
     */
    @TableField("orderDate")
    private LocalDate orderDate;

    /**
     * 客户编号
     */
    @TableField("userId")
    private String userId;

    /**
     * 所属医院编号
     */
    @TableField("hpId")
    private Integer hpId;

    /**
     * 所属套餐编号
     */
    @TableField("smId")
    private Integer smId;

    /**
     * 订单状态（1：未归档；2：已归档）
     */
    @TableField("state")
    private Integer state;
}
