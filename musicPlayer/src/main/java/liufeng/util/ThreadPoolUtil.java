package liufeng.util;

import javafx.concurrent.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author liufeng
 * @Description: todo
 * @since 2021/7/22 17:30
 */
public class ThreadPoolUtil {
    private static ExecutorService pool = Executors.newFixedThreadPool(2);

    public static void submit(Task<?> task) {
        pool.submit(task);
    }
}
