package com.fanlan.fightertree.common;

import lombok.Data;

/**
 * @author lenovo
 */
@Data
public class Result {
    private String code;
    private String message;
    private  Object data;

    public static Result ok(Object data){
        Result result = new Result();
        result.setCode("200");
        result.setMessage("请求成功");
        result.setData(data);
        return result;
    }
    public static Result error(){
        Result result = new Result();
        result.setCode("500");
        result.setMessage("请求失败");
        return result;
    }
}
