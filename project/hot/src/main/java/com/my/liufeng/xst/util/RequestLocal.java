package com.my.liufeng.xst.util;

import com.my.liufeng.xst.entity.Context;
import com.my.liufeng.xst.entity.Project;

import javax.servlet.http.HttpServletRequest;

public class RequestLocal {
    private static ThreadLocal<Context> requestThreadLocal = new ThreadLocal<>();

    public static void setContext(HttpServletRequest request, Project project) {
        Context context = requestThreadLocal.get();
        if (context == null) {
            context = new Context();
            requestThreadLocal.set(context);
        }
        context.setHttpRequest(request);
        context.setproject(project);
    }

    public static Context get() {
        return requestThreadLocal.get();
    }

    public static void remove() {
        requestThreadLocal.remove();
    }
}

