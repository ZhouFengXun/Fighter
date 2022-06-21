package com.fanlan.fightertest.stream;

import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {
        //show();
        //show1();
        show2();
    }

    public static void show(){
        List<User> list32 = new ArrayList<User>();
        for(int i=1;i<=10;i++){
            list32.add(new User(i,"张三"+i));
        }
        System.out.println("转换之前的数据:" + list32);// 转换之前的数据:[1, 2, 3]
        List<String> collect = list32.stream().map(user -> user.getName()).collect(Collectors.toList());
        System.out.println("转换之后的数据:" + collect); // [1, 2, 3]
    }

    public static void show1(){
        ArrayList<String> stringsList = new ArrayList<>(Arrays.asList("1","2","3"));
        System.out.println(stringsList);
        List<Integer> collect = stringsList.stream().map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(collect);
        ArrayList<String> stringsList1 = new ArrayList<>(Arrays.asList("a","b","c"));
        System.out.println(stringsList1);
        List<String> c = stringsList1.stream().map(String::toUpperCase).distinct().filter(n -> !n.equals("C")).collect(Collectors.toList());
        System.out.println(c);
    }
    public static void show2(){
        /**
         * {
         * "resultCode": 200,
         * "resultMessage": "操作成功",
         * "resultJson": [
         * {
         * "sequenceNum": 1,
         * "categoryLevel": 1,
         * "carousePicList": [],
         * "thirdCategoryList": [],
         * "categoryName": "茶水",
         * "categoryId": 2000000000000035
         * },
         * {},
         * {},
         * {}
         * ]
         * }
         */
        JSONObject map = new JSONObject();
        map.put("resultCode",200);
        map.put("resultMessage","请求成功");
        ArrayList<Object> resultJson = new ArrayList<>();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sequenceNum",1);
        hashMap.put("categoryLevel",1);
        hashMap.put("carousePicList",1);
        hashMap.put("categoryName","茶水");
        hashMap.put("categoryId","2000000000000035");
        resultJson.add(hashMap);
        map.put("resultJson",resultJson);
        System.out.println(map);
    }
}
