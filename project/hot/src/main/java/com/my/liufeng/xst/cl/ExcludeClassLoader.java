package com.my.liufeng.xst.cl;

import java.util.Map;

/**
 * 独占式 classLoader
 */
public class ExcludeClassLoader extends ClassLoader {
    private Map<String, byte[]> classMap;

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        if (classMap == null || classMap.isEmpty()) {
            throw new ClassNotFoundException();
        }
        try {
            // 从map解析数据
            byte[] b = classMap.remove(name);
            if (b == null) {
                throw new ClassNotFoundException();
            }
            System.out.println("find class: " + name);
            return defineClass(name, b, 0, b.length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException(name);
        }
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        // todo checkName
        // 此方法不建议重写
        // 这里是为了打破双亲委派规则，自定义加载那些类
        System.out.println("load class: " + name);
        if (loadCurrentClass(name)) {
            return super.loadClass(name, resolve);
        }
        return findClass(name);
    }

    /**
     * 设置classMap
     */
    public void setClassMap(Map<String, byte[]> classMap) {
        if (classMap == null || classMap.isEmpty()) {
            throw new RuntimeException("forbidden empty map as a parameter");
        }
        if (this.classMap != null) {
            throw new IllegalArgumentException("just init once");
        }
        this.classMap = classMap;
    }

    /**
     * 根据类名，判断是否加载此类
     * @param name 类名
     * @return
     */
    private boolean loadCurrentClass(String name) {
        return name.startsWith("java") || !classMap.containsKey(name);
    }
}
