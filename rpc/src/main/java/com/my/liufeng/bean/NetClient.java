package com.my.liufeng.bean;

import com.my.liufeng.serial.MsgSerializer;

/**
 * @Author Ailwyn
 * @Description: todo
 * @Date 2021/5/27 19:48
 */
public class NetClient {
    private MsgSerializer msgSerializer;

    private Integer serverPort;
    private String serverAddress;

    public NetClient() {
        // 不建立连接，等待请求打过来，走注册中心获取端口和ip
    }


    public NetClient(Integer serverPort, String serverAddress) {
        this.serverPort = serverPort;
        this.serverAddress = serverAddress;
        // 建立服务端连接
    }
}
