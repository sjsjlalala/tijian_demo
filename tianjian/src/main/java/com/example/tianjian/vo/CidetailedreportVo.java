package com.example.tianjian.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CidetailedreportVo {
    private int cidrId;
    private int isError;
    private String value;
}