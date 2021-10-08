package liufeng.modul;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @Author liufeng
 * @Description: todo
 * @since 2021/1/5 21:03
 */
public class Music {
    private String icon = "img/bird.png";
    private StringProperty title = new SimpleStringProperty("title");
    private StringProperty content = new SimpleStringProperty("content");

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getContent() {
        return content.get();
    }

    public StringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }
}
