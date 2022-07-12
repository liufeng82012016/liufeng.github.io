package com.my.liufeng.xst.entity;

import javax.servlet.http.HttpServletRequest;

/**
 * 上下文环境
 */
public class Context {
    private HttpServletRequest httpRequest;

    private Project project;

    public HttpServletRequest getHttpRequest() {
        return httpRequest;
    }

    public void setHttpRequest(HttpServletRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public Project getproject() {
        return project;
    }

    public void setproject(Project project) {
        this.project = project;
    }
}
