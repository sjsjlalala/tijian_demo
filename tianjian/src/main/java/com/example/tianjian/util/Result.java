package com.example.tianjian.util;

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
    private Integer maxPageNum;

    public Result (Object data, Long total){
        this.data = data;
        this.total = total;
        this.success = true;
        this.errorMsg = null;
        this.maxPageNum = null;
    }

    public Result (List<?> data){
        this.data = data;
        this.total = 0L;
        this.success = true;
        this.errorMsg = null;
        this.maxPageNum = null;
    }

    public static Result ok(){
        return new Result(true, null, null, null,null);
    }
    public static Result ok(Object data){
        return new Result(true, null, data, null,null);
    }
    public static Result ok(List<?> data, Long total, Integer maxPageNum){
        return new Result(true, null, data, total,maxPageNum);
    }
    public static Result ok(List<?> data, Long total){
        return new Result(true, null, data, total,null);
    }
    public static Result fail(String errorMsg){
        return new Result(false, errorMsg, null, null,null);
    }
}

