package com.example.xixin.dto;

import com.example.xixin.entity.Checkitem;
import lombok.Data;

import java.util.List;

/**
 * @projectName: xixin
 * @package: com.example.xixin.dto
 * @className: SetmealDto
 * @author: moki
 * @description: TODO
 * @date: 2024/6/14 10:45
 * @version: 1.0
 */
@Data
public class SetmealDto {

    private Integer smId;
    private String name;
    private Integer type;
    private Integer price;
    private List<Checkitem> checkitem;
}
