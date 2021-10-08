package liufeng.jdk.jvm.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * D:\project\liufeng\test\src\main\java>java -Xmx20m -XX:+HeapDumpOnOutOfMemoryError  liufeng.jdk.jvm.DirectMemoryTest
 * Exception in thread "main" java.lang.OutOfMemoryError
 *         at sun.misc.Unsafe.allocateMemory(Native Method)
 *         at liufeng.jdk.jvm.DirectMemoryTest.main(DirectMemoryTest.java:15)
 * MaxDirectMemorySize 默认和Xmx一致？？？
 */
public class DirectMemoryTest {
    private static int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception{
        Field declaredField = Unsafe.class.getDeclaredFields()[0];
        declaredField.setAccessible(true);
        Unsafe unsafe = (Unsafe) declaredField.get(null);
        while (true){
            unsafe.allocateMemory(_1MB);
        }
    }
}
