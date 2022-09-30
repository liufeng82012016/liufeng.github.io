package com.my.liufeng.sentinel.config;

import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * web应用需定义拦截器
 * sentinel 拦截器
 */
@Configuration
public class SentinelFilter {
    static {
        // 设置统一异常处理和url清洗
        WebCallbackManager.setUrlBlockHandler(new WebUrlBlockHandler());
        WebCallbackManager.setUrlCleaner(new WebUrlCleaner());
    }

    @Bean
    public FilterRegistrationBean sentinelFilterRegistration() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new CommonFilter());
        registration.addUrlPatterns("/*");
        registration.setName("sentinelFilter");
        registration.setOrder(1);
        return registration;
    }
}
