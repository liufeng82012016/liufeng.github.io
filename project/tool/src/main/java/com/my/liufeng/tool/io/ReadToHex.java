package com.my.liufeng.tool.io;


import org.junit.Test;

import java.io.*;

public class ReadToHex {

    /**
     * 读取文件，将文件转换为16进制
     *
     * @param path 文件路径
     * @throws IOException
     */
    public static void readToHex(String path) throws IOException {
        File file = new File(path);
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            // 每次读取1024字节
            byte[] bytes = new byte[1024];
            int length;
            while ((length = inputStream.read(bytes)) != -1) {
                for (int i = 0; i < length; i++) {
                    String str = Integer.toHexString(bytes[i] & 0xff);
                    System.out.print(str + " ");
                }
                // 换行
                System.out.println();
            }
        }
    }

    @Test
    public void test() {
        // javac ReadToHex.java
        String path = "/Users/liufeng/IdeaProjects/liufeng82012016.github.io/project/hot/target/classes/com/my/liufeng/master/HelloWorld.class";
        try {
            readToHex(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
