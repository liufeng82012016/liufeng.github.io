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
 * @Description: 从ChoiceBoxDemo复制，目标对象更换了对应的对象
 * @since 2021/5/28 15:56
 */
public class ChoiceBoxDemo1 extends Application {
    private final ChoiceBox<Pair<String, CustomObject>> assetClass = new ChoiceBox<>();

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
    private final static Pair<String, CustomObject> EMPTY_PAIR = new Pair<>("", new CustomObject());

    private void initChoice() {
        List<Pair<String, CustomObject>> classes = new ArrayList<>();
        classes.add(new Pair<>("Easdf", new CustomObject("zhangsan", 11, "31.5KG")));
        classes.add(new Pair<>("Ezxcv", new CustomObject("lisi", 12, "34.5KG")));
        classes.add(new Pair<>("Eqwer", new CustomObject("wangwu", 13, "36.5KG")));
        assetClass.setConverter(new StringConverter<Pair<String, CustomObject>>() {
            @Override
            public String toString(Pair<String, CustomObject> object) {
                return object.getKey();
            }

            @Override
            public Pair<String, CustomObject> fromString(String string) {
                return null;
            }
        });

        assetClass.getItems().add(EMPTY_PAIR);
        assetClass.getItems().addAll(classes);
        assetClass.setValue(EMPTY_PAIR);
    }


    static class CustomObject {
        private String name;
        private Integer id;
        private String weight;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public CustomObject() {
        }

        public CustomObject(String name, Integer id, String weight) {
            this.name = name;
            this.id = id;
            this.weight = weight;
        }
    }
}
