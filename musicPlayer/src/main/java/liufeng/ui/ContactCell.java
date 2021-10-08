package liufeng.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


/**
 * @Author liufeng
 * @Description: 联系人
 * @since 2021/1/4 19:36
 */
public class ContactCell extends HBox {
    private ImageView icon;
    private GridPane gridPane;

    public ContactCell(double width, double height, double interval, GridPane gridPane, String icon) {
        setMaxSize(width, height);
        setMinSize(width, height);
        this.icon = new ImageView(new Image(ContactCell.class.getResourceAsStream(icon)));
        this.icon.setFitWidth(height - 2 * interval);
        this.icon.setFitHeight(this.icon.getFitWidth());
        this.gridPane = gridPane;
    }

}
