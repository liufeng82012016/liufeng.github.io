package com.my.liufeng.xst.entity;

import javax.servlet.http.HttpServletRequest;

public class Context {
    private HttpServletRequest httpRequest;

    private Projectx projectx;

    public HttpServletRequest getHttpRequest() {
        return httpRequest;
    }

    public void setHttpRequest(HttpServletRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public Projectx getProjectx() {
        return projectx;
    }

    public void setProjectx(Projectx projectx) {
        this.projectx = projectx;
    }
}
