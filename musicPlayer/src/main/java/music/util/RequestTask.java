package music.util;

import javafx.concurrent.Task;
import music.http.Response;

import java.util.Random;

/**
 * @Author Ailwyn
 * @Description: todo
 * @Date 2021/7/22 16:59
 */
public class RequestTask extends Task<Response> {
    Random random = new Random();

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
