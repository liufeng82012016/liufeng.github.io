package com.my.liufeng.rocketmq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = {"com.my.liufeng.rocketmq.mapper"})
@SpringBootApplication
public class RocketmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketmqApplication.class, args);
    }

}
