package com.fanlan.itrestry.controller;


import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
public class helloController {


    @PostMapping("hello")
    @Retryable(value = ConnectTimeoutException.class,maxAttempts = 2,backoff = @Backoff(delay = 2000,multiplier = 1.5))
    public int Hello(int code) throws Exception {

        System.out.println("retryServiceTest被调用,时间："+ LocalTime.now()+"code:"+code);
        if (code==0){
            throw new ConnectTimeoutException("请求参数为0，出现异常");

        }
        System.out.println("retryServiceTest被调用,情况对头了！");
        return 200;
    }

    @Recover
    public int recover(ConnectTimeoutException e){ //返回的类型要和上面保持一致，且@Retryable只能写在直接调用的方法上，写在内部调用的方法上不生效
        System.out.println("重试次数结束后还有异常，回调方法开始执行");
        //可调用其余的方法
        return 400;
    }

}
