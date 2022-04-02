package liufeng.fx.img;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * 图片操作-绘制图形
 */
public class Img2 extends Application {

    // 图像数据
    private final int width = 10;
    private final int height = 10;
    private final byte[] imageData =
            new byte[width * height * 3];

    public Parent getRoot() {
        StackPane root = new StackPane();

        Canvas canvas = new Canvas(200, 200);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        createImageData(gc);
        drawImageData(gc);

        // 在屏幕上显示图像
        root.getChildren().add(canvas);
        return root;
    }

    private void createImageData(GraphicsContext gc) {
        int i = 0;
        for (int y = 0; y < height; y++) {
            int r = y * 255 / height;
            for (int x = 0; x < width; x++) {
                int g = x * 255 / width;
                imageData[i] = (byte) r;
                imageData[i + 1] = (byte) g;
                i += 3;
            }
        }
    }

    private void drawImageData(GraphicsContext gc) {
        boolean on = true;
        PixelWriter pixelWriter = gc.getPixelWriter();
        PixelFormat<ByteBuffer> pixelFormat = PixelFormat.getByteRgbInstance();
        for (int y = 50; y < 150; y += height) {
            for (int x = 50; x < 150; x += width) {
                if (on) {
                    pixelWriter.setPixels(x, y, width,
                            height, pixelFormat, imageData,
                            0, width * 3);
                }
                on = !on;
            }
            on = !on;
        }

        // 增加阴影效果
        gc.applyEffect(new DropShadow(20, 20, 20, Color.GRAY));
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(getRoot());
        primaryStage.setScene(new Scene(getRoot()));
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();
        //截取场景的快照
        WritableImage writableImage = scene.snapshot(null);
        System.out.println("show");
        // 将快照写入文件系统存为.png图片
        File outFile = new File("imageops-snapshot.png");
        try {
            System.out.println("write: " + outFile.getAbsolutePath());
            ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", outFile);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
