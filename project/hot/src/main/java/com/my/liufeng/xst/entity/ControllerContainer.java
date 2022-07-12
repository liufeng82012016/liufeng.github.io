package com.my.liufeng.xst.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * controller容器
 */
public class ControllerContainer {

    private String id;

    private Object instance;

    private Map<String, MethodContainer> actions;

    public Map<String, MethodContainer> getActions() {
        return actions;
    }

    public void addAction(MethodContainer action) {
        if (this.actions == null) {
            this.actions = new HashMap<>();
        }
        this.actions.put(action.getId(), action);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }
}
