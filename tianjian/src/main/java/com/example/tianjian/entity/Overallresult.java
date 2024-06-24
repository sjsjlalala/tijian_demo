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
@TableName("overallresult")
public class Overallresult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 总检结论项编号
     */
    @TableId(value = "orId", type = IdType.AUTO)
    private Integer orId;

    /**
     * 总检结论项标题
     */
    @TableField("title")
    private String title;

    /**
     * 总检结论项内容
     */
    @TableField("content")
    private String content;

    /**
     * 所属预约编号
     */
    @TableField("orderId")
    private Integer orderId;


}
