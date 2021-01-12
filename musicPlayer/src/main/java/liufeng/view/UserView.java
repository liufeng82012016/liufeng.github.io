package liufeng.view;

import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import liufeng.controller.UserViewController;
import liufeng.util.RandomUtil;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Ailwyn
 * @Description: todo
 * @Date 2020/10/28 15:44
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
