package com.my.liufeng.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DynamicConfigTest implements InitializingBean {
//    @Value("${spring.datasource.username}")
    private String username;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("username value is :{} this:{}", username,this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @PostConstruct
    public void print() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    log.info("print value is :{} this:{}",getUsername(),this);
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
