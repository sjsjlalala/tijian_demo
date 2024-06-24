package com.example.tianjian.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDto {
    private Integer orderId;

    private LocalDate orderDate;

    private String userId;

    private String realName;

    private Integer sex;

    private Integer hpId;

    private String hpName;

    private Integer smId;

    private String smName;

    private Integer state;
}
