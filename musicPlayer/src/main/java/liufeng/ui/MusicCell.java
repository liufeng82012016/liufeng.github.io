package liufeng.ui;

import javafx.scene.control.ListCell;
import liufeng.modul.Music;


/**
 * @Author Ailwyn
 * @Description: 音乐列表
 * @Date 2021/1/4 19:36
 */
public class MusicCell<T> extends ListCell<Music> {

    @Override
    public void updateItem(Music item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            MusicPane musicPane = new MusicPane();
            musicPane.setAttr(item);
            setGraphic(musicPane);
        } else {
            setGraphic(null);
        }
        setText(null);
    }

}
