package com.my.liufeng.gateway;

import com.my.liufeng.gateway.config.DynamicConfigTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(GatewayApplication.class, args);
        while (true) {
            //当动态配置刷新时，会更新到 Enviroment中，因此这里每隔一秒中从Enviroment中获取配置
            String userName = applicationContext.getEnvironment().getProperty("spring.datasource.username");
            System.err.println("user name :" + userName + "; ");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    DynamicConfigTest dynamicConfigTest;

    @Resource
    public void setDynamicConfigTest(DynamicConfigTest dynamicConfigTest) {
        this.dynamicConfigTest = dynamicConfigTest;
        log.info("inject dynamicConfigTest:{}", dynamicConfigTest);
    }

    @PostConstruct
    public void print() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    log.info("print value is :{} dynamicConfigTest:{}", dynamicConfigTest.getUsername(), dynamicConfigTest);
                    try {
                        Thread.sleep(5000L);
                    } catch (InterruptedException e) {
                        //
                    }
                }
            }
        })
                .start();
    }

}
