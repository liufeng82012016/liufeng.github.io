package liufeng.view;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
                    scene = new Scene(loginView.getContainer(), 370, 740);
                    scene.getStylesheets().add("/css/login.css");
                    System.out.println(loginView.text2.getWidth());
                }
            }
        }
        return scene;
    }


    /**
     * 实例化
     */
    private LoginView() {
        container = new VBox();
        container.getStyleClass().add("outer-container");
        // my
        input = new TextField();
        ImageView imageView2 = new ImageView("/img/bird.png");
        text2 = new HBox();
        input.getStyleClass().add("input");
        text2.getStyleClass().add("inner-container");

        text2.getChildren().add(imageView2);
        text2.getChildren().add(input);
        imageView2.setFitHeight(30);
        imageView2.setFitWidth(30);
        passwordField = new PasswordField();
        passwordField.getStyleClass().add("input");
        pwdBox2 = new HBox();
        pwdBox2.getStyleClass().add("inner-container");
        ImageView imageView3 = new ImageView("/img/bird.png");
        pwdBox2.getChildren().add(imageView3);
        pwdBox2.getChildren().add(passwordField);
        imageView3.setFitHeight(30);
        imageView3.setFitWidth(30);
        container.getChildren().add(text2);
        container.getChildren().add(pwdBox2);
        button = new JFXButton("hello , world!");
        button.getStyleClass().add("login-button");
        button.setOnAction(value -> {
            Scanner.checkUserView();
        });
        container.getChildren().add(button);
        nameProperty = new SimpleStringProperty();
        input.textProperty().bindBidirectional(nameProperty);
    }

    public VBox getContainer() {
        return container;
    }

    public TextField getInput() {
        return input;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Button getButton() {
        return button;
    }

    public String getNameProperty() {
        return nameProperty.get();
    }

    public StringProperty namePropertyProperty() {
        return nameProperty;
    }

    public String getPwdProperty() {
        return pwdProperty.get();
    }

    public StringProperty pwdPropertyProperty() {
        return pwdProperty;
    }

}
