package liufeng.jdk.jvm.compile.enums;

/**
 * field 描述符
 */
public enum FieldDescriptor {

    BYTE("byte", "B"),
    CHAR("char", "C"),
    DOUBLE("double", "D"),
    FLOAT("float", "F"),
    INT("int", "I"),
    LONG("long", "J"),
    SHORT("short", "S"),
    BOOLEAN("boolean", "Z"),
    VOID("void", "V");
    /**
     * 描述符
     */
    private String value;
    /**
     * 对应的java描述
     */
    private String origin;

    FieldDescriptor(String origin, String value) {
        this.value = value;
        this.origin = origin;
    }


    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * 根据描述符获取对应的java代码
     */
    public static FieldDescriptor getByValue(String value) {
        for (FieldDescriptor fieldDescriptor:FieldDescriptor.values()){
            if (fieldDescriptor.value.equals(value)){
                return fieldDescriptor;
            }
        }
        return null;
    }
}
