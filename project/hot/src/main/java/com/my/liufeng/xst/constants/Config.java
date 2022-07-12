package com.my.liufeng.xst.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class Config {
    @Value("${dir.exclude}")
    public String excludeDir;

    @PostConstruct
    public void print() {
        // todo 测试方法
        System.out.println(this);
    }

    @Override
    public String toString() {
        // todo 测试方法
        return "Config{" +
                "excludeDir='" + excludeDir + '\'' +
                '}';
    }

    public String getExcludeDir() {
        return excludeDir;
    }

    public void setExcludeDir(String excludeDir) {
        this.excludeDir = excludeDir;
    }
}
