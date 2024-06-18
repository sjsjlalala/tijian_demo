package com.example.xixin.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * @projectName: xixin
 * @package: com.example.xixin.dto
 * @className: CalendarDto
 * @author: moki
 * @description: TODO
 * @date: 2024/6/17 10:31
 * @version: 1.0
 */
@Data
public class CalendarDto {

    private LocalDate date;
    private Integer total;
    private Integer remainder;

    private Integer used;
}
