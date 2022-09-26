package com.fanlan.fightertest.singleton;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
//        Singleton instance = Singleton.INSTANCE;
//        instance.businessMethod();
//
//
//        Singleton1 singleton1 = Singleton1.getSingleton1();
//        System.out.println(singleton1);


        String code = "{\n" +
                "\"success\": true,\n" +
                "\"resultCode\": \"000000\",\n" +
                "\"resultMessage\": \"操作成功\",\n" +
                "\"result\": {\n" +
                "\t\t\"allegeCode\": \"TS201909241911161234\"\n" +
                "}\n" +
                "}";


        String code1="{\"success\":true,\"resultCode\":\"000000\",\"resultMessage\":\"操作成功\",\"result\":\"SH202208251501305200\"}";
//        if ("01".equals(code) || "02".equals(code)) {
//            System.out.println(code);
//        }

        JSONObject jsonObject1 = JSONObject.parseObject(code);
        System.out.println(jsonObject1.get("result"));
        JSONObject jsonObject = JSONObject.parseObject(jsonObject1.getString("result"));
        System.out.println(jsonObject);


        List list = new ArrayList();
        list.add(1);
        System.out.println(list);


        boolean equals = ObjectUtils.equals("1", "1");
        System.out.println(equals);
        boolean equals1 = StringUtils.equals("1", "1");
        System.out.println(equals1);


        String verifyCode = String.valueOf(RandomUtils.nextInt(100000, 999999));
        System.out.println(verifyCode);


        Random random = new Random();
        int i = random.nextInt(999999-100000);
        System.out.println(i);
        System.out.println(i+100000);


    }
}
