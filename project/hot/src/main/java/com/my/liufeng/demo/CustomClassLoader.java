package com.my.liufeng.demo;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 自定义classLoader
 */
public class CustomClassLoader extends ClassLoader {
    private static int index = 0;
    private static String[] paths = new String[]{
//            "file:///Users/liufeng/Documents/clazz/HelloWorld.class",
//            "file:///Users/liufeng/Documents/clazz/2/HelloWorld.class"
            "/Users/liufeng/Documents/clazz/HelloWorld.class",
            "/Users/liufeng/Documents/clazz/2/HelloWorld.class"
    };

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
            InputStream is = new FileInputStream(paths[index]);
            index++;
            System.out.println("file " + fileName + ", input stream:" + is.available());
            byte[] b = new byte[is.available()];
            is.read(b);
//            byte[] b = Files.readAllBytes(Paths.get(new URI(paths[index])));
//            index++;
//            System.out.println(name);
            return defineClass(name, b, 0, b.length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException(name);
        }
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
    }
}
