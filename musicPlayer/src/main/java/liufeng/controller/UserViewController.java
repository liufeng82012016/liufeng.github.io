package liufeng.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import liufeng.constants.PaintConstants;
import liufeng.modul.Music;
import liufeng.ui.MusicCell;


/**
 * @Author Ailwyn
 * @Description: todo
 * @Date 2020/12/17 19:01
 */
public class UserViewController {
    @FXML
    private Label chat;
    @FXML
    private Label contact;
    @FXML
    private Label music;
    @FXML
    private ListView list;

    public UserViewController() {
    }


    public void switchChat() {
        System.out.println("chat");
        chat.setTextFill(PaintConstants.BLACK);
        contact.setTextFill(PaintConstants.WHITE);
        music.setTextFill(PaintConstants.WHITE);
    }

    public void switchContact() {
        chat.setTextFill(PaintConstants.WHITE);
        contact.setTextFill(PaintConstants.BLACK);
        music.setTextFill(PaintConstants.WHITE);
    }

    public void switchMusic() {
        chat.setTextFill(PaintConstants.WHITE);
        contact.setTextFill(PaintConstants.WHITE);
        music.setTextFill(PaintConstants.BLACK);
    }

    /**
     * listView绑定数据
     */
    public void bindList() {
        if (list == null){
            list = new ListView<Music>();
        }
        ObservableList<Music> items = FXCollections.observableArrayList (
                new Music(),new Music(),new Music(),new Music(),new Music(),new Music(),
                new Music(),new Music(),new Music(),new Music(),new Music(),new Music(),
                new Music(),new Music(),new Music(),new Music(),new Music(),new Music(),
                new Music(),new Music(),new Music(),new Music(),new Music(),new Music());
        list.setItems(items);
        list.setCellFactory(param -> new MusicCell());
    }
}
