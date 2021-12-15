package two;

import javafx.scene.Parent;
import javafx.scene.Scene;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SceneFactory {
    private static ConcurrentHashMap<String, Scene> sceneMap = new ConcurrentHashMap<>();

    private static Map<String, String> sceneNameMap = new HashMap<>();

    static {
        sceneNameMap.put("", "");
    }

    public static Scene getScene(String name) {
        Scene scene = sceneMap.get(name);
        if (scene == null) {
            Class<?> clazz = null;
            try {
                clazz = Class.forName(sceneNameMap.get(name));
                synchronized (clazz) {
                    if ((scene = sceneMap.get(name)) == null) {
                        Method method = clazz.getMethod("newInstance", clazz);
                        Object invoke = method.invoke(null);
                        scene = new Scene((Parent) invoke);
                        sceneMap.put(name, scene);
                    }
                }
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                throw new RuntimeException(String.format("class %s not found", name));
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return scene;
    }
}
