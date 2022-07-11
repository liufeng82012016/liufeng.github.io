package com.my.liufeng.xst.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目实体类
 */
public class Projectx {
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

    private Map<String, Playway> playways;

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

    public Map<String, Playway> getPlayways() {
        return playways;
    }

    public void addPlayways(Playway playway) {
        if (this.playways == null) {
            this.playways = new HashMap<>();
            this.playways.put(playway.getId(), playway);
        }
    }

    private long updateTime;

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public void clearPlayways() {
        this.playways.clear();
    }

    @Override
    public String toString() {
        return "Projectx{" +
                "name='" + name + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                ", classLoader=" + classLoader +
                ", playways=" + playways +
                ", updateTime=" + updateTime +
                '}';
    }
}
