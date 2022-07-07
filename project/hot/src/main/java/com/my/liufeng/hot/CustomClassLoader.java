package com.my.liufeng.hot;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CustomClassLoader extends ClassLoader {

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
            InputStream is = new FileInputStream("/Users/liufeng/IdeaProjects/liufeng82012016.github.io/project/hot/target/classes/com/my/liufeng/master/HelloWorld.class");
            System.out.println("file " + fileName + ", input stream:" + is);
            byte[] b = new byte[is.available()];
            is.read(b);
            return defineClass(name, b, 0, b.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
    }


}
