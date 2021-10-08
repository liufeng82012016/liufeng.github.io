package liufeng.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import liufeng.controller.UserViewController;

import java.io.IOException;

/**
 * @Author liufeng
 * @Description: todo
 * @since 2021/2/6 17:48
 */
public class MusicView {
    private static Scene scene;

    private static MusicView musicView;


    /**
     * 获取实例
     */
    public static Scene getInstance() {
        if (scene == null) {
            synchronized (LoginView.class) {
                if (scene == null) {
                    musicView = new MusicView();
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(UserView.class.getClassLoader().getResource("fxml/MusicDetail.fxml"));
                        Parent root = fxmlLoader.load();
                        UserViewController controller = fxmlLoader.getController();
                        controller.bindList();
                        scene = new Scene(root);
                    } catch (IOException e) {
                        // ignore
                        e.printStackTrace();
                    }
                }
            }
        }
        return scene;
    }
}
