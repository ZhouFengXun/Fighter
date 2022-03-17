package com.fanlan.fighterdemo.utils;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * 判断一个集合中是否包含某个元素
 */
public class containUtils {

    private static ArrayList<String> partnerId = new ArrayList(Arrays.asList("123", "234"));

    public static void main(String[] args) {

        Boolean containElement = isContainElement("234");
        System.out.println(containElement);

        Boolean containList = isContainObjectUtils(partnerId, "234");
        System.out.println(containList);

        Boolean containCollectionUtils = iscontainsInstance(partnerId, "234");
        System.out.println(containCollectionUtils);
    }

    /**
     *  使用 list 集合自带的 方法  contains
     * @param str
     * @return
     */
    public static Boolean isContainElement(String str) {
        if (partnerId.contains(str)) {
            return true;
        }
        return false;
    }

    /**
     *  实现方式二( ObjectUtils )
     * @param collection
     * @param str
     * @return
     */
    public static Boolean isContainObjectUtils(Collection<?> collection, String str) {
        boolean contains = ObjectUtils.containsElement(collection.toArray(), str);
        return contains;
    }

    /**
     * 实现方式三（CollectionUtils）
     * @param collection
     * @param str
     * @return
     */
    public static Boolean iscontainsInstance(Collection<?> collection, String str) {
        boolean contains = CollectionUtils.containsInstance(collection, str);
        return contains;
    }
}
