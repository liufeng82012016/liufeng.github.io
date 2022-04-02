package liufeng.fx.uic;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import liufeng.fx.util.FastApplication;

public class Label01 extends FastApplication {
    @Override
    public Parent getRoot() {
        //一个空Label
        Label label1 = new Label();
        //一个带文本元素的Label
        Label label2 = new Label("Search2");
        //一个带文本和图标的Label
        Image image = new Image("https://img1.baidu.com/it/u=3497165832,3130120492&fm=253&fmt=auto&app=138&f=JPEG?w=550&h=297");
        Label label3 = new Label("Search3 Search3 Search3 Search3 Search3 Search3 Search3 Search3 Search3", new ImageView(image));
        // 如果初始化没有指定图标，也可以单独设置：label1.setGraphic(new ImageView(image));
        label3.setTextFill(Color.web("#0076a3"));
        // 设置文本位置
        label3.setContentDisplay(ContentDisplay.BOTTOM);
        // 设置文本字体
        label2.setFont(Font.font("Cambria", 32));
        label3.setFont(new Font("Arial", 32));
        // 启用文本折叠换行
        label3.setWrapText(true);
        // 如果标签有宽度和高度限制，当文本无法全部显示时，可以指定显示行为：label.setTextOverrun();

        Group group = new Group();

        group.getChildren().addAll(label1, label2, label3);

        return group;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
