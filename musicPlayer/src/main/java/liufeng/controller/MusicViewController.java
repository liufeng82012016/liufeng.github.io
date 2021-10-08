package liufeng.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import liufeng.CentralController;
import liufeng.modul.Music;


/**
 * @Author liufeng
 * @Description: todo
 * @since 2020/12/17 19:01
 */
public class MusicViewController {
    @FXML
    private Label chat;
    @FXML
    private Label contact;
    @FXML
    private Label music;
    @FXML
    private ListView list;

    public MusicViewController() {
    }



    /**
     * listView绑定数据
     */
    public void initMusic() {
        Music selected = CentralController.getSelected();
    }
}
