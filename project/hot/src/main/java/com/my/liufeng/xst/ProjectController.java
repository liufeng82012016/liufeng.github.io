package com.my.liufeng.xst;

import com.my.liufeng.xst.entity.MethodContainer;
import com.my.liufeng.xst.entity.ControllerContainer;
import com.my.liufeng.xst.entity.Project;
import com.my.liufeng.xst.util.ProjectFactory;
import com.my.liufeng.xst.util.RequestLocal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping(value = "project")
public class ProjectController {

    @RequestMapping(value = "{projectId}/{playwayId}/{actionId}.do")
    public Object hello(@PathVariable String projectId, @PathVariable String playwayId, @PathVariable String actionId, HttpServletRequest request) {
        Project project = ProjectFactory.getProject(projectId);
        ControllerContainer controller = project.getControllers().get(playwayId);
        if (controller == null) {
            return "not found controller";
        }
        MethodContainer action = controller.getActions().get(actionId);
        if (action == null) {
            return "not found action";
        }
        try {
            RequestLocal.setContext(request, project);
            return action.getMethod().invoke(controller.getInstance());
        } catch (IllegalAccessException | InvocationTargetException e) {
            return "unknown error";
        }
    }
}
