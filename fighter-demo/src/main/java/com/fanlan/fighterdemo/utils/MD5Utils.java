package com.fanlan.fighterdemo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    /**
     * 加密
     * @param context
     */
    public static String encrypByMd5(String context) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // update处理
            md.update(context.getBytes());
            // 调用该方法完成计算
            byte [] encryContext = md.digest();
            int i;
            StringBuilder buf = new StringBuilder("");
            //做相应的转化（十六进制）
            for (byte b : encryContext) {
                i = b;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return  null;
        }
    }

    public static void main(String[] args) {
        String str = MD5Utils.encrypByMd5("123");
        System.out.println(str);
    }


}
