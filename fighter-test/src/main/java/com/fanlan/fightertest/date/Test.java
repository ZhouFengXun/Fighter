package com.fanlan.fightertest.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Test {

    public static void main(String[] args) {

        //获取当前时间
        LocalDateTime now = LocalDateTime.now();
        //前一天
        LocalDateTime localDateTime = now.plusDays(-1);
        //
        LocalDateTime localDateTime1 = localDateTime.plusMonths(-1);
        DateTimeFormatter currentDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format1 = now.format(currentDate);
        String format = localDateTime.format(currentDate);
        String format2 = localDateTime1.format(currentDate);
        System.out.println(format);
        System.out.println(format1);
        System.out.println(format2);

        LocalDate localDate = LocalDate.now(); //获取今天的日期
        LocalDate yesterday = localDate.plusDays(-1); //前一天日期是今天减1

        LocalDateTime of = LocalDateTime.of(yesterday, LocalTime.MIN);
        System.out.println(of);

        LocalDateTime endTime = LocalDateTime.of(yesterday, LocalTime.MAX);
        System.out.println(endTime);

        String startDate = LocalDateTime.of(LocalDate.now().plusDays(-1), LocalTime.MIN).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String endDate = LocalDateTime.of(LocalDate.now().plusDays(-1), LocalTime.MAX).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.println(startDate);
        System.out.println(endDate);

    }
}
