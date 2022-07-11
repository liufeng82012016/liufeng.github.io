package com.my.liufeng.xst.cl;

import java.io.InputStream;
import java.util.Map;

/**
 * 适配器，将多种格式输入转换为统一的key-value格式
 */
public interface ClassAdapter {
    /**
     * 从本地文件加载jar包
     *
     * @param path 本地文件绝对路径
     * @return key=className，value=byte[],class二进制数据
     */
    Map<String, byte[]> loadLocalJar(String path);

    /**
     * 从输入流加载jar包
     *
     * @param inputStream 输入流
     * @return key=className，value=byte[],class二进制数据
     */
    Map<String, byte[]> loadInputStream(InputStream inputStream);

    /**
     * 从网络加载jar
     *
     * @param getUrl get请求地址，请求文件流
     * @return key=className，value=byte[],class二进制数据
     */
    Map<String, byte[]> loadNetwork(String getUrl);

    /**
     * 从本地class文件目录加载class文件目录
     *
     * @param folder 本地文件目录
     * @return key=className，value=byte[],class二进制数据
     */
    Map<String, byte[]> loadLocalDir(String folder);

}
