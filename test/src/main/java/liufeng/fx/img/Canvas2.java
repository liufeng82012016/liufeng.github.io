package liufeng.fx.img;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import liufeng.fx.util.FastApplication;

/**
 * canvas简单图形，阴影、事件
 */
public class Canvas2 extends FastApplication {
    @Override
    public Parent getRoot() {
        Group root = new Group();
        Canvas canvas = new Canvas(300, 250);
        moveCanvas(canvas, 0, 0);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // 绘图
        drawShapes(gc);
        // 填充颜色
        drawRadialGradient(gc, Color.BLUE, Color.RED);
        // 添加阴影
        drawDropShadow(gc, Color.LIGHTGREEN, Color.ORANGE, Color.PURPLE, Color.PINK);
        // canvas添加事件
        setCanvasClick(canvas, gc);
        root.getChildren().add(canvas);
        return root;
    }


    private void moveCanvas(Canvas canvas, int x, int y) {
        canvas.setTranslateX(x);
        canvas.setTranslateY(y);
    }

    private void drawShapes(GraphicsContext gc) {
        gc.beginPath();
        // 指定起点
        gc.moveTo(50, 50);
        // 指定经过的点
        gc.bezierCurveTo(160, 20, 150, 150, 75, 150);
        gc.closePath();
    }

    private void drawRadialGradient(GraphicsContext gc, Color firstColor, Color lastColor) {
        gc.setFill(new RadialGradient(0, 0, 0.5, 0.5, 0.1, true,
                CycleMethod.REFLECT,
                new Stop(0.0, firstColor),
                new Stop(1.0, lastColor)));
        gc.fill();
    }

    public void setCanvasClick(Canvas canvas, GraphicsContext gc) {
// 在用户拖动鼠标时擦除部分内容
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        // 擦除canvas
                        gc.clearRect(e.getX() - 2, e.getY() - 2, 5, 5);
                    }
                });

        // 当用户双击鼠标时使用蓝色矩形填充Canvas
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        if (t.getClickCount() > 1) {
                            // 双击蓝色填充
                            gc.setFill(Color.BLUE);
                            gc.fillRect(t.getX(), t.getY(), 2, 3);
                        }
                    }
                });
    }

    private void drawDropShadow(GraphicsContext gc, Color firstColor, Color secondColor,
                                Color thirdColor, Color fourthColor) {
        gc.applyEffect(new DropShadow(20, 20, 0, firstColor));
        gc.applyEffect(new DropShadow(20, 0, 20, secondColor));
        gc.applyEffect(new DropShadow(20, -20, 0, thirdColor));
        gc.applyEffect(new DropShadow(20, 0, -20, fourthColor));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
