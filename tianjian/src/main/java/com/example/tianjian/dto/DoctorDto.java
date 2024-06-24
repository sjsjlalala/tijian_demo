package com.example.tianjian.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    private String docId;
    private String docCode;
    private String realName;
    private Integer sex;
    private Integer deptno;
    private String token;
}
