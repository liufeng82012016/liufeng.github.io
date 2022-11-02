//package com.my.liufeng.es.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//
//@Slf4j
//@Configuration
//public class LoggerConfig {
//    public LoggerConfig() {
//        log.info(" LoggerConfig init.");
//
//    }
//
//    @PostConstruct
//    public void log() {
//        new Thread(() -> {
//            while (true) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    //
//                }
//                log.info("today is Tuesday.");
//            }
//        }).start();
//    }
//}
