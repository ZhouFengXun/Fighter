package com.fanlan.fighterredis.service.impl;

import com.fanlan.fighterredis.common.vo.Result;
import com.fanlan.fighterredis.service.TokenService;
import com.fanlan.fighterredis.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedisUtil redisService;

    String  RedisKeyPrefix ="REDISKEY_PREFIX:";

    /**
     * 创建token
     *
     * @return
     */
    @Override
    public Result createToken() {
        String str = UUID.randomUUID().toString().replace("-", "");
        StringBuilder token = new StringBuilder();
        try {
            token.append(RedisKeyPrefix).append(str);
            redisService.setEx(token.toString(), token.toString(), 10000L);
            boolean empty = StringUtils.isEmpty(token.toString());
            if (!empty) {
                Result result=new Result();
                result.setResultCode(200);
                result.setResultMessage("操作成功");
                result.setResultJson(token.toString());
                return result;
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        Result result=new Result();
        result.setResultCode(500);
        result.setResultMessage("操作失败");
        return result;
    }

    /**
     * 检验token
     *
     * @param request
     * @return
     */
    @Override
    public boolean checkToken(HttpServletRequest request) throws Exception {

        String token = request.getHeader(RedisKeyPrefix);
        System.out.println(token);
        if (StringUtils.isEmpty(token)) {// header中不存在token
            token = request.getParameter(RedisKeyPrefix);
            if (StringUtils.isEmpty(token)) {// parameter中也不存在token
                throw new Exception("请求头和参数中token 不存在");
            }
        }

        if (!redisService.exists(token)) {
            throw new Exception("redis 不存在,重复调用 ");
        }

        boolean remove = redisService.remove(token);
        if (!remove) {
            throw new Exception("第一次调用接口成功，之后删除token");
        }
        return true;
    }

}