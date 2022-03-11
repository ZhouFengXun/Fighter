package com.fanlan.fighterredis.common.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class ResultVo {
    Integer Code;
    String Message;
    String Data;

    public static JSONObject getFailedResult(Integer code,String Message){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("Code",code);
        jsonObject.put("Message",Message);
        return  jsonObject;
    }
    public static JSONObject ok(String Message){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("Message",Message);
        return  jsonObject;
    }
}
