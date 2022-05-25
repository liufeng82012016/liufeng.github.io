package liufeng.jdk.jvm.compile.enums;

import liufeng.jdk.jvm.compile.constants.Constants;


/**
 * 类访问标识 u2，最多32种标识
 */
public enum AccessFlags {
    /**
     * public 0000 0000 0000 0001
     */
    ACC_PUBLIC(0x0001, "public ", Constants.LOCATION_ALL),
    /**
     * private 0000 0000 0000 0010
     */
    ACC_PRIVATE(0x0002, "private ", Constants.LOCATION_M_F),
    /**
     * protected 0000 0000 0000 0100
     */
    ACC_PROTECTED(0x0004, "protected ", Constants.LOCATION_M_F),
    /**
     * static 0000 0000 0000 1000
     */
    ACC_STATIC(0x0008, "static ", Constants.LOCATION_M_F),
    /**
     * final 0000 0000 0001 0000
     */
    ACC_FINAL(0x0010, "final ", Constants.LOCATION_ALL),
    /**
     * jdk 1.2之后编译，这个值被置为1
     * super 0000 0000 0010 0000
     */
    ACC_SUPER(0x0020, Constants.LOCATION_CLASS),
    /**
     * volatile 0000 0000 0100 0000
     */
    ACC_VOLATILE(0x0040, "volatile", Constants.LOCATION_FIELD),
    /**
     * transient 0000 0000 1000 0000
     */
    ACC_TRANSIENT(0x0080, "transient", Constants.LOCATION_FIELD),
    /**
     * interface 0000 0010 0000 0000
     */
    ACC_INTERFACE(0x0200, "interface ", true, Constants.LOCATION_CLASS),
    /**
     * abstract 0000 0100 0000 0000
     */
    ACC_ABSTRACT(0x0400, "abstract ", Constants.LOCATION_M_C),
    /**
     * synthetic 0001 0000 0000 0000
     * 表示字段由编译器自动产生
     */
    ACC_SYNTHETIC(0x1000, Constants.LOCATION_ALL),
    /**
     * annotation 0010 0000 0000 0000
     */
    ACC_ANNOTATION(0x2000, "@interface ", true, Constants.LOCATION_CLASS),
    /**
     * enum 0010 0000 0000 0000
     */
    ACC_ENUM(0x4000, "enum ", true, Constants.LOCATION_C_F);
    /**
     * 标记对应的值
     */
    private int value;
    /**
     * 标记对应的java关键字字符串
     */
    private String javaKey;

    /**
     * 权重，决定各关键字的顺序
     */
    private int weight;
    /**
     * 是否是特殊类（如接口、枚举、注解）
     */
    private boolean clazz;

    private int location;

    AccessFlags() {
    }

    AccessFlags(int value) {
        this.value = value;
    }

    public String getJavaKey() {
        return javaKey;
    }

    AccessFlags(int value, String javaKey) {
        this.value = value;
        this.javaKey = javaKey;
    }

    AccessFlags(int value, int location) {
        this.value = value;
        this.location = location;
    }

    AccessFlags(int value, String javaKey, int location) {
        this.value = value;
        this.javaKey = javaKey;
        this.location = location;
    }

    AccessFlags(int value, String javaKey, boolean clazz, int location) {
        this.value = value;
        this.javaKey = javaKey;
        this.clazz = clazz;
        this.location = location;
    }

    AccessFlags(int value, String javaKey, boolean clazz) {
        this.value = value;
        this.javaKey = javaKey;
        this.clazz = clazz;
    }


    public boolean isClazz() {
        return clazz;
    }

    public int getValue() {
        return value;
    }

    public int getLocation() {
        return location;
    }
}
