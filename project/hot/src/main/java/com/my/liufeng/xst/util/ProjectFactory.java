package com.my.liufeng.xst.util;

import com.my.liufeng.xst.annotations.CustomMethod;
import com.my.liufeng.xst.annotations.CustomController;
import com.my.liufeng.xst.cl.ClassAdapter;
import com.my.liufeng.xst.cl.ExcludeClassLoader;
import com.my.liufeng.xst.constants.Config;
import com.my.liufeng.xst.entity.MethodContainer;
import com.my.liufeng.xst.entity.ControllerContainer;
import com.my.liufeng.xst.entity.Project;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProjectFactory {
    private static final ConcurrentHashMap<String, Project> projectMap = new ConcurrentHashMap<>();

    public static Project getProject(String projectId) {
        Project project = projectMap.get(projectId);
        if (project == null) {
            synchronized (projectId.intern()) {
                project = projectMap.get(projectId);
                if (project != null && project.getLastUpdateTime() == project.getUpdateTime()) {
                    Thread.currentThread().setContextClassLoader(project.getClassLoader());
                    System.out.println("hit project:" + project);
                    return project;
                }
                if (project == null) {
                    project = new Project();
                    project.setName(projectId);
                    projectMap.put(projectId, project);
                    System.out.println("init project:" + projectId);
                } else {
                    project.clearControllers();
                    System.out.println("reload project:" + projectId);
                }
                project.setClassLoader(new ExcludeClassLoader());
                buildProject(project);
            }
        } else {
            // 判断是否需要重新加载
            if (project.getLastUpdateTime() != project.getUpdateTime()) {
                project.setClassLoader(new ExcludeClassLoader());
                Thread.currentThread().setContextClassLoader(project.getClassLoader());
                System.out.println("reload2 project:" + projectId);
                buildProject(project);
            } else {
                System.out.println("hit project" + project);
            }
        }
        Thread.currentThread().setContextClassLoader(project.getClassLoader());
        return project;
    }

    /**
     * 根据项目id解析代码，构建项目
     *
     * @param project 项目实体类
     */
    private static void buildProject(Project project) {
        // 获取工作目录
        Config config = ApplicationContextUtil.get(Config.class);
        String excludeDir = config.getExcludeDir();
        String targetDir = excludeDir + File.separator + project.getName();
        // 解析工作目录
        ClassAdapter classAdapter = ApplicationContextUtil.get(ClassAdapter.class);
        ExcludeClassLoader excludeClassLoader = (ExcludeClassLoader) project.getClassLoader();
        Map<String, byte[]> classMap = classAdapter.loadLocalDir(targetDir);
        excludeClassLoader.setClassMap(classMap);
        // 遍历class，加载玩法

        // 复制map，loadClass对map更改；遍历+更改将导致报错
        HashMap<String, byte[]> copiedClassMap = new HashMap<>(classMap);
        for (Map.Entry<String, byte[]> entry : copiedClassMap.entrySet()) {
            String className = entry.getKey();
            if (!className.contains("Controller")) {
                // 根据命名匹配
                continue;
            }
            try {
                // 解析玩法
                Class<?> clazz = excludeClassLoader.loadClass(className);
                CustomController controllerAnnotation = clazz.getAnnotation(CustomController.class);
                if (controllerAnnotation == null) {
                    continue;
                }
                Object instance = clazz.newInstance();
                ControllerContainer playway = new ControllerContainer();
                playway.setId(controllerAnnotation.playwayId());
                playway.setInstance(instance);
                project.addController(playway);
                System.out.println("add controller: " + playway.getId());


                // 解析方法
                Method[] declaredMethods = clazz.getDeclaredMethods();
                for (Method method : declaredMethods) {
                    CustomMethod customRequestAction = method.getAnnotation(CustomMethod.class);
                    if (customRequestAction == null) {
                        continue;
                    }
                    MethodContainer action = new MethodContainer();
                    action.setMethod(method);
                    action.setId(customRequestAction.actionId());
                    playway.addAction(action);
                    System.out.println("add action: " + action.getId());
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        // 设置时间
        project.setLastUpdateTime(System.currentTimeMillis());
        project.setUpdateTime(project.getLastUpdateTime());
    }
// todo 监听文件更改
//    public static void
}
