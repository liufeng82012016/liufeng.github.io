package two;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import two.constants.SceneConstants;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = SceneFactory.getScene(SceneConstants.INDEX);
        primaryStage.setScene(scene);
        primaryStage.setTitle("音乐播放器");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
