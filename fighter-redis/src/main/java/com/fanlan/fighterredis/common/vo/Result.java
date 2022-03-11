package com.fanlan.fighterredis.common.vo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class Result<T> {

    private int resultCode;

    private String resultMessage;

    private T resultJson;

    public Result() {}

    public Result(int code, String message, T resultJson) {
        this.resultCode = code;
        this.resultMessage = message;
        this.resultJson = resultJson;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return this.resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public T getResultJson() {
        return this.resultJson;
    }

    public void setResultJson(T resultJson) {
        this.resultJson = resultJson;
    }

    public String toString() {
        return JSON.toJSONString(this, new SerializerFeature[] { SerializerFeature.WriteDateUseDateFormat });
    }

}

