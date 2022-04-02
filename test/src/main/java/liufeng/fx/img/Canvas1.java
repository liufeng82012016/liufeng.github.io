package liufeng.fx.img;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import liufeng.fx.util.FastApplication;

/**
 * canvas简单图形
 */
public class Canvas1 extends FastApplication {
    @Override
    public Parent getRoot() {
        Group root = new Group();
        Canvas canvas = new Canvas(300, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);
        root.getChildren().add(canvas);
        return root;
    }

    private void drawShapes(GraphicsContext gc) {
        // 实心图形为绿色
        gc.setFill(Color.GREEN);
        // 空心图形为蓝色
        gc.setStroke(Color.BLUE);
        // 设定线的宽度，修改之后生效，已经渲染的图形无效
        gc.setLineWidth(4);
        // 使用当前笔划绘制绘制绘制线条
        gc.strokeLine(40, 10, 10, 40);
        // 使用当前的填充绘制填充椭圆形，w和h一样就是圆形
        gc.fillOval(10, 60, 30, 50);
        gc.setLineWidth(6);
        // 绘制空心椭圆
        gc.strokeOval(60, 60, 30, 50);
        // 绘制实心矩形，参数分别是x，y，w，h，x方向圆角，y方向圆角
        gc.fillRoundRect(110, 60, 30, 30, 10, 20);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 180, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
//        gc.fillPolygon(new double[]{10, 40, 10, 40},
//                new double[]{210, 210, 240, 240}, 4);
//        gc.strokePolygon(new double[]{60, 90, 60, 90},
//                new double[]{210, 210, 240, 240}, 4);
//        gc.strokePolyline(new double[]{110, 140, 110, 140},
//                new double[]{210, 210, 240, 240}, 4);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
