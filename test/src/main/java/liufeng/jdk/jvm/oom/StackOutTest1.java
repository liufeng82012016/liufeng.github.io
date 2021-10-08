package liufeng.jdk.jvm.oom;

/**
 * D:\project\liufeng\test\src\main\java>java -Xss128k liufeng.jdk.jvm.StackOutTest1
 * stack length:993
 * Exception in thread "main" java.lang.StackOverflowError
 *         at liufeng.jdk.jvm.StackOutTest1.stackLeak(StackOutTest1.java:7)
 *         at liufeng.jdk.jvm.StackOutTest1.stackLeak(StackOutTest1.java:8)
 *         ...
 */
public class StackOutTest1 {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        StackOutTest1 oom = new StackOutTest1();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}
