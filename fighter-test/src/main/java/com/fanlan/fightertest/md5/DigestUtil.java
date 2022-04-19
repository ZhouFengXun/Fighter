package com.fanlan.fightertest.md5;


import org.springframework.util.DigestUtils;

public class DigestUtil extends DigestUtils {

    /**
     * md5加盐加密
     *
     * @param salt
     * @param password
     * @return
     */
    public static String encryptPassword(String salt, String password) {
        String format = String.format(salt, password);
        System.out.println(format);
        return DigestUtil.md5DigestAsHex(format.getBytes());
    }

    public static void main(String[] args) {
        String s = encryptPassword("smart_%s_admin", "123456");
        System.out.println(s);
    }
}
