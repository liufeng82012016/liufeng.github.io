package com.my.liufeng.bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author liufeng
 * @Description: todo
 * @since 2021/8/4 19:19
 */
public class ClientSocketContainer {
    private Map<String, NetClient> clientMap = new ConcurrentHashMap<>();

    public void sendMsg(String msg, String serverAddress) {

    }
}
