package com.my.liufeng.util;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateUtil {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String formatYmd(Long timeMills) {
        Objects.requireNonNull(timeMills);
        Instant instant = Instant.ofEpochMilli(timeMills);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return dateTime.format(dateTimeFormatter);
    }


}
