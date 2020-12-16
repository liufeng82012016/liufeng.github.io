package liufeng.view;

import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import liufeng.util.RandomUtil;

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
    private VBox container;
    private ListView<String> content;
    private PasswordField passwordField;
    private Button button;
    private StringProperty nameProperty;
    private StringProperty pwdProperty;
    private HBox buttonContainer;
    private HBox pwdBox2;

    private static UserView userView;
    private ObservableList<String> stringList;

    /**
     * 获取实例
     */
    public static Scene getInstance() {
        if (scene == null) {
            synchronized (LoginView.class) {
                if (scene == null) {
                    userView = new UserView();
                    scene = new Scene(userView.getContainer(), 370, 740);
                    scene.getStylesheets().add("/css/user.css");
                }
            }
        }
        return scene;
    }

    private UserView() {
        container = new VBox();
        content = new JFXListView();
        stringList = FXCollections.observableArrayList();
        stringList.add("hello");
        stringList.add("world");
        // 添加列表
        content.itemsProperty().set(stringList);
        container.getChildren().add(content);
        content.getStyleClass().add("content");
        // 添加底部button
        buttonContainer = new HBox();
        buttonContainer.getStyleClass().add("button-container");

        Button chat = new Button("chat");
        Button music = new Button("music");
        Button my = new Button("my");
        chat.getStyleClass().add("bottom-button");
        music.getStyleClass().add("bottom-button");
        my.getStyleClass().add("bottom-button");

        buttonContainer.getChildren().add(chat);
        buttonContainer.getChildren().add(music);
        buttonContainer.getChildren().add(my);
        container.getChildren().add(buttonContainer);
        new Thread(() -> {
            while (true) {
                if (stringList.size() < 50) {
                    stringList.add(RandomUtil.getRandomCode(5));
                } else {
                    stringList.remove(0);
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public VBox getContainer() {
        return container;
    }
}
