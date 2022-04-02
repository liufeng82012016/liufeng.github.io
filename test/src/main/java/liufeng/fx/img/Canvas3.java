package liufeng.fx.img;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import liufeng.fx.util.FastApplication;

/**
 * canvas图层
 */
public class Canvas3 extends FastApplication {
    @Override
    public Parent getRoot() {
        BorderPane root = new BorderPane();

        // 创建一个单选框，用于切换图层
        ChoiceBox<String> cb = new ChoiceBox<>();
        cb.getItems().addAll("layer1", "layer2");
        cb.setValue("layer1");


        root.setTop(cb);

        // 创建pane，放置canvas画布
        Pane pane = new Pane();
        Canvas layer1 = new Canvas(800, 800);
        Canvas layer2 = new Canvas(800, 800);
        // 设置画布属性
        drawShapes(layer1, layer2);
        // 设置画布点击事件
        setCanvasClick(layer1, layer2);
        // 设置切换涂层事件
        cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if ("layer1".equals(newValue)) {
                    layer1.toFront();
                } else {
                    layer2.toFront();
                }
            }
        });

        pane.getChildren().addAll(layer1, layer2);
        // 设置画布层级
        layer1.toFront();
        root.setCenter(pane);


        return root;
    }


    private void drawShapes(Canvas layer1, Canvas layer2) {

        // 获得图形上下文,
        GraphicsContext gc1 = layer1.getGraphicsContext2D();
        gc1.setFill(Color.GREEN);
        gc1.fillOval(50, 50, 20, 20);
        GraphicsContext gc2 = layer2.getGraphicsContext2D();
        gc2.setFill(Color.BLUE);
        gc2.fillOval(100, 100, 20, 20);
    }


    public void setCanvasClick(Canvas layer1, Canvas layer2) {
        // Layer 1的处理器
        layer1.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        System.out.println("click 1");
                        layer1.getGraphicsContext2D().fillOval(e.getX(), e.getY(), 20, 20);
                    }
                });

        // Layer 2的处理器
        layer2.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        System.out.println("click 2");
                        layer2.getGraphicsContext2D().fillOval(e.getX(), e.getY(), 20, 20);
                    }
                });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
