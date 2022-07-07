package liufeng.jdk.jvm.ma;

/**
 * 局部变量表Slot复用对垃圾回收的影响测试
 * 备注：java -verbose:gc liufeng.jdk.jvm.ma.LocalVariableTest（maven项目，在java目录执行命令）
 * --------------------------------------------------
 * byte[] placeholder = new byte[64*1024*1024];
 * System.gc();
 *
 *
 * [GC (System.gc())  68157K->65992K(251392K), 0.0018868 secs]
 * [Full GC (System.gc())  65992K->65804K(251392K), 0.0077577 secs]
 * 数组未被回收，GC Roots一部分局部变量仍然保持对它的关联
 * --------------------------------------------------
 * {
 *     byte[] placeholder = new byte[64*1024*1024];
 * }
 * System.gc();
 *
 *
 * [GC (System.gc())  68157K->65992K(251392K), 0.0017526 secs]
 * [Full GC (System.gc())  65992K->65804K(251392K), 0.0054468 secs]
 * --------------------------------------------------
 * {
 *     byte[] placeholder = new byte[64*1024*1024];
 * }
 * int a = 0;
 * System.gc();
 *
 * [GC (System.gc())  68157K->65928K(251392K), 0.0019035 secs]
 * [Full GC (System.gc())  65928K->268K(251392K), 0.0059965 secs]
 * --------------------------------------------------
 * 3种情况只有第3种情况下，数组被回收
 */
public class LocalVariableTest {
    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        int a= 0;
        System.gc();
    }
}
