package com.my.liufeng.util;

public class StringUtil {
    /**
     * 判断对象是否为空或者空字符串
     */
    public static boolean isBlank(String s) {
        return s == null || s.length() == 0;
    }

    /**
     * 是否其中一个为空
     */
    public static boolean isAnyBlank(String... strs) {
        for (String s : strs) {
            if (isBlank(s)) {
                return true;
            }
        }
        return false;
    }
}
