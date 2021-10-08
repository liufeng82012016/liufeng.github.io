package liufeng.jdk.exception;

import org.junit.Test;

/**
 * 异常相关测试
 */
public class ExceptionTest {
    @Test
    public void testCatch() {
        try {
            throw new IllegalArgumentException("msg");
        } catch (RuntimeException e) {
            System.out.println(1);
        } catch (Exception e) {
            // 异常只会被处理一次
            System.out.println(2);
        }
    }

    @Test
    public void testCatch1() {
        try {
            throw new IllegalArgumentException("msg");
        } catch (RuntimeException e) {
            System.out.println(1);
            // 这里抛出不会被Exception捕获到
            throw e;
        } catch (Exception e) {
            System.out.println(2);
        }
    }


    @Test
    public void testReturn() {
        System.out.println(catchAndReturn());
    }

    public int catchAndReturn() {
        try {
            throw new IllegalArgumentException("msg");
        } catch (RuntimeException e) {
            System.out.println("exception");
            return 0;
        } finally {
            // finally会先return掉，所以返回结果是1而不是0
            return 1;
        }
    }

    @Test
    public void testReturn1() {
        System.out.println(tryReturn());
    }

    public int tryReturn() {
        try {
            System.out.println("exception");
            return 0;
        } finally {
            // finally会先return掉，所以返回结果是1而不是0
            return 1;
        }
    }
}
