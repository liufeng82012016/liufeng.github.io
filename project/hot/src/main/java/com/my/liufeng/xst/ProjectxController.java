package com.my.liufeng.xst;

import com.my.liufeng.xst.entity.Action;
import com.my.liufeng.xst.entity.Playway;
import com.my.liufeng.xst.entity.Projectx;
import com.my.liufeng.xst.util.ProjectFactory;
import com.my.liufeng.xst.util.RequestLocal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping(value = "projectx")
public class ProjectxController {

    @RequestMapping(value = "{projectId}/{playwayId}/{actionId}.do")
    public Object hello(@PathVariable String projectId, @PathVariable String playwayId, @PathVariable String actionId, HttpServletRequest request) {
        Projectx projectx = ProjectFactory.getProject(projectId);
        Playway playway = projectx.getPlayways().get(playwayId);
        if (playway == null) {
            return "not found playway";
        }
        Action action = playway.getActions().get(actionId);
        if (action == null) {
            return "not found action";
        }
        try {
            RequestLocal.setContext(request, projectx);
            return action.getMethod().invoke(playway.getInstance());
        } catch (IllegalAccessException | InvocationTargetException e) {
            return "unknown error";
        }
    }
}
