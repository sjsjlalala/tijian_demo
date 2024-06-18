package com.example.xixin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Boolean success;
    private String errorMsg;
    private Object data;
    private Long total;
    private Integer code;
    public static final Integer USERID_ERROR = 100;//手机号错误
    public static final Integer USERID_OR_PASSWORD_ERROR = 101;//手机号错误
    public static final Integer USER_EXIST = 102;//用户已存在
    public static final Integer REGISTER_FAIL = 103;//注册失败
    public static final Integer ORDER_IS_EXIST = 104;//用户不存在
    public static final Integer ORDER_FAIL = 105;
    public static final Integer ORDER_CANCEL_FAIL = 106;//订单取消失败

    public static Result ok(){
        return new Result(true, null, null, null,200);
    }
    public static <T> Result ok(T data){
        return new Result(true, null, data, null,200);
    }
    public static <T> Result ok(List<T> data, Long total){
        return new Result(true, null, data, total,200);
    }
    public static Result fail(String errorMsg,Integer code){
        return new Result(false, errorMsg, null, null,code);
    }
}
