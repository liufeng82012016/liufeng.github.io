package liufeng.jdk.thread;

import org.junit.Test;

/**
 * wait/notify相关方法测试
 */
public class WaitTest {
    @Test
    public void testWaitCommon(){
        try {
            // Synchronized代码块或者方法才能调用，wait/notify方法会尝试monitor锁，无法获取会抛出IllegalMonitorStateException异常
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WaitTest waitTest = new WaitTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                waitTest.testWaitCommon();
            }
        }).start();
    }
}
