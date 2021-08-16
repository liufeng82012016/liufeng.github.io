package liufeng.modul;

import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import liufeng.http.Response;
import liufeng.util.ThreadPoolUtil;

import java.util.Objects;

/**
 * @author itqn
 */
public class ProgressStage<T> {
    private Stage stage;
    private Task<T> work;

    private ProgressStage() {
    }

    /**
     * 创建
     *
     * @param parent
     * @param work
     * @param ad
     * @return
     */
    public static ProgressStage of(Stage parent, Task<?> work, String ad) {
        ProgressStage ps = new ProgressStage();
        ps.work = Objects.requireNonNull(work);
        ps.initUI(parent, ad);
        return ps;
    }

    /**
     * 显示
     */
    public Task<T> show() {
        ThreadPoolUtil.submit(work);
        stage.show();
        return work;
    }

    private void initUI(Stage parent, String ad) {
        stage = new Stage();
        stage.initOwner(parent);
        // style
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        // message
        ProgressIndicator indicator = new ProgressIndicator();
        indicator.setProgress(-1);
        indicator.progressProperty().bind(work.progressProperty());
        // pack
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setBackground(Background.EMPTY);
        vBox.getChildren().add(indicator);
        // scene
        Scene scene = new Scene(vBox);
        scene.setFill(null);
        stage.setScene(scene);
        stage.setWidth(ad.length() * 8 + 10);
        stage.setHeight(100);
        // show center of parent
        double x = parent.getX() + (parent.getWidth() - stage.getWidth()) / 2;
        double y = parent.getY() + (parent.getHeight() - stage.getHeight()) / 2;
        stage.setX(x);
        stage.setY(y);
        // close if work finish
        work.setOnSucceeded(e -> {
            try {
                System.out.println(e.getSource().getValue());
                Response response = (Response) e.getSource().getValue();
                Thread.sleep(3000L);
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}