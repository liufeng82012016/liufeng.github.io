package liufeng.jdk.jvm.sync;

/**
 * cd 当前目录
 * javac *.java
 * javap -c *.class(输出分解后的代码)
 * 得到字节码文件如下
 * public class liufeng.jdk.jvm.sync.SynchronizedTest {
 *   public liufeng.jdk.jvm.sync.SynchronizedTest();
 *     Code:
 *        0: aload_0
 *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        4: return
 *
 *   public void add();
 *     Code:
 *        0: aload_0
 *        1: dup
 *        2: astore_1
 *        3: monitorenter                       // 进入同步方法
 *        4: aload_0
 *        5: dup
 *        6: getfield      #2                  // Field count:I
 *        9: iconst_1
 *       10: iadd
 *       11: putfield      #2                  // Field count:I
 *       14: aload_1
 *       15: monitorexit                        // 退出同步方法
 *       16: goto          24
 *       19: astore_2
 *       20: aload_1
 *       21: monitorexit                        // 退出同步方法。为什么退出2次？编译器自动产生一个异常处理器，如果发生异常，也会自动退出同步方法
 *       22: aload_2
 *       23: athrow
 *       24: return
 *     Exception table:
 *        from    to  target type
 *            4    16    19   any
 *           19    22    19   any
 *
 *   public synchronized void sub();
 *     Code:
 *        0: aload_0
 *        1: dup
 *        2: getfield      #2                  // Field count:I
 *        5: iconst_1
 *        6: isub
 *        7: putfield      #2                  // Field count:I
 *       10: return
 * }
 * // 鉴于synchronized方法没有特殊的标识，使用javap -verbose *.class重新反编译，得到字节码如下
 *Classfile /Users/liufeng/IdeaProjects/liufeng82012016.github.io/test/src/main/java/liufeng/jdk/jvm/sync/SynchronizedTest.class
 *   Last modified 2022-3-31; size 487 bytes
 *   MD5 checksum 09acc93aff4fef1f5252247939d6ce1d
 *   Compiled from "SynchronizedTest.java"
 * public class liufeng.jdk.jvm.sync.SynchronizedTest
 *   minor version: 0
 *   major version: 52
 *   flags: ACC_PUBLIC, ACC_SUPER
 * Constant pool:
 *    #1 = Methodref          #4.#19         // java/lang/Object."<init>":()V
 *    #2 = Fieldref           #3.#20         // liufeng/jdk/jvm/sync/SynchronizedTest.count:I
 *    #3 = Class              #21            // liufeng/jdk/jvm/sync/SynchronizedTest
 *    #4 = Class              #22            // java/lang/Object
 *    #5 = Utf8               count
 *    #6 = Utf8               I
 *    #7 = Utf8               <init>
 *    #8 = Utf8               ()V
 *    #9 = Utf8               Code
 *   #10 = Utf8               LineNumberTable
 *   #11 = Utf8               add
 *   #12 = Utf8               StackMapTable
 *   #13 = Class              #21            // liufeng/jdk/jvm/sync/SynchronizedTest
 *   #14 = Class              #22            // java/lang/Object
 *   #15 = Class              #23            // java/lang/Throwable
 *   #16 = Utf8               sub
 *   #17 = Utf8               SourceFile
 *   #18 = Utf8               SynchronizedTest.java
 *   #19 = NameAndType        #7:#8          // "<init>":()V
 *   #20 = NameAndType        #5:#6          // count:I
 *   #21 = Utf8               liufeng/jdk/jvm/sync/SynchronizedTest
 *   #22 = Utf8               java/lang/Object
 *   #23 = Utf8               java/lang/Throwable
 * {
 *   public liufeng.jdk.jvm.sync.SynchronizedTest();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: return
 *       LineNumberTable:
 *         line 3: 0
 *
 *   public void add();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=3, locals=3, args_size=1
 *          0: aload_0
 *          1: dup
 *          2: astore_1
 *          3: monitorenter
 *          4: aload_0
 *          5: dup
 *          6: getfield      #2                  // Field count:I
 *          9: iconst_1
 *         10: iadd
 *         11: putfield      #2                  // Field count:I
 *         14: aload_1
 *         15: monitorexit
 *         16: goto          24
 *         19: astore_2
 *         20: aload_1
 *         21: monitorexit
 *         22: aload_2
 *         23: athrow
 *         24: return
 *       Exception table:
 *          from    to  target type
 *              4    16    19   any
 *             19    22    19   any
 *       LineNumberTable:
 *         line 7: 0
 *         line 8: 4
 *         line 9: 14
 *         line 10: 24
 *       StackMapTable: number_of_entries = 2
 *         frame_type = 255 // full_frame
 *           offset_delta = 19
 *           locals = [ class liufeng/jdk/jvm/sync/SynchronizedTest, class java/lang/Object ]
 *           stack = [ class java/lang/Throwable ]
 *         frame_type = 250 // chop
 *           offset_delta = 4
 *
 *   public synchronized void sub();
 *     descriptor: ()V
 *     // ACC_PUBLIC 标识方法由public修饰 ACC_SYNCHRONIZED标识方法由 synchronized修饰，保存在方法常量池的方法表结构
 *     flags: ACC_PUBLIC, ACC_SYNCHRONIZED
 *     Code:
 *       stack=3, locals=1, args_size=1
 *          0: aload_0
 *          1: dup
 *          2: getfield      #2                  // Field count:I
 *          5: iconst_1
 *          6: isub
 *          7: putfield      #2                  // Field count:I
 *         10: return
 *       LineNumberTable:
 *         line 13: 0
 *         line 14: 10
 * }
 * SourceFile: "SynchronizedTest.java"
 *
 */
public class SynchronizedTest {
    private int count;

    public void add() {
        synchronized (this) {
            count++;
        }
    }

    public synchronized void sub() {
        count--;
    }
}
