package liufeng.jdk.jvm.compile.enums;

import liufeng.jdk.jvm.compile.constants.Constants;
import liufeng.jdk.jvm.compile.strategy.AccessFlagStrategy;
import liufeng.jdk.jvm.compile.strategy.ClassNameStrategy;
import liufeng.jdk.jvm.compile.strategy.ConstantsStrategy;
import liufeng.jdk.jvm.compile.strategy.CountStrategy;
import liufeng.jdk.jvm.compile.strategy.InterfaceStrategy;
import liufeng.jdk.jvm.compile.strategy.MagicNumberStrategy;
import liufeng.jdk.jvm.compile.strategy.Strategy;
import liufeng.jdk.jvm.compile.strategy.VersionStrategy;

/**
 * class文件组成
 */
public enum ClassForm {
    /**
     * 魔数
     */
    MAGIC_NUMBER(4, new MagicNumberStrategy()),
    /**
     * 副版本号
     */
    MINOR_VERSION(2, new VersionStrategy()),
    /**
     * 主版本号
     */
    MAJOR_VERSION(2, new VersionStrategy()),
    /**
     * 常量池中常量的数量，从1开始，实际数量需要减1
     * 第0项常量空出来，表示"不引用任何一个常量池项目"
     */
    CONSTANT_POOL_COUNT(2, new CountStrategy()),
    /**
     * 常量池内容
     */
    CONSTANT_POOL(Constants.UNFIXED_LENGTH, new ConstantsStrategy()),
    /**
     * 类访问标志
     */
    ACCESS_FLAGS(2, new AccessFlagStrategy()),
    /**
     * 当前类
     */
    THIS_CLASS(2, new ClassNameStrategy()),
    /**
     * 父类
     */
    SUPER_CLASS(2, new ClassNameStrategy()),
    /**
     * 实现的接口数
     */
    INTERFACES_COUNT(2, new CountStrategy()),
    /**
     * 接口引用
     */
    INTERFACES(2, new InterfaceStrategy()),
    /**
     * 字段数量
     */
    FIELDS_COUNT(2, new CountStrategy()),
    /**
     * 字段数据
     */
    FIELDS(Constants.UNFIXED_LENGTH),
    METHODS_COUNT(2, new CountStrategy()),
    METHODS(Constants.UNFIXED_LENGTH),
    ATTRIBUTES_COUNT(2, new CountStrategy()),
    ATTRIBUTES(Constants.UNFIXED_LENGTH);

    ClassForm(int length) {
        this.length = length;
    }

    ClassForm(int length, Strategy strategy) {
        this.length = length;
        this.strategy = strategy;
    }

    /**
     * 占用字节数
     */
    private final int length;
    /**
     * 解析策略
     */
    private Strategy strategy;
    /**
     * 是否打印解析结果
     */
    private boolean print = true;

    /**
     * 如果当前结构是计数结构，将保存数目
     */
    private int counter;

    private Object value;

    public int getLength() {
        return length;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public boolean isPrint() {
        return print;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * 获取第一个结构
     */
    public static ClassForm first() {
        return ClassForm.values()[0];
    }

    /**
     * 获取下一个结构，如果已经是第一个，返回null
     */
    public ClassForm pre() {
        ClassForm[] values = ClassForm.values();
        if (this == values[0]) {
            return null;
        }
        for (int i = 0; i < values.length; i++) {
            if (this == values[i]) {
                return values[i - 1];
            }
        }
        throw new RuntimeException("无法查找下一个ClassForm");
    }

    /**
     * 获取下一个结构，如果已经是最后一个，返回null
     */
    public ClassForm next() {
        if (this == ClassForm.values()[ClassForm.values().length - 1]) {
            return null;
        }
        boolean current = false;
        for (ClassForm classForm : ClassForm.values()) {
            if (current) {
                return classForm;
            }
            if (this == classForm) {
                current = true;
            }
        }
        throw new RuntimeException("无法查找下一个ClassForm");
    }

}
