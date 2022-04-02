package liufeng.jdk.jvm.sync;

/**
 * 双重检查 是否需要加volatile修饰
 * 单纯从代码上没有问题，但是有一种说法，cpu执行指令时，new操作不是原子操作，分为3个步骤
 * 1. 分配内存
 * 2. 调用构造器，执行初始化
 * 3. 将引用赋值给变量
 * 第2、3步可能会重排序，没能证明
 */
public class DoubleCheckTest {
    private static DoubleCheckTest instance;

    public static void init() {
        if (instance == null) {
            synchronized (DoubleCheckTest.class) {
                if (instance == null) {
                    instance = new DoubleCheckTest();
                }
            }
        }
    }

}
