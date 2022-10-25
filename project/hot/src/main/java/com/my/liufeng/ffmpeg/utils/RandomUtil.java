package com.my.liufeng.ffmpeg.utils;

import java.util.Arrays;
import java.util.Random;

public class RandomUtil {
    private static Random random = new Random();
    private static char[] chars = new char[52];

    static {
        for (int i = 0; i < 52; i += 2) {
            chars[i] = (char) (65 + i/2);
            chars[i + 1] = (char) (chars[i] + 32);
        }
        System.out.println(Arrays.toString(chars));
    }

    public static String randomStr(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(chars[random.nextInt(chars.length)]);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(randomStr(16));
    }
}
