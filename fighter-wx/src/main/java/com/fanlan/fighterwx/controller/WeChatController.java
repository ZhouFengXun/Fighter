package com.fanlan.fighterwx.controller;


import com.fanlan.fighterwx.service.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lenovo
 */
@RestController
@RequestMapping("/wechat")
public class WeChatController {

    @Autowired
    WeChatService weChatService;

    /**
     * 获取小程序全局唯一后台接口调用凭据（access_token）
     *
     * @return
     */
    @PostMapping("/getAccessToken")
    public String getAccessToken() {
        try {
            return weChatService.getAccessToken();
        } catch (Exception e) {
            e.printStackTrace();
            return "获取凭据（access_token）异常";
        }
    }
    /**
     * 获取手机号
     *
     * @param code 动态令牌
     * @return
     */
    @PostMapping("/getPhoneNumber")
    public String getPhoneNumber(@RequestParam String code) {
        try {
            return weChatService.getPhoneNumber(code);
        } catch (Exception e) {
            e.printStackTrace();
            return "获取手机号异常";
        }
    }
}
