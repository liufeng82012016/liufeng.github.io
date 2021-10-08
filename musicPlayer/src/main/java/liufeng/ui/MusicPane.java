package liufeng.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import liufeng.modul.Music;

import java.io.IOException;

/**
 * @Author liufeng
 * @Description: todo
 * @since 2021/1/4 20:24
 */
public class MusicPane extends AnchorPane {
    @FXML
    private ImageView icon;
    @FXML
    private Label title;
    @FXML
    private Label content;
    @FXML
    private ImageView operation;

    public MusicPane() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(
                "fxml/CommonCell.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    public <T> void setAttr(T item) {
        Music music = (Music) item;
        icon.setImage(new Image(music.getIcon()));
        operation.setImage(new Image(music.getIcon()));
        title.textProperty().bindBidirectional(music.titleProperty());
        content.textProperty().bindBidirectional(music.contentProperty());
        operation.setOnMouseClicked(handler -> {
            Node source = (Node) handler.getSource();
            System.out.println(source);
            ContextMenu contextMenu = MusicMenu.getInstance();
            contextMenu.show(source,javafx.geometry.Side.BOTTOM,0,0);
        });
    }
}
