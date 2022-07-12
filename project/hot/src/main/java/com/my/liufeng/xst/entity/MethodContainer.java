package com.my.liufeng.xst.entity;

import java.lang.reflect.Method;

/**
 * 自定义方法容器
 */
public class MethodContainer {
    private String id;
    private Method method;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
