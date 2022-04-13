package com.fanlan.fighterwebsocket.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * websocket的处理类。
 * 作用相当于HTTP请求
 * 中的controller
 */
@Component
@ServerEndpoint("/api/pushMessage/{userId}")
public class WebSocketServer {

    private final static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static AtomicInteger atomicInteger=new AtomicInteger(0);
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>(18);
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收userId
     */
    private String userId = "";

    /**
     * 连接建立成
     * 功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            //加入set中
            webSocketMap.put(userId,this);
        } else {
            //加入set中
            webSocketMap.put(userId, this);
            //在线数加1
            addOnlineCount();
        }
        logger.info("用户连接:" + userId + ",当前在线人数为:" + getOnlineCount());
        sendMessage("连接成功");
    }

    /**
     * 连接关闭
     * 调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            //从set中删除
            subOnlineCount();
        }
        logger.info("用户退出:" + userId + ",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消
     * 息后调用的方法
     *
     * @param message 客户端发送过来的消息
     **/
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("用户消息:" + userId + ",报文:" + message);
        //可以群发消息
        //消息保存到数据库、redis
        if (StringUtils.isNotBlank(message)) {
            try {
                //解析发送的报文
                JSONObject jsonObject = JSON.parseObject(message);
                //追加发送人(防止串改)
                jsonObject.put("fromUserId", this.userId);
                String toUserId = jsonObject.getString("toUserId");
                //传送给对应toUserId用户的websocket
                if (StringUtils.isNotBlank(toUserId) && webSocketMap.containsKey(toUserId)) {
                    webSocketMap.get(toUserId).sendMessage(message);
                } else {
                    //否则不在这个服务器上，发送到mysql或者redis
                    logger.error("请求的userId:" + toUserId + "不在该服务器上");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("用户错误:" + this.userId + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     *   群发消息
     * @param message
     */
    public static void sendinfoBatch(String message){
        if (StringUtils.isNotBlank(message)){
            logger.info("群发消息:{}",message);
            Set<Map.Entry<String, WebSocketServer>> entries = webSocketMap.entrySet();
            for (Map.Entry<String, WebSocketServer> entry : entries) {
                String key = entry.getKey();
                //发送消息
                sendInfo(message,key);
            }
        }else {
            logger.info("没有要群发的消息");
        }
    }
    /**
     * 发送自定
     * 义消息
     **/
    public static void sendInfo(String message, String userId) {
        logger.info("发送消息到:" + userId + "，报文:" + message);
        if (StringUtils.isNotBlank(userId) && webSocketMap.containsKey(userId)) {
            webSocketMap.get(userId).sendMessage(message);
        } else {
            logger.error("用户" + userId + ",不在线！");
        }
    }

    /**
     * 实现服务器主动推送消息到前端
     */
    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得此时的
     * 在线人数
     *
     * @return
     */
    public static synchronized int getOnlineCount() {
        return atomicInteger.get();
    }

    /**
     * 在线人
     * 数加1
     */
    public static synchronized void addOnlineCount() {
        atomicInteger.incrementAndGet();
    }

    /**
     * 在线人
     * 数减1
     */
    public static synchronized void subOnlineCount() {
        atomicInteger.decrementAndGet();
    }
}


