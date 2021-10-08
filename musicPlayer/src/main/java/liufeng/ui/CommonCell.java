package liufeng.ui;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * @Author liufeng
 * @Description: todo
 * @since 2021/7/15 16:53
 */
public class CommonCell extends AnchorPane {
    private ImageView icon;
    private Label title;
    private Label content;
    private ImageView operation;

    public CommonCell(double width, double height, double interval, String title, String content, String icon, String operation) {

        setMaxSize(width, height);
        setMinSize(width, height);
        this.icon = new ImageView(icon);
        this.icon.setFitWidth(height - 2 * interval);
        this.icon.setFitHeight(this.icon.getFitWidth());
    }

    private static void paramValidate(double width, double height, double interval) {

    }
}
