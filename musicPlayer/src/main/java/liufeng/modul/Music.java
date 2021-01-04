package liufeng.modul;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import liufeng.ui.MusicPane;

import java.awt.*;
import java.io.IOException;


/**
 * @Author Ailwyn
 * @Description: 音乐列表
 * @Date 2021/1/4 19:36
 */
public class Music {
    class MusicCell<T> extends ListCell<T> {
        private ImageView imageView;
        private Label title;
        private Label content;
        private HBox hBox;
        private Rectangle rectangle;
        private MusicModul musicModul;

        @Override
        public void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                MusicPane musicPane = new MusicPane();
                setGraphic(musicPane);
            } else {
                setGraphic(null);
            }
            setText(null);
        }
    }


    class MusicModul {
        private Double width;
        private Double height;

        public Double getWidth() {
            return width;
        }

        public void setWidth(Double width) {
            this.width = width;
        }

        public Double getHeight() {
            return height;
        }

        public void setHeight(Double height) {
            this.height = height;
        }
    }

    public class CustomControl extends VBox {
        @FXML
        private TextField textField;

        public CustomControl() {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                    "custom_control.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);

            try {
                fxmlLoader.load();
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }

    }
}
