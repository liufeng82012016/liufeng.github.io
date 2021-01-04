package liufeng.ui;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;

/**
 * @Author Ailwyn
 * @Description: todo
 * @Date 2021/1/4 20:24
 */
public class MusicPane extends AnchorPane {
    @FXML
    private ImageView icon;
    private Label title;
    private Label content;
    private ImageView operation;

    public MusicPane() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "CommonCell.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void init(){}

}
