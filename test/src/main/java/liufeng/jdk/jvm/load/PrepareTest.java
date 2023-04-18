package liufeng.jdk.jvm.load;

import org.junit.Test;

public class PrepareTest {
    private static class T {
        private static T t = new T();
        private static int count = 2;

        private T() {
            count++;
            System.out.println("con:" + count);
        }
    }

    private static class T2 {
        private static int count = 2;
        private static T2 t = new T2();

        private T2() {
            count++;
            System.out.println("con:" + count);
        }
    }

    @Test
    public void test() {
        System.out.println(T.count);
        System.out.println("------------");
        System.out.println(T2.count);

    }

}
