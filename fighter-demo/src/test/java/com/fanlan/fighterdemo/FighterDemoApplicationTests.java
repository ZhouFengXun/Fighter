package com.fanlan.fighterdemo;


import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class FighterDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 是否包含
     */
    @Test
    public void show() {
        ArrayList<String> PartnerId = new ArrayList<>(Arrays.asList("14300709", "14301218"));
        System.out.println(CollectionUtils.containsInstance(PartnerId, "14301218"));
        if (!PartnerId.contains("14301218")) {
            System.out.println("不包含");
        } else {
            System.out.println("包含");
        }
    }

    @Test
    public void show1() {
        // 创建一个list集合对象
        List<String> list = new ArrayList<>();
        // 往list中存放数据
        list.add("Peking");
        list.add("NewYork");
        list.add("HongKong");
        list.add("Bangkok");
        list.add("Seoul");
        // 集合生成流
        List<Map<String, String>> cityName = list.stream().map(city -> {
            // 创建一个map集合对象
            Map<String, String> map = new HashMap<>(16);
            // 往map中存放数据
            map.put("city", city.toUpperCase());
            System.out.println(map);
            // 返回map集合中的数据
            return map;
            // 收集流处理后的数据转为list数据结构
        }).collect(Collectors.toList());
        // 打印测试效果
        System.out.println(cityName);


        // 以下是真实数据库查出的数据对该方式的应用
//        List<DictDetail> dictList = dictDetailMapper.selectList(wrapper);
//        //stream组装字典
//        List<Map<String,String>> collect = dictList.stream().map(e -> {
//            Map<String,String> map = new HashMap<>(16);
//            map.put("label", e.getLabel());
//            map.put("value", e.getValue());
//            return map;
//        }).collect(Collectors.toList());
//        return collect;
    }

    @Test
    public void show3() {
        int i = 1;
        String str = null;
        /**
         * 创建一个空的optional对象
         */
        Optional<Object> empty = Optional.empty();
        /**
         * 如果存在值，则使用该值执行给定的操作，否则不执行任何操作
         */
        empty.ifPresent(System.out::println);//这里什么都没返回

        /**
         * 允许值为空，不会报错，返回Optional.empty
         */
        Optional<String> str1 = Optional.ofNullable(str);
        System.out.println(str1);
        /**
         * 判断值存在返回该值，不存在返回其他
         */
        String s = str1.orElse("sss");
        System.out.println(s);
        /**
         * of  不允许值为空,为空会报 null指针异常
         */
        Optional<Integer> i1 = Optional.of(i);
        i1.ifPresent(System.out::println);
        /**
         * 返回该值
         */
        Integer integer = i1.get();
        System.out.println(integer);
        /**
         * 判断值是否为空
         */
        boolean present = i1.isPresent();
        System.out.println(present);
        boolean equals = i1.equals(i1);
        System.out.println(equals);

//        Optional<MyUser> myUser2 = Optional.of(new MyUser("阿飞", "123456"));
//
//        // filter传入一个lambda，lambda返回值为boolean；true:不做任何改变，false:返回一个空的optional；
//        Optional<MyUser> myUser3 = myUser2.filter(myUser -> myUser.getName().equals("ss"));
//
//        System.out.println(myUser3);
    }


    @Test
    public void show4() {
        int age = 1;
        int age1 = 2;

        if (age1 > 1) {
            System.out.println("大于1");
        } else if (age == 1) {
            System.out.println("等于1");
        } else if (age > 1 && age == 1) {
            System.out.println("都不是");
        }

    }

    @Test
    public void show5() {

        ArrayList<String> partnerIdList = new ArrayList<>(Arrays.asList("14300709", "14301218"));
        String orderMonth = "11";
        String localMonth = "1";
        String subOrderStatus = "0113";
        if (!partnerIdList.contains("1430q1218")) {
            System.out.println("该商户不支持发放积分");
        } else if (orderMonth != localMonth && !subOrderStatus.equals("013")) {
            System.out.println("抱歉，您已超过领取期限");
        } else if (subOrderStatus.equals("013")) {
            System.out.println("未查询到中奖信息，请返回游戏联系腾讯客服");
        } else {
            System.out.println("发66积分");
        }
    }

    @Test
    public void print(){
        String userId="ssa";
        boolean blank = StringUtils.isBlank(userId);
        System.out.println(blank);
        boolean notBlank = StringUtils.isNotBlank(userId);
        System.out.println(notBlank);
    }
}
