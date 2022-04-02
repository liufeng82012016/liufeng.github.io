package liufeng.fx.img;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import liufeng.fx.util.FastApplication;

/**
 * 简单3D徒刑
 */
public class Demo1 extends FastApplication {
    @Override
    public Parent getRoot() {
        int x = 100, y = 150;
        Box box = new Box(x, x, x);
        Cylinder cylinder = new Cylinder(x, y);
//        Cylinder cylinder1 = new Cylinder(x, y, 3);
        Sphere sphere = new Sphere(x);
//        Sphere sphere1 = new Sphere(x, 3);

        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setSpecularColor(Color.ORANGE);
        redMaterial.setDiffuseColor(Color.RED);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.BLUE);
        blueMaterial.setSpecularColor(Color.LIGHTBLUE);

        final PhongMaterial greyMaterial = new PhongMaterial();
        greyMaterial.setDiffuseColor(Color.DARKGREY);
        greyMaterial.setSpecularColor(Color.GREY);

        box.setMaterial(redMaterial);
        cylinder.setMaterial(blueMaterial);
        sphere.setMaterial(greyMaterial);

        Group group = new Group();
        group.getChildren().addAll(box, cylinder, sphere);
        return group;
    }


    public static void main(String[] args) {
        launch(args);

    }
}
