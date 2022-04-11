package com.fanlan.fighteraop.vo;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResultVO {

    String code;
    String msg;
    private Map<String, Object> data = new HashMap<>();

    public static ResultVO ok(){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode("200");
        resultVO.setMsg("请求成功");
        return resultVO;
    }
    public  ResultVO data(String key,Object value){
        this.data.put(key,value);
        return this;
    }
}
