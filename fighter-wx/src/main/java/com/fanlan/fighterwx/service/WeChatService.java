package com.fanlan.fighterwx.service;

/**
 * @author lenovo
 */
public interface WeChatService {

    String getAccessToken() throws Exception;

    String getPhoneNumber(String code) throws Exception;
}
