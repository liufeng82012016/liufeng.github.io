package com.my.liufeng.sentinel.test;

import cn.hutool.http.HttpUtil;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class SentinelTest {
    @Test
    public void fast() throws Exception {
        for (int i = 0; i < 100; i++) {
            String s = HttpUtil.get("localhost:8080/resource/time");
            System.out.println(s);
        }
    }

    @Test
    public void relation() throws Exception {
        for (int i = 0; i < 100; i++) {
            String s = HttpUtil.get("localhost:8080/resource/time");
            System.out.println(s);
        }
        for (int i = 0; i < 100; i++) {
            String s = HttpUtil.get("localhost:8080/resource/date");
            System.out.println(s);
        }
    }

    @Test
    public void exception() throws Exception {
        for (int i = 0; i < 100; i++) {
            String s = HttpUtil.get("localhost:8080/resource/ex");
            System.out.println(s);
        }
    }

    @Test
    public void slow() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                String s = HttpUtil.get("localhost:8080/resource/slow");
                System.out.println(s);
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
    }

    @Test
    public void auth() throws Exception {
        for (int i = 0; i < 100; i++) {
            String s = HttpUtil.get("localhost:8080/resource/auth?origin=ABC" + i);
            System.out.println(s);
        }
    }
}
