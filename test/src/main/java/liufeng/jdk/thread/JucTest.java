package liufeng.jdk.thread;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class JucTest {
    /**
     * Semaphore 信号量
     * 共享锁
     * 使用场景：
     * 1. 同一时间，对于一个资源，发放n枚令牌；
     * 2. 每一个要使用资源的用户来申请令牌，先到先得；
     *  2.1 使用完了要返回令牌，不然你带走了，后面的人没得用；
     *  2.2 你可以申请任意枚令牌；剩余足够就会给你；否则申请失败
     */
    @Test
    public void semaphoreTest() {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    // 申请令牌
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 模拟业务操作
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(finalI + "end");
                // 归还令牌
                semaphore.release();
            }).start();
        }
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }

    /**
     * 计时器操作
     */
    @Test
    public void countDownLatchTest() {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        final Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    // 每个线程处理自己的事情
                    int sleepMills = random.nextInt(3000);
                    Thread.sleep(sleepMills);
                    // 计时1次
                    countDownLatch.countDown();
                    System.out.println(finalI + " sleep " + sleepMills);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            // 等待所有计时线程业务操作结束
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 自定义业务操作
        System.out.println("end");
    }
}
