package liufeng.jdk.jvm.ma;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

public class LayoutTest {

    private static class T {

    }

    private static class TStr {
        private String str = "1234";
    }


    @Test
    public void testEmptyObj() {
        T t = new T();
        System.out.println(ClassLayout.parseInstance(t).toPrintable());
    }
    @Test
    public void testObjStr() {
        TStr t = new TStr();
        System.out.println(ClassLayout.parseInstance(t).toPrintable());
        synchronized (t){
            System.out.println(ClassLayout.parseInstance(t).toPrintable());
        }
    }

    @Test
    public void testObj() {
        Object obj = new Object();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }

    @Test
    public void testString() {
        String obj = new String("333");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}
