package liufeng.util;

import javafx.concurrent.Task;
import liufeng.http.Response;

import java.util.Random;

/**
 * @Author liufeng
 * @Description: todo
 * @since 2021/7/22 16:59
 */
public class RequestTask extends Task<Response> {
    private Random random = new Random();

    @Override
    protected Response call() throws Exception {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (random.nextInt(2) == 0) {
            return Response.success();
        }
        return Response.fail();
    }
}
