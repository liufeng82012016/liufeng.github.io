package liufeng.jdk.jvm.compile.constants;

import java.util.HashMap;

public class JdkVersion {
    private static HashMap<Integer, String> jdkVersionMap = new HashMap<>();

    static {
        for (int i = 1; i < 18; i++) {
            String str = i > 8 ? "jdk" + i : ("jdk 1." + i);
            jdkVersionMap.put(44 + i, str);
        }
    }

    /**
     * 根据版本号获取对应的jdk版本
     *
     * @param versionNumber 主版本号
     */
    public static String getVersion(String versionNumber) {
        return jdkVersionMap.get(Integer.parseInt(versionNumber));
    }
}
