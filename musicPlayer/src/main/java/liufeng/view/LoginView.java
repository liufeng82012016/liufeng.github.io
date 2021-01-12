package liufeng.view;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import liufeng.Scanner;
import liufeng.util.RandomUtil;

import java.io.IOException;
import java.util.Base64;
import java.util.Random;


/**
 * @Author Ailwyn
 * @Description: todo
 * @Date 2020/10/24 14:58
 */
public class LoginView {
    private static Scene scene;
    private VBox container;
    private TextField input;
    private PasswordField passwordField;
    private Button button;
    private StringProperty nameProperty;
    private StringProperty pwdProperty;
    private HBox text2;
    private HBox pwdBox2;

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
