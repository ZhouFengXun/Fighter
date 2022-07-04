package com.fanlan.fightertest.digdecimal;

import java.math.BigDecimal;

/**
 * BigDecimal(int);     // 创建一个具有参数所指定整数值的对象
 * BigDecimal(double);  // 创建一个具有参数所指定双精度值的对象
 * BigDecimal(long);    // 创建一个具有参数所指定长整数值的对象
 * BigDecimal(String);  // 创建一个具有参数所指定以字符串表示的数值的对象
 * 三、方法
 * bigDecimal1.add(bigDecimal2);           // bigDecimal1 加 bigDecimal2
 * bigDecimal1.subtract(bigDecimal2);      // bigDecimal1 减 bigDecimal2
 * bigDecimal1.multiply(bigDecimal2);      // bigDecimal1 乘以 bigDecimal2
 * bigDecimal1.divide(bigDecimal2);        // bigDecimal1 除以 bigDecimal2
 * bigDecimal.toString();                  // 将bigDecimal对象的数值转换成字符串
 * bigDecimal.doubleValue();               // 将bigDecimal对象中的值以双精度数返回
 * bigDecimal.floatValue();                // 将bigDecimal对象中的值以单精度数返回
 * bigDecimal.longValue();                 // 将bigDecimal对象中的值以长整数返回
 * bigDecimal.intValue();                  // 将bigDecimal对象中的值以整数返回
 */

public class Test {


    public static void main(String[] args) {


        BigDecimal bigDecimal = new BigDecimal(123);
        System.out.println(bigDecimal);
        //1~10
        BigDecimal zero = BigDecimal.ZERO;
        System.out.println(zero);

        BigDecimal bigDecimal1 = BigDecimal.valueOf(22.20);
        // +
        BigDecimal add = bigDecimal.add(zero);
        System.out.println(add);
        /**
         * / BigDecimal.setScale()方法用于格式化小数点
         * setScale(1); // 表示保留一位小数，默认用四舍五入方式
         * setScale(1, BigDecimal.ROUND_DOWN); // 直接删除多余的小数位，如2.35会变成2.3
         * setScale(1, BigDecimal.ROUND_UP); // 进位处理，2.35变成2.4
         * setScale(1, BigDecimal.ROUND_HALF_UP); // 四舍五入，2.35变成2.4
         * setScaler(1, BigDecimal.ROUND_HALF_DOWN); // 四舍五入，2.35变成2.3，如果是5则向下舍
         * setScaler(1, BigDecimal.ROUND_CEILING); // 接近正无穷大的舍入
         * setScaler(1, BigDecimal.ROUND_FLOOR); // 接近负无穷大的舍入，数字>0和ROUND_UP作用一样，数字<0和ROUND_DOWN作用一样
         * setScaler(1, BigDecimal.ROUND_HALF_EVEN); // 向最接近的数字舍入，如果与两个相邻数字的距离相等，则向相邻的偶数舍入。
         */

        // /  必须要设置保留位数，不让会报错
        BigDecimal divide = bigDecimal.divide(bigDecimal1,2,BigDecimal.ROUND_CEILING);

        /**
         * Non-terminating decimal expansion
        *         BigDecimal divide = bigDecimal.divide(bigDecimal1);
        *         BigDecimal bigDecimal2 = divide.setScale(4, BigDecimal.ROUND_CEILING);
        *         System.out.println(bigDecimal2);
         */

        System.out.println(divide);
        // -
        BigDecimal subtract = bigDecimal.subtract(bigDecimal1);
        System.out.println(subtract);
        // *
        double v = bigDecimal.multiply(bigDecimal1).doubleValue();
        System.out.println(v);

        BigDecimal divide1 = bigDecimal.divide(new BigDecimal(10), 0, BigDecimal.ROUND_CEILING).multiply(new BigDecimal(3));
        System.out.println(divide1);


        /**
         *科学计数法
         */

        BigDecimal bd = new BigDecimal("3.40256010353E11");
        String result = bd.toPlainString();
        System.out.println(result);  //340256010353

        /**
         * 注意不能使用equals方法来比较大小。
         *
         * compareTo比较结果为：-1、0、1
         * 如：a.a.compareTo(b)，结果为-1表示a小于b，为0表示a等于b 为1表示a大于b
         *
         * 使用BigDecimal的坏处是性能比double和float差，在处理庞大，复杂的运算时尤为明显，因根据实际需求决定使用哪种类型。
         */
        BigDecimal a = new BigDecimal("1");
        BigDecimal b = new BigDecimal("2");
        BigDecimal c = new BigDecimal("1");
        int result1 = a.compareTo(b);
        int result2 = a.compareTo(c);
        int result3 = b.compareTo(a);
        boolean equals = a.equals(b);
        System.out.println(equals);

        System.out.println(result1);  // -1
        System.out.println(result2);  // 0
        System.out.println(result3);  // 1
    }
}
