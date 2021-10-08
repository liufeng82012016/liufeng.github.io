package liufeng.jdk.jvm.ma;

/**
 * 年轻代内存分配测试
 * D:\project\liufeng\test\src\main\java>java -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=5m liufeng.jdk.jvm.ma.EdenMATest
 * Heap
 *  PSYoungGen      total 9216K, used 7464K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
 *   eden space 8192K, 91% used [0x00000000ff600000,0x00000000ffd4a050,0x00000000ffe00000)
 *   from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
 *   to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
 *  ParOldGen       total 10240K, used 4096K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
 *   object space 10240K, 40% used [0x00000000fec00000,0x00000000ff000010,0x00000000ff600000)
 *  Metaspace       used 2568K, capacity 4486K, committed 4864K, reserved 1056768K
 *   class space    used 280K, capacity 386K, committed 512K, reserved 1048576K
 *
 *   这里和书籍上的预期结果不一样，4MB被分配到了老年代，没出现GC,怀疑是-XX:PretenureSizeThreshold参数小于4MB，所以该对象直接分配到了老年代
 *
 *   D:\project\liufeng\test\src\main\java>java -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=5242880 liufeng.jdk.jvm.ma.EdenMATest
 *   设定-XX:PretenureSizeThreshold=5*1024*1024 发现结果没有变化？ 继续探索中
 *
 *   继续读书，发现作者增加了备注：
 *   -XX:PretenureSizeThreshold只针对Serial和ParNew 2款新生代收集器有效，HotShot的其他新生代收集器如Parallel Scavenge并不支持此参数。可采取ParNew+CMS进行调试
 *
 *   注意：进行编码调试的时候，最好保持和作者相同的环境，否则会发生一些难以察觉的问题。。。
 *
 *   附（命令行查看垃圾收集器）:
 *      java -XX:+PrintCommandLineFlags -version
 *      -XX:InitialHeapSize=131598528 -XX:MaxHeapSize=2105576448 -XX:+PrintCommandLineFlags -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+U
 * seParallelGC
 * java version "1.8.0_241"
 * Java(TM) SE Runtime Environment (build 1.8.0_241-b07)
 * Java HotSpot(TM) 64-Bit Server VM (build 25.241-b07, mixed mode)
 *
 * |---------------------|
 * | -XX:+UeParallelGC   |
 * |---------------------|
 *
 *
 * 本书说明：大对象将直接进入到老年代，所以编写程序时需要避免出现‘朝生夕死’的大对象，如
 * - 很长的字符串
 * - 元素庞大的数组
 * -
 */
public class EdenMATest {
    public static void main(String[] args) {
        int _1MB = 1024 * 1024;
        byte[] alloctiona1, alloctiona2, alloctiona3, alloctiona4;
        alloctiona1 = new byte[2 * _1MB];
        alloctiona2 = new byte[2 * _1MB];
        alloctiona3 = new byte[2 * _1MB];
        alloctiona4 = new byte[4 * _1MB];
    }
}
