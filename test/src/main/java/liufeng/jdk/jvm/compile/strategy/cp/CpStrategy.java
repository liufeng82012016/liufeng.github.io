package liufeng.jdk.jvm.compile.strategy.cp;

import liufeng.jdk.jvm.compile.enums.ConstantsForm;

/**
 * 常量池结构体解析
 */
public abstract class CpStrategy {
    /**
     * tag固定占用1字节
     */
    public static final int TAG_LENGTH = 1;
    /**
     * 具体结构枚举类型
     */
    protected ConstantsForm constantsForm;


    /**
     * 解析class文件
     *
     * @param bytes         class文件转16进制数组
     * @param index         解析开始的下标
     * @param constantsForm 常量池结构
     * @return 返回读取内容的长度（字节数）
     */
    public int compile(byte[] bytes, int index, ConstantsForm constantsForm) {
        this.constantsForm = constantsForm;
        // 因为tag占用了一个字节，这里需要加1
        return compile(bytes, index + TAG_LENGTH) + TAG_LENGTH;
    }

    /**
     * @return 解析内容占用的字节数
     */
    public abstract int compile(byte[] bytes, int index);
}
