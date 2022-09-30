package com.my.liufeng.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

/**
 * @author liufeng
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {
    Random random = new Random();

    /**
     * 测试快速失败
     */
    @SentinelResource("HelloWorld")
    @GetMapping("/time")
    public Object time() {
        return System.currentTimeMillis();
    }

    /**
     * 测试关联失败
     */
    @GetMapping("/date")
    public Object date() {
        return new Date();
    }

    /**
     * 测试异常比例和异常数降级
     */
    @GetMapping("/ex")
    public Object ex() {
        if (random.nextInt(3) == 0) {
            throw new RuntimeException();
        }
        return new Date();
    }

    /**
     * 测试慢调用
     */
    @GetMapping("/slow")
    public Object slow() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    @GetMapping("/hot")
    // 不加，热点规则不生效，未测试，sentinel版本过低
    @SentinelResource("hot")
    public Object hot(String name, String age) {
        return name + age;
    }

    @GetMapping("/auth")
    public Object auth(String name, String age,String origin) {
        return name + age;
    }
}
