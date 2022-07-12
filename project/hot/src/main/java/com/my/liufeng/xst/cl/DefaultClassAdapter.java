package com.my.liufeng.xst.cl;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 适配器，将多种格式输入转换为统一的key-value格式
 */
@Component
public class DefaultClassAdapter implements ClassAdapter {
    @Override
    public Map<String, byte[]> loadLocalJar(String path) {
        throw new RuntimeException("invoke unimplement method");
    }

    @Override
    public Map<String, byte[]> loadInputStream(InputStream inputStream) {
        throw new RuntimeException("invoke unimplement method");
    }

    @Override
    public Map<String, byte[]> loadNetwork(String getUrl) {
        throw new RuntimeException("invoke unimplement method");
    }

    @Override
    public Map<String, byte[]> loadLocalDir(String folder) {
        // 包名写死
        File file = new File(folder);
        Map<String, byte[]> classMap = new HashMap<>();
        scanFolder(classMap, file, folder);
        return classMap;
    }

    /**
     * 扫描文件夹，解析class文件
     *
     * @param classMap 容器，解析得到的class文件放入此map
     * @param file     目标文件（需解析子目录）
     * @param folder   根目录，路径不包含在报名
     */
    private void scanFolder(Map<String, byte[]> classMap, File file, String folder) {
        print("parse file: " + file.getAbsolutePath() + "，name=" + file.getName());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File child : files) {
                scanFolder(classMap, child, folder);
            }
        } else {
            String name = file.getName();
            int index = name.lastIndexOf(".");
            if (index == -1) {
                print("ignore  file : " + name);
                return;
            }
            if (name.substring(index).equals(".class")) {
                print("add class file : " + file.getAbsolutePath());
                classMap.put(parsePackageName(file, folder), readClassFile(file));
            } else {
                print("ignore  file : " + name.substring(index));
            }
        }
    }

    /**
     * 将文件地址转为包名
     *
     * @param file   文件
     * @param folder 项目根目录
     * @return 包名
     */
    public String parsePackageName(File file, String folder) {
        // 替换项目路径
        String packageName = file.getAbsolutePath().replace(folder, "");
        if (packageName.startsWith(File.separator)) {
            packageName = packageName.substring(1);
        }
        // 替换类名
        if (packageName.endsWith(".class")) {
            packageName = packageName.substring(0, packageName.length() - 6);
        }
        // 将目录分割符替换为.
        packageName = packageName.replaceAll(File.separator, ".");
        print("path" + file.getAbsolutePath().replace(folder, "") + ",packageName: " + packageName);
        return packageName;
    }

    /**
     * 读取文件，放入byte[]
     *
     * @param file 文件
     * @return byte[]
     */
    private byte[] readClassFile(File file) {
        try {
            InputStream is = new FileInputStream(file);
            byte[] b = new byte[is.available()];
            is.read(b);
            return b;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 打印字符串
     *
     * @param args 多个字符串
     */
    private static void print(String... args) {
        // System.out.println(Arrays.toString(args));
    }
}
