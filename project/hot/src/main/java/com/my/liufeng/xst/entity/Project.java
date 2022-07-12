package com.my.liufeng.xst.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目实体类
 */
public class Project {
    /**
     * 项目名
     */
    private String name;
    /**
     * 上一次更新时间
     */
    private long lastUpdateTime;

    /**
     * 类加载器
     */
    private ClassLoader classLoader;

    private Map<String, ControllerContainer> controllers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public Map<String, ControllerContainer> getControllers() {
        return controllers;
    }

    public void addController(ControllerContainer controller) {
        if (this.controllers == null) {
            this.controllers = new HashMap<>();
        }
        this.controllers.put(controller.getId(), controller);
    }

    private long updateTime;

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public void clearControllers() {
        this.controllers.clear();
    }

    @Override
    public String toString() {
        // todo 测试方法
        return "project{" +
                "name='" + name + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                ", classLoader=" + classLoader +
                ", playways=" + controllers +
                ", updateTime=" + updateTime +
                '}';
    }
}
