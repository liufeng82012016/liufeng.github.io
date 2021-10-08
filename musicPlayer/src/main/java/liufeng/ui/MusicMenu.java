package liufeng.ui;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

/**
 * @Author liufeng
 * @Description: todo
 * @since 2021/1/12 12:39
 */
public class MusicMenu extends ContextMenu {
    /**
     * 单例
     */
    private static MusicMenu INSTANCE = null;

    /**
     * 私有构造函数
     */
    private MusicMenu() {
        MenuItem playMenuItem = new MenuItem("播放");
        MenuItem modifyMenuItem = new MenuItem("修改");
        MenuItem deleteMenuItem = new MenuItem("删除");
        MenuItem viewMenuItem = new MenuItem("查看信息");
        getItems().addAll(playMenuItem, modifyMenuItem, deleteMenuItem, viewMenuItem);
    }

    /**
     * 获取实例 * @return GlobalMenu
     */
    public static MusicMenu getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MusicMenu();
        }
        return INSTANCE;
    }
}
