package com.fanlan.fighterwebsocket.controller;

import com.fanlan.fighterwebsocket.config.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebSocketController {

    private static Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    /**
     * 启动页面
     *
     * @return
     */
    @GetMapping("/start")
    public String start() {
        return "index";
    }

    /**
     * 登录用户群发消息接口
     *
     * @param message 消息体
     */
    @PostMapping("/sendMessage")
    @ResponseBody
    public void sendMessage(@RequestParam String message) {
        try {
            WebSocketServer.sendinfoBatch(message);
        } catch (Exception e) {
            logger.error("登录用户群发消息接口异常", e.getStackTrace());
        }
    }

}

