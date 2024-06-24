package com.example.tianjian.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {
    OK(true, 200, "成功"),
    BAD_REQUEST(false, 400, "参数错误"),
    UNAUTHORIZED(false, 401, "未授权，请登录"),
    NOT_FOUND(false, 404, "找不到请求的资源");

    private Boolean success;
    private Integer code;
    private String message;
}

