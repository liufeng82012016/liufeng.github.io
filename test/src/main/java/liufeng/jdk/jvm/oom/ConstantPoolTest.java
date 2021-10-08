package liufeng.jdk.jvm.oom;

import java.util.HashSet;
import java.util.Set;

/**
 * D:\project\liufeng\test\src\main\java>java -Xmx6m liufeng.jdk.jvm.ConstantPoolTest
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 *         at java.util.HashMap.resize(Unknown Source)
 *         at java.util.HashMap.putVal(Unknown Source)
 *         at java.util.HashMap.put(Unknown Source)
 *         at java.util.HashSet.add(Unknown Source)
 *         at liufeng.jdk.jvm.ConstantPoolTest.main(ConstantPoolTest.java:11)
 * 元空间归属于Java堆内存
 */
public class ConstantPoolTest {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        int i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}
