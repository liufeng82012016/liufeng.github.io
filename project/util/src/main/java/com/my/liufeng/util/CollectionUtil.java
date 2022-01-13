package com.my.liufeng.util;

import java.util.Collection;

public class CollectionUtil {
    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

    /**
     * 判断数组是否为空
     */
    public static <T> boolean isEmpty(T[] args) {
        return args == null || args.length == 0;
    }

    /**
     * 判断集合是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }
}
