package liufeng.jdk.jvm.compile.constants;

public interface Constants {
    // 魔数
    String MAGIC_NUMBER = "cafebabe";

    /**
     * 可变长结构
     */
    int UNFIXED_LENGTH = -1;

    /**
     * 通用标志
     */
    int LOCATION_ALL = 7;
    /**
     * 类专用
     */
    int LOCATION_CLASS = 1;
    /**
     * field专用
     */
    int LOCATION_FIELD = 2;
    /**
     * class,field可以使用
     */
    int LOCATION_C_F = 3;
    /**
     * method专用
     */
    int LOCATION_METHOD = 4;
    /**
     * class,method可以使用
     */
    int LOCATION_M_C = 5;
    /**
     * method,field可以使用
     */
    int LOCATION_M_F = 6;

}
