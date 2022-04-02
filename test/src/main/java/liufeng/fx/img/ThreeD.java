package liufeng.fx.img;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import liufeng.fx.util.FastApplication;

public class ThreeD extends FastApplication {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public Parent getRoot() {
        return new XformWithPivot();
    }

    static class XformWithPivot extends Group {
        public Translate t = new Translate();
        public Translate p = new Translate();
        public Translate ip = new Translate();
        public Rotate rx = new Rotate();

        {
            rx.setAxis(Rotate.X_AXIS);
        }

        public Rotate ry = new Rotate();

        {
            ry.setAxis(Rotate.Y_AXIS);
        }

        public Rotate rz = new Rotate();

        {
            rz.setAxis(Rotate.Z_AXIS);
        }

        public Scale s = new Scale();

        public XformWithPivot() {
            super();
            getTransforms().addAll(t, p, rz, ry, rx, s, ip);
        }
    }
}

