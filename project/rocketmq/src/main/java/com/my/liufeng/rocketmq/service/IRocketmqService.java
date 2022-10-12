package com.my.liufeng.rocketmq.service;

public interface IRocketmqService {

    /**
     * 发送事务消息
     */
    void sendTransactionMsg(boolean throwException);

    /**
     * 发送普通消息
     */
    void sendCommonMessage();

    /**
     * 发送顺序消息
     */
    void sendMsgOrderly();

    /**
     * 发送延迟消息
     */
    void sendMsgDelay(int delayLevel);

}
