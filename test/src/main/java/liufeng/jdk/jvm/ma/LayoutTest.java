package liufeng.jdk.jvm.ma;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import java.math.BigDecimal;

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

    @Test
    public void computeKLineNode(){
        KLineNode kLineNode = new KLineNode();
        kLineNode.coinName = "TEST";
        kLineNode.avgPrice=new BigDecimal("1.00");
        kLineNode.maxPrice=new BigDecimal("0.9999999");
        kLineNode.minPrice=new BigDecimal("1.00000001");
        kLineNode.intervalType=1;
        kLineNode.startTime=System.currentTimeMillis();
        System.out.println(ClassLayout.parseInstance(kLineNode).toPrintable());
        System.out.println(40*1000*20/1024);
    }


    class KLineNode{
        private String coinName;
        private BigDecimal maxPrice;
        private BigDecimal minPrice;
        private Integer intervalType;

        private Long startTime;

        private BigDecimal avgPrice;
    }
}
