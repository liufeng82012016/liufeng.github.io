package liufeng.fx.img;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import liufeng.fx.util.FastApplication;

/**
 * 图片操作-展示网络图片，修改
 */
public class Img1 extends FastApplication {
    @Override
    public Parent getRoot() {
        StackPane root = new StackPane();

        // 创建Image和ImageView对象
        Image image = new Image("https://img1.baidu.com/it/u=3497165832,3130120492&fm=253&fmt=auto&app=138&f=JPEG?w=550&h=297");
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        // 获取PixelReader--只读，不可写
        PixelReader pixelReader = image.getPixelReader();
        System.out.println("Image Width: "+image.getWidth());
        System.out.println("Image Height: "+image.getHeight());
        System.out.println("Pixel Format: "+pixelReader.getPixelFormat());

        // 创建WritableImage
        WritableImage wImage = new WritableImage(
                (int)image.getWidth(),
                (int)image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();

        // 确定图片中每一个像素的颜色
        for (int readY = 0; readY < image.getHeight(); readY++) {
            for (int readX = 0; readX < image.getWidth(); readX++) {
                Color color = pixelReader.getColor(readX, readY);
                System.out.println("\nPixel color at coordinates ("
                        + readX + "," + readY + ") "
                        + color.toString());
                System.out.println("R = " + color.getRed());
                System.out.println("G = " + color.getGreen());
                System.out.println("B = " + color.getBlue());
                System.out.println("Opacity = " + color.getOpacity());
                System.out.println("Saturation = " + color.getSaturation());

                // 现在写入一个更为明亮的颜色到PixelWriter中
                color = color.brighter();
                pixelWriter.setColor(readX,readY,color);
            }
        }

        // 在屏幕上显示图像
        root.getChildren().add(imageView);
        return root;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
