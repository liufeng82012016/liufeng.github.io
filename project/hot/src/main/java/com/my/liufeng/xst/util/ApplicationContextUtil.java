package com.my.liufeng.xst.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil {

    private static ApplicationContext applicationContext;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextUtil.applicationContext = applicationContext;
        System.out.println("applicationContext inject");
    }

    /**
     * 根据class对象获取实例
     */
    public static <T> T get(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }


}
