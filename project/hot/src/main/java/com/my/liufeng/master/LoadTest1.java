package com.my.liufeng.master;

import com.my.liufeng.hot.CustomClassLoader;

import java.util.Scanner;

/**
 * 测试步骤，启动test方法，调用v1版本，然后修改HelloWorld代码，编译成class文件，重新加载并调用
 */
public class LoadTest1 {

    public static void main(String[] args) {
        new LoadTest1().loadHelloWorld();
    }

    public void loadHelloWorld() {
        loadHelloWorldClass();
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.say();

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

                loadHelloWorldClass();
                HelloWorld helloWorld2 = new HelloWorld();
                helloWorld2.say();
            }
        }
    }

    private void loadHelloWorldClass() {
        CustomClassLoader customClassLoader = new CustomClassLoader();
        try {
            customClassLoader.findClass("com.my.liufeng.hot.test.HelloWorld");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
