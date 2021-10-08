package liufeng.jdk.jvm.oom;

/**
 * D:\project\liufeng\test\src\main\java>java liufeng.jdk.jvm.StackOutTest2
 * stack length:4340
 * Exception in thread "main" java.lang.StackOverflowError
 *         at liufeng.jdk.jvm.StackOutTest2.stackLeak(StackOutTest2.java:25)
 *         at liufeng.jdk.jvm.StackOutTest2.stackLeak(StackOutTest2.java:25)
 *         ...
 */
public class StackOutTest3 {
    private void dontStop(){
        while (true){

        }
    }

    private void stackLeakByThread(){
        while (true){
            new Thread(this::dontStop).start();
        }
    }

    public static void main(String[] args) {
        new StackOutTest3().stackLeakByThread();
        // OutOfMemory
    }
}
