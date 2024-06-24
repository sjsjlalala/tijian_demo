package com.example.tianjian.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private String userId;
    private String realName;
    private Integer sex;
    private Integer orderId;
    private LocalDate orderDate;
    private Integer smId;
    private String smName;
    private Integer state;
}
