package com.fanlan.fightertest;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FighterTestApplicationTests {




    @Test
    private void print(){
        JSONObject jsonObject = new JSONObject();
        String subOrderPoints = jsonObject.getString("subOrderPoints");//商场积分
        String subBossPoints = jsonObject.getString("subBossPoints");//和积分
        String payIntegral=null;
        if (StringUtils.isNotBlank(subOrderPoints)&& StringUtils.isNotBlank(subBossPoints)) {
            payIntegral = jsonObject.getString("subOrderPoints");//用户支付积分值
        }else {
            payIntegral = jsonObject.getString("subBossPoints");//用户支付积分值
        }
        System.out.println(payIntegral);

        System.out.println("ssd");
    }

    @Test
    public void show(){

    }
}
