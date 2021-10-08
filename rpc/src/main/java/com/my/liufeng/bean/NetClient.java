package com.my.liufeng.bean;

import com.my.liufeng.serial.MsgSerializer;
import com.my.liufeng.utils.IpUtil;

import java.nio.channels.Channel;

/**
 * @Author liufeng
 * @Description: todo
 * @since 2021/5/27 19:48
 */
public class NetClient {
    private MsgSerializer msgSerializer;

    private String serverHost;
    private Integer serverPort;
    private Channel channel;

    public NetClient() {
        // 不建立连接，等待请求打过来，走注册中心获取端口和ip
    }

    public NetClient(String serverAddress) {
        String[] strings = IpUtil.splitAddress(serverAddress);
        this.serverHost = strings[0];
        this.serverPort = Integer.parseInt(strings[1]);
    }


    public NetClient(Integer serverPort, String serverHost) {
        this.serverPort = serverPort;
        this.serverHost = serverHost;
        // 建立服务端连接
    }

    public void sendMsg(String msg){

    }
}
