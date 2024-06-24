package com.example.xixin.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * @projectName: xixin
 * @package: com.example.xixin.dto
 * @className: OrderDto
 * @author: moki
 * @description: TODO
 * @date: 2024/6/17 13:20
 * @version: 1.0
 */
@Data
public class OrderDto {
    private Integer hpId;
    private LocalDate date;
}
