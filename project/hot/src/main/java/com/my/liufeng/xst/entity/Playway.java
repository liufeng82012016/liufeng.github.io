package com.my.liufeng.xst.entity;

import java.util.HashMap;
import java.util.Map;

public class Playway {

    private String id;

    private Object instance;

    private Map<String, Action> actions;

    public Map<String, Action> getActions() {
        return actions;
    }

    public void addAction(Action action) {
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
