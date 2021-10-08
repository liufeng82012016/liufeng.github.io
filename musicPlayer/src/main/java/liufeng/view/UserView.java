package liufeng.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import liufeng.controller.UserViewController;

import java.io.IOException;

/**
 * @Author liufeng
 * @Description: todo
 * @since 2020/10/28 15:44
 */
public class UserView {
    private static Scene scene;
    private static UserView userView;

    /**
     * 获取实例
     */
    public static Scene getInstance() {
        if (scene == null) {
            synchronized (LoginView.class) {
                if (scene == null) {
                    userView = new UserView();
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(UserView.class.getClassLoader().getResource("fxml/UserView.fxml"));
                        Parent root = fxmlLoader.load();
                        UserViewController controller = fxmlLoader.getController();
                        controller.bindList();
                        scene = new Scene(root);
                    } catch (IOException e) {
                        //
                    }
                }
            }
        }
        return scene;
    }



}
