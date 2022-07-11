package com.my.liufeng.demo;

import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * 测试步骤，启动test方法，调用v1版本，然后修改HelloWorld代码，编译成class文件，重新加载并调用
 */
public class LoadTest1 {

    public static void main(String[] args) throws Exception {
        new LoadTest1().loadHelloWorld();
    }

    public void loadHelloWorld() throws Exception {
        Class clazz1 = loadHelloWorldClass();

        System.gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            //
        }
        // 单元测试无法输入
        Scanner scanner = new Scanner(System.in);
        System.out.println("input");
        while (scanner.hasNext()) {
            String next = scanner.next();
            if ("exit".equals(next)) {
                System.out.println("exit");
                break;
            }
            if ("reset".equals(next)) {
                System.out.println("reset");

                Class clazz2 = loadHelloWorldClass();
                System.out.println(clazz2 == clazz1);
            }
        }
    }

    private Class loadHelloWorldClass() {
        CustomClassLoader customClassLoader = new CustomClassLoader();
        try {
            Class<?> clazz = customClassLoader.findClass("com.my.liufeng.master.HelloWorld");
            Object o = clazz.newInstance();
            Method say = clazz.getMethod("say");
            say.invoke(o);
            return clazz;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
