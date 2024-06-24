package com.example.xixin.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDate;

/**
 * @projectName: xixin
 * @package: com.example.xixin.dto
 * @className: UsersDto
 * @author: moki
 * @description: TODO
 * @date: 2024/6/13 14:57
 * @version: 1.0
 */
@Data

public class UsersDto {


    /**
     * 用户编号（手机号码）
     */

    private String userId;



    /**
     * 真实姓名
     */

    private String realName;

    /**
     * 用户性别（1：男；0女）
     */

    private Integer sex;

    /**
     * 身份证号
     */

    private String identityCard;

    /**
     * 出生日期
     */

    private LocalDate birthday;

    /**
     * 用户类型（1：普通用户；2：东软内部员工；3：其他）
     */

    private Integer userType;
}
