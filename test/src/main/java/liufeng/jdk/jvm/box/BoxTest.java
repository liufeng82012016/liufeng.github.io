package liufeng.jdk.jvm.box;

import org.junit.Test;

/**
 * 自动装箱、拆箱测试
 * == 运算在没有遇到算数运算的情况下，不会自动拆箱，equals()不会处理数据转型
 */
public class BoxTest {
    @Test
    public void box() {
        Integer a = 1, b = 2, c = 3, d = 3, e = 321, f = 321;
        Long g = 3L;
        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));
    }
}
