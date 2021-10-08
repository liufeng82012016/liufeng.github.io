package learn;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author liufeng
 * @Description: todo
 * @since 2021/5/28 15:56
 */
public class ChoiceBoxDemo extends Application {
    private final ChoiceBox<Pair<String, String>> assetClass = new ChoiceBox<>();

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label label = new Label("Asset Class:");
        assetClass.setPrefWidth(200);
        Button saveButton = new Button("Save");

        HBox hbox = new HBox(
                label,
                assetClass,
                saveButton);
        hbox.setSpacing(10.0d);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(40));

        Scene scene = new Scene(hbox);

        initChoice();

        saveButton.setOnAction(
                (evt) -> System.out.println("saving " + assetClass.getValue())
        );

        primaryStage.setTitle("ChoicesApp");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * 通常会给空值，而不是null
     */
    private final static Pair<String, String> EMPTY_PAIR = new Pair<>("", "");

    private void initChoice() {
        List<Pair<String, String>> classes = new ArrayList<>();
        classes.add(new Pair<>("Easdf", "20000"));
        classes.add(new Pair<>("Ezxcv", "21000"));
        classes.add(new Pair<>("Eqwer", "22000"));
        assetClass.setConverter(new StringConverter<Pair<String, String>>() {
            @Override
            public String toString(Pair<String, String> object) {
                return object.getKey();
            }

            @Override
            public Pair<String, String> fromString(String string) {
                return null;
            }
        });

        assetClass.getItems().add(EMPTY_PAIR);
        assetClass.getItems().addAll(classes);
        assetClass.setValue(EMPTY_PAIR);
    }
}
