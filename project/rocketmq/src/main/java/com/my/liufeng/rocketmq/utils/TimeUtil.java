package com.my.liufeng.rocketmq.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author lyh
 */
public class TimeUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static synchronized String getTime() {
        return sdf.format(new Date());
    }

}
