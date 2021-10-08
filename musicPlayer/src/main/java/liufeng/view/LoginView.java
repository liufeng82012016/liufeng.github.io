package liufeng.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;


/**
 * @Author liufeng
 * @Description: todo
 * @since 2020/10/24 14:58
 */
public class LoginView {
    private static Scene scene;

    private static LoginView loginView;


    /**
     * 获取实例
     */
    public static Scene getInstance() {
        if (scene == null) {
            synchronized (LoginView.class) {
                if (scene == null) {
                    loginView = new LoginView();
                    try {
                        scene = new Scene(FXMLLoader.load(UserView.class.getClassLoader().getResource("fxml/LoginView.fxml")));
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
