package com.fanlan.fightertest.String;

import org.apache.commons.lang3.StringUtils;

public class string {

    public static String getUril(String Url) {
        if (StringUtils.isNotBlank(Url)) {
            StringBuilder stringBuilder = new StringBuilder();
            String substring = Url.substring(Url.lastIndexOf("."));
            return stringBuilder.append(substring).toString();
        }
        return Url;
    }


    public static void main(String[] args) {
        String url="http://111.0.33.46/asset/pic/ware/5ecdd0/c/5ecdd0c3dd942b05d8b551a5.jpg";
        String uril = getUril(url);
        System.out.println(uril);
    }
}
