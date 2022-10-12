package com.my.liufeng.rocketmq.config;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RocketmqConfiguration {
    @Value("${rocketmq.consumer.topic}")
    private String topic;
    @Value("${rocketmq.name-server}")

    private String namesrvAddr;


    @Bean
    public DefaultMQPushConsumer defaultMQPushConsumer(MessageListener mqMsgListener) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("transaction");
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.registerMessageListener(mqMsgListener);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.setConsumeMessageBatchMaxSize(10);
        try {
            consumer.subscribe(topic, "tt");
            consumer.start();
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        }
        return consumer;
    }

}
