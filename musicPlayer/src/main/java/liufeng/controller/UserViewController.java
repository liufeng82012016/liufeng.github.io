package liufeng.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import liufeng.CentralController;
import liufeng.Scanner;
import liufeng.modul.Music;
import liufeng.ui.MusicCell;


/**
 * @Author liufeng
 * @Description: todo
 * @since 2020/12/17 19:01
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



    /**
     * listView绑定数据
     */
    public void bindList() {
        if (list == null) {
            list = new ListView<Music>();
        }
        ObservableList<Music> items = FXCollections.observableArrayList(
                new Music(), new Music(), new Music(), new Music(), new Music(), new Music(),
                new Music(), new Music(), new Music(), new Music(), new Music(), new Music(),
                new Music(), new Music(), new Music(), new Music(), new Music(), new Music(),
                new Music(), new Music(), new Music(), new Music(), new Music(), new Music());
        list.setItems(items);
        list.setCellFactory(param -> new MusicCell());
        list.getSelectionModel().selectedItemProperty().addListener(e -> {
            Music music = (Music) list.getSelectionModel().getSelectedItem();
            CentralController.setSelected(music);
            Scanner.checkMusicView();
        });
    }
}
