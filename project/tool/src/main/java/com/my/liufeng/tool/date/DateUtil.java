package com.my.liufeng.tool.date;


import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
    public static final SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static Date getDate(String str) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("date", str);
        // fast json 内置了很多日期格式的处理，这里直接使用它的api来处理好了;但是，遇到它不能识别的，会抛出异常
        return jsonObject.getDate("date");
    }


    public static String format(Date date, SimpleDateFormat sdf) {
        synchronized (sdf) {
            return sdf.format(date);
        }
    }

    public static String format(Date date) {
        return format(date, yyyyMMddHHmmss);
    }

    private static String format(Long timeMills) {
        return format(new Date(timeMills), yyyyMMddHHmmss);
    }

    @Test
    public void parse1() {
        String[] arr = new String[]{"2021-9-13", "2021-9-3 15:00:00", "" + System.currentTimeMillis()};
        for (String str : arr) {
            System.out.println(format(getDate(str)));
        }
    }
}
