package liufeng.jdk.jvm.compile.strategy;

import liufeng.jdk.jvm.compile.enums.ClassForm;

/**
 * Class文件解析策略
 */
public abstract class Strategy {
    /**
     * 获取对应的class文件结构
     */
    protected ClassForm classForm;


    /**
     * 解析class文件
     *
     * @param bytes     class文件转16进制数组
     * @param index     解析开始的下标
     * @param classForm class结构
     * @return 返回读取内容的长度
     */
    public int compile(byte[] bytes, int index, ClassForm classForm) {
        this.classForm = classForm;
        return compile(bytes, index);
    }

    public abstract int compile(byte[] bytes, int index);


}
