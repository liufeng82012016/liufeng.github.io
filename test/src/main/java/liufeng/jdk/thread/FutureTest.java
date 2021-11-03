package liufeng.jdk.thread;

import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Future相关api学习
 */
public class FutureTest {
    /**
     * 不能用test方法运行，主线程不会等待线程池提交结果
     */
    public void testCompletableFuture1() {
        long startTime = System.currentTimeMillis();
        Executor executor = Executors.newFixedThreadPool(2);
        // 创建第一个任务
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("开始烧水...");
            try {
                Thread.sleep(1000);
                return "烧水完成，耗时:" + (System.currentTimeMillis() - startTime);
            } catch (InterruptedException e) {
                return "断电了！！！";
            }
        }, executor);
        // 创建第二个任务
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("机器人开始扫地...");
            try {
                Thread.sleep(2000);
                return "扫地完成，耗时:" + (System.currentTimeMillis() - startTime);
            } catch (InterruptedException e) {
                return "机器人坏掉了！！！";
            }
        }, executor);
        // 结果处理
        future1.thenAccept(s -> System.out.println("烧水： " + s));
        future2.thenAccept(s -> System.out.println("扫地： " + s));
        // 业务逻辑
        System.out.println("打游戏...");
        System.out.println("吃零食...");
        System.out.println("打游戏...");
    }

    public void testCompletableFuture2() {
        CompletableFuture<String> future1 = new CompletableFuture();
        // 创建第一个任务
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String next = scanner.next();
                if ("quit".equals(next)) {
                    System.out.println(System.currentTimeMillis());
                    future1.complete(next);
                    return;
                }
                System.out.println("wrong msg: " + next);
            }
        }).start();
        // 创建第二个任务
        String s1 = null;
        try {
            s1 = future1.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println(s1);
        future1.thenAccept(s -> {
            System.out.println(System.currentTimeMillis());
            System.out.println("in： " + s);
        });
        // 业务逻辑
        System.out.println("打游戏...");
        System.out.println("吃零食...");
        System.out.println("打游戏...");
    }

    public static void main(String[] args) {
        int methodMarker = 2;
        switch (methodMarker) {
            case 1:
                new FutureTest().testCompletableFuture1();
                break;
            case 2:
                new FutureTest().testCompletableFuture2();
                break;
            default:
                ;
        }

    }

}
