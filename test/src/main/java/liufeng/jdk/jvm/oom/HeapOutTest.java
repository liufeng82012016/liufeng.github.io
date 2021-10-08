package liufeng.jdk.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * heap内存溢出测试方法 （深入理解Java虚拟机）
 * 编译java文件为class文件
 * D:\project\liufeng\test\src\main\java>javac liufeng\jdk\jvm\HeapOutTest.java
 * 设定Xmx20m，导出异常日志
 * D:\project\liufeng\test\src\main\java>java -Xmx20m -Xms20m -XX:+HeapDumpOnOutOfMemoryError liufeng.jdk.jvm.HeapOutTest
 * Heap dump file created [28065915 bytes in 0.372 secs]
 * 日志打印
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 *         at java.util.Arrays.copyOf(Unknown Source)
 *         at java.util.Arrays.copyOf(Unknown Source)
 *         at java.util.ArrayList.grow(Unknown Source)
 *         at java.util.ArrayList.ensureExplicitCapacity(Unknown Source)
 *         at java.util.ArrayList.ensureCapacityInternal(Unknown Source)
 *         at java.util.ArrayList.add(Unknown Source)
 *         at liufeng.jdk.jvm.HeapOutTest.main(HeapOutTest.java:20)
 *
 * 打开java安装目录下jvisualvm.exe,点击文件下第一个图标，装入hprof文件，店家类，可看到OOMObject实例数为810326(99.2%)，占用内存为12965216(64.5%)
 */
public class HeapOutTest {

    static class OOMObject {

    }

    public static void main(String[] args) {
        System.out.println("open");
        List<OOMObject> list = new ArrayList<>();
        int count = 0;
        while (count++ < Integer.MAX_VALUE) {
            list.add(new OOMObject());
        }
//        System.out.println("end");
    }
}
