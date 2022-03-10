package com.fanlan.fighterwx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fanlan.fighterwx.service.WeChatService;
import com.fanlan.fighterwx.utils.RedisUtil;
import com.fanlan.fighterwx.utils.RestTemplateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * @author lenovo
 */
@Service
public class WeChatServiceImpl implements WeChatService {
    /**
     * 唯一凭证
     */
    private String appId = "xxxx";
    /**
     * 小程序唯一凭证密钥
     */
    private String appSecret = "xxxxxx";

    @Autowired
    RedisUtil redisUtil;

    private String errcCode = "errcode";

    @Autowired
    WeChatService weChatService;

    @Autowired
    RedisTemplate redisTemplate;

    private static Logger logger = LoggerFactory.getLogger(WeChatServiceImpl.class);

    @Override
    public String getAccessToken() throws Exception {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
        RestTemplate restTemplate = RestTemplateUtils.getRestTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        if (forEntity.getStatusCodeValue() == 200) {
            JSONObject jsonObject = JSONObject.parseObject(forEntity.getBody());
            //获取到的凭证
            String accessToken = jsonObject.getString("access_token");
            // 凭证有效时间，单位：秒。目前是7200秒之内的值。
            String expiresIn = jsonObject.getString("expires_in");
            long timeMillis = System.currentTimeMillis();
            HashMap<String, Object> map = new HashMap<>();
            map.put("accessToken", accessToken);
            map.put("expireTime", String.valueOf(timeMillis));
            logger.info("expireTime:{}", String.valueOf(timeMillis));
            redisTemplate.opsForValue().set("accessToken", map);
            // 7200 秒   5 分钟的平滑过度
            //redisTemplate.expire("accessToken", 120, TimeUnit.SECONDS);
            return forEntity.getBody();
        }
        return "http请求失败";
    }

    @Override
    public String getPhoneNumber(String code) throws Exception {
        HashMap<String, String> accesstoken = (HashMap) redisTemplate.opsForValue().get("accessToken");
        if (accesstoken == null || accesstoken.isEmpty()) {
            String accessToken = weChatService.getAccessToken();
            if (accessToken == "http请求失败") {
                return "http请求失败";
            }
            accesstoken = (HashMap) redisTemplate.opsForValue().get("accessToken");
        }
        if (IsExpireAccessToken(accesstoken)) {
            String accessToken = weChatService.getAccessToken();
            if (accessToken == "http请求失败") {
                return "http请求失败";
            }
            accesstoken = (HashMap) redisTemplate.opsForValue().get("access_token");
        }
        String accessToken1 = (String) accesstoken.get("accessToken");
//        JSONObject jsonObject1 = JSONObject.parseObject(accessToken1);
//        String accessToken = jsonObject1.getString("accessToken");
        String Url = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=" + accessToken1;
        RestTemplate restTemplate = RestTemplateUtils.getRestTemplate();
        HashMap map = new HashMap(1);
        map.put("code", code);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(Url, JSONObject.toJSONString(map
        ), String.class);
        String body = stringResponseEntity.getBody();
        if (stringResponseEntity.getStatusCodeValue() == 200) {
            JSONObject jsonObject = JSONObject.parseObject(body);
            if (!jsonObject.getString(errcCode).equals("0")) {
                weChatService.getAccessToken();
            }
            return jsonObject.toString();
        }
        return "http请求失败";
    }

    /**
     * 判断accessToken 是否过期
     *
     * @param accessToken
     * @return
     */
    public boolean IsExpireAccessToken(HashMap accessToken) {
        String expireTime = (String) accessToken.get("expireTime");
        //JSONObject jsonObject = JSONObject.parseObject(accessToken);
        //Long expiretime = jsonObject.getLong("expiretime");
        //Long expire = redisTemplate.getExpire("accessToken")
        long timeMillis = System.currentTimeMillis();
        //当前时间减token生成时间  > token有效时间  且  token 有效时间 <= 0   失效
        if ((timeMillis - Long.valueOf(expireTime)) / 1000 > 120) {
            return true;
        } else {
            return false;
        }
    }
}
