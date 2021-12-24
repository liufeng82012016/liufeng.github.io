package com.my.liufeng.util;

public class ObjectUtil {
    /**
     * 判断对象是否为空，为空返回默认值
     */
    public static <T> T defaultValue(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }



    /**
     * 判断对象是否为空
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }
}
