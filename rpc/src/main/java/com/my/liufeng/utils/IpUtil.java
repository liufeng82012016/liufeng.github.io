package com.my.liufeng.utils;

import java.util.Objects;

/**
 * @Author Ailwyn
 * @Description: todo
 * @Date 2021/5/27 20:20
 */
public class IpUtil {
    private static final String NUMBER_REGEX = "^[0-9]*$";

    public static String[] splitAddress(String addr) {
        Objects.requireNonNull(addr);
        String[] addressInfo = addr.split(":");
        if (addressInfo.length != 2 || !NUMBER_REGEX.matches(addressInfo[1])) {
            throw new RuntimeException("Invalid address " + addr);
        }
        return addressInfo;
    }
}
