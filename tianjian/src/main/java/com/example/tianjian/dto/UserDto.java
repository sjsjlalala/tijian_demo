package com.example.tianjian.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String userId;
    private String realName;
    private Integer sex;
    private String identityCard;
    private LocalDate birthday;
    private Integer userType;
    private String token;
}
