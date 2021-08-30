/*
 * 文 件 名:  Scanner.java
 * 版    权:  Technologies Co., Ltd. Copyright 2017-2020,  All rights reserved
 * 描    述:  todo 描述
 * 创建人  :  lyh
 * 创建时间:  2020年03月24日
 *
 */

package liufeng;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import liufeng.view.LoginView;
import liufeng.view.MusicView;
import liufeng.view.UserView;

public class Scanner extends Application {

    private static SimpleDoubleProperty wight;
    private static SimpleDoubleProperty high;
    private static Stage primaryStage;

    public static void checkUserView() {
        primaryStage.setScene(UserView.getInstance());
    }

    public static void checkMusicView() {
        primaryStage.setScene(MusicView.getInstance());
    }

    /**
     * 设置窗口最大化 primaryStage.setMaximized(true);
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // 设置窗口尺寸-->除任务栏全部占用
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
//        primaryStage.setX(bounds.getMinX());
//        primaryStage.setY(bounds.getMinY());
//        primaryStage.setWidth(bounds.getWidth());
//        primaryStage.setHeight(bounds.getHeight());
        // 设置窗口
        primaryStage.setTitle("MusicCell");
        primaryStage.getIcons().add(new Image("/img/icon.png"));
        Scanner.primaryStage = primaryStage;

        // 设置场景占据全部窗口
//        double width = primaryStage.getWidth();
//        double height = primaryStage.getHeight();

        primaryStage.setScene(LoginView.getInstance());
        // 设置为固定大小
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static String getUrl(String path) {
        return Scanner.class.getResource(path).toExternalForm();
    }


    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}
