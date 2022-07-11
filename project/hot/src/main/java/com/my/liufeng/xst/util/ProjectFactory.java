package com.my.liufeng.xst.util;

import com.my.liufeng.xst.annotations.CustomRequestAction;
import com.my.liufeng.xst.annotations.PlaywayInstane;
import com.my.liufeng.xst.cl.ClassAdapter;
import com.my.liufeng.xst.cl.ExcludeClassLoader;
import com.my.liufeng.xst.constants.Config;
import com.my.liufeng.xst.entity.Action;
import com.my.liufeng.xst.entity.Playway;
import com.my.liufeng.xst.entity.Projectx;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProjectFactory {
    private static final ConcurrentHashMap<String, Projectx> projectMap = new ConcurrentHashMap<>();

    public static Projectx getProject(String projectId) {
        Projectx projectx = projectMap.get(projectId);
        if (projectx == null) {
            synchronized (projectId.intern()) {
                projectx = projectMap.get(projectId);
                if (projectx != null && projectx.getLastUpdateTime() == projectx.getUpdateTime()) {
                    Thread.currentThread().setContextClassLoader(projectx.getClassLoader());
                    System.out.println("hit project:" + projectx);
                    return projectx;
                }
                if (projectx == null) {
                    projectx = new Projectx();
                    projectx.setName(projectId);
                    projectMap.put(projectId, projectx);
                    System.out.println("init project:" + projectId);
                } else {
                    projectx.clearPlayways();
                    System.out.println("reload project:" + projectId);
                }
                projectx.setClassLoader(new ExcludeClassLoader());
                buildProject(projectx);
            }
        } else {
            // 判断是否需要重新加载
            if (projectx.getLastUpdateTime() != projectx.getUpdateTime()) {
                projectx.setClassLoader(new ExcludeClassLoader());
                Thread.currentThread().setContextClassLoader(projectx.getClassLoader());
                System.out.println("reload2 project:" + projectId);
                buildProject(projectx);
            } else {
                System.out.println("hit project" + projectx);
            }
        }
        Thread.currentThread().setContextClassLoader(projectx.getClassLoader());
        return projectx;
    }

    /**
     * 根据项目id解析代码，构建项目
     *
     * @param projectx 项目实体类
     */
    private static void buildProject(Projectx projectx) {
        // 获取工作目录
        Config config = ApplicationContextUtil.get(Config.class);
        String excludeDir = config.getExcludeDir();
        String targetDir = excludeDir + File.separator + projectx.getName();
        // 解析工作目录
        ClassAdapter classAdapter = ApplicationContextUtil.get(ClassAdapter.class);
        ExcludeClassLoader excludeClassLoader = (ExcludeClassLoader) projectx.getClassLoader();
        Map<String, byte[]> classMap = classAdapter.loadLocalDir(targetDir);
        excludeClassLoader.setClassMap(classMap);
        // 遍历class，加载玩法
        projectx.setLastUpdateTime(System.currentTimeMillis());
        projectx.setUpdateTime(projectx.getLastUpdateTime());

        for (Map.Entry<String, byte[]> entry : classMap.entrySet()) {
            String className = entry.getKey();
            if (!className.contains("Playway")) {
                // 根据命名匹配
                continue;
            }
            try {
                // 解析玩法
                Class<?> playwayClass = excludeClassLoader.loadClass(className);
                PlaywayInstane playwayClassAnnotation = playwayClass.getAnnotation(PlaywayInstane.class);
                if (playwayClassAnnotation == null) {
                    continue;
                }
                Object instance = playwayClass.newInstance();
                Playway playway = new Playway();
                playway.setId(playwayClassAnnotation.playwayId());
                playway.setInstance(instance);
                projectx.addPlayways(playway);
                System.out.println("add playway: " + playway.getId());


                // 解析方法
                Method[] declaredMethods = playwayClass.getDeclaredMethods();
                for (Method method : declaredMethods) {
                    CustomRequestAction customRequestAction = method.getAnnotation(CustomRequestAction.class);
                    if (customRequestAction == null) {
                        continue;
                    }
                    Action action = new Action();
                    action.setMethod(method);
                    action.setId(customRequestAction.actionId());
                    playway.addAction(action);
                    System.out.println("add action: " + action.getId());
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

    }
// todo 监听文件更改
//    public static void
}
