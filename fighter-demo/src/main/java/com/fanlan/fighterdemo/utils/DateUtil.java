package com.fanlan.fighterdemo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateUtil {
    private static String FORMAT_SHORT = "yyyyMMdd";
    private static String FORMAT_SHORT1 = "yyyymmdd";
    private static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    private static String FORMAT_LONG1 = "yyyyMMddHHmmss";

    /**
     * 获取订单当前月份
     *
     * @param date
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static int getMonth() {
        long timeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当月开始时间
     */
    public static Long getMonthStartTime() {
        Long currentTime = System.currentTimeMillis();
        String timeZone = "GMT+8:00";
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        calendar.setTimeInMillis(currentTime);
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取当月的结束时间戳
     */
    public static Long getMonthEndTime() {
        Long currentTime = System.currentTimeMillis();
        String timeZone = "GMT+8:00";
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        calendar.setTimeInMillis(currentTime);
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));// 获取当前月最后一天
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取本年的开始时间
     */
    public static Long getBeginDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        return getDayStartTime(cal.getTime());
    }

    /**
     * 获取本年的结束时间
     */
    public static Long getEndDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return getDayEndTime(cal.getTime());
    }

    /**
     * 获取某个日期的开始时间
     */
    public static Long getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取某个日期的结束时间
     */
    public static Long getDayEndTime(Date d) {

        Calendar calendar = Calendar.getInstance();

        if (null != d) calendar.setTime(d);

        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);

        calendar.set(Calendar.MILLISECOND, 999);

        return calendar.getTimeInMillis();

    }

    /**
     * 获取今年是哪一年
     */
    public static Integer getNowYear() {
        //Date date = new Date();
        long timeMillis = System.currentTimeMillis();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTimeInMillis(timeMillis);
        //gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }

    /**
     * 获取当月开始时间和结束时间
     *
     * @return
     */
    public static Map getMonthTime() {
        Long startTime = getMonthStartTime();
        Long endTime = getMonthEndTime();
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern(FORMAT_SHORT);
        String startTimeStr = ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime), ZoneId.systemDefault()));
        String endTimeStr = ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(endTime), ZoneId.systemDefault()));
        Map map = new HashMap();
        map.put("startTime", startTimeStr);
        map.put("endTime", endTimeStr);
        return map;
    }

    /**
     * 获取当月开始时间和结束时间
     *
     * @return
     */
    public static Map<String, String> getYearTime() {
        long startYear = getBeginDayOfYear();
        long endYear = getEndDayOfYear();
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern(FORMAT_SHORT);
        String startTimeStr = ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(startYear), ZoneId.systemDefault()));
        String endTimeStr = ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(endYear), ZoneId.systemDefault()));
        Map map = new HashMap();
        map.put("startTime", startTimeStr);
        map.put("endTime", endTimeStr);
        return map;
    }

    public static void main(String[] args) throws ParseException {

        System.out.println("===================================本月开始时间，结束时间==========================================");
//        long startTime=getMonthStartTime();
//        long endTime=getMonthEndTime();
//
//        DateTimeFormatter ftf = DateTimeFormatter.ofPattern(FORMAT_SHORT);
//        String startTimeStr = ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime), ZoneId.systemDefault()));
//        String endTimeStr = ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(endTime), ZoneId.systemDefault()));
//
//        System.out.println(startTimeStr);
//        System.out.println(endTimeStr);
        Map monthTime = getMonthTime();
        Object startTime = monthTime.get("startTime").toString();
        Object endTime = monthTime.get("endTime");
        System.out.println(startTime);
        System.out.println(endTime);


        System.out.println("===========================今年开始时间结束时间===============================");
//        long  startYear =getBeginDayOfYear();
//        long  endYear=getEndDayOfYear();
//        DateTimeFormatter ftf1 = DateTimeFormatter.ofPattern(FORMAT_SHORT);
//        String startYear1 = ftf1.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(startYear), ZoneId.systemDefault()));
//        String endYear1 = ftf1.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(endYear), ZoneId.systemDefault()));
//        System.out.println(startYear1);
//        System.out.println(endYear1);
        Map monthTime1 = getYearTime();
        Object startTime1 = monthTime1.get("startTime").toString();
        Object endTime1 = monthTime1.get("endTime");
        System.out.println(startTime1);
        System.out.println(endTime1);

        System.out.println("==================================================================================");
        int month = getMonth();
        System.out.println("当前月份:" + month);


        String ordOrpTime = "20211121091505";
        SimpleDateFormat sdfBefore = new SimpleDateFormat(FORMAT_LONG1);
        Date date = sdfBefore.parse(ordOrpTime);
        int Ordermonth = getMonth(date);
        System.out.println("获取月份：" + Ordermonth);
    }
}
