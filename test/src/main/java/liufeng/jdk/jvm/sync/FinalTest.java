package liufeng.jdk.jvm.sync;

import org.junit.Test;

public class FinalTest {

    @Test
    public void t1() {
        String a = "hello2";
        final String b = "hello";
        String d = "hello";
        String c = b + 2;
        String e = d + 2;
        System.out.println((a == c));
        System.out.println((a == e));
    }

    @Test
    public void t2() {
        String a = "hello2";
        final String b = "hello";
        String d = "hello";
        String c = b + 2;
        String e = (d + 2).intern();
        System.out.println((a == c));
        System.out.println((a == e));
    }
}
