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
@TableName("checkitem")
public class Checkitem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 检查项编号
     */
    @TableId(value = "ciId", type = IdType.AUTO)
    private Integer ciId;

    /**
     * 检查项名称
     */
    @TableField("ciName")
    private String ciName;

    /**
     * 检查项内容
     */
    @TableField("ciContent")
    private String ciContent;

    /**
     * 检查项意义
     */
    @TableField("meaning")
    private String meaning;

    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;
}
