package liufeng.jdk.jvm.compile.strategy.af;


import liufeng.jdk.jvm.compile.enums.AccessFlags;
import liufeng.jdk.jvm.compile.util.ByteReader;

import java.util.LinkedList;
import java.util.List;

/**
 * 类访问标识 u2，最多32种标识
 */
public abstract class AccessFlagsParser {
    /**
     * 标识符
     */
    protected AccessFlags[] accessFlags = new AccessFlags[16];

    /**
     * 根据标记获取对应的标识符
     */
    public List<AccessFlags> getByTag(int flag) {
        init();
        char[] flagChars = ByteReader.toBinaryString(flag).toCharArray();
        List<AccessFlags> result = new LinkedList<>();
        // 解析标识，并按照倒序添加到列表，保证list内各标记的顺序
        for (int i = flagChars.length - 1; i >= 0; i--) {
            char c = flagChars[i];
            if (c == '1') {
                result.add(accessFlags[15 - i]);
            }
        }
        return result;
    }

    /**
     * 根据标记获取对应的修饰符
     */
    public String getClassModifier(int flag) {
        init();
        StringBuilder sb = new StringBuilder();
        char[] flagChars = ByteReader.toBinaryString(flag).toCharArray();
//        System.out.println("getClassModifier : " + Arrays.toString(accessFlags));
//        System.out.println("getClassModifier : " + Arrays.toString(flagChars));
        // 解析标识，并按照倒序添加到列表，保证list内各标记的顺序
        boolean justClass = true;
        for (int i = flagChars.length - 1; i >= 0; i--) {
            char c = flagChars[i];
            if (c == '1') {
                AccessFlags accessFlag = accessFlags[15 - i];
                sb.append(accessFlag.getJavaKey() == null ? "" : accessFlag.getJavaKey());
                if (accessFlag.isClazz()) {
                    justClass = false;
                }
            }
        }
        return sb + (justClass ? "class " : "");
    }

    /**
     * 初始化标识符
     */
    private void init() {
        if (accessFlags[0] == null) {
            // 初始化数组
            synchronized (ClassAccessParser.class) {
                for (AccessFlags classAccessFlags : AccessFlags.values()) {
                    if (!select(classAccessFlags.getLocation())) {
                        // 只添加对应的的类型
                        continue;
                    }
                    String binaryString = ByteReader.toBinaryString(classAccessFlags.getValue());
                    int index = binaryString.indexOf("1");
                    accessFlags[15 - index] = classAccessFlags;
                }
            }
        }
    }

    abstract boolean select(int location);

}
