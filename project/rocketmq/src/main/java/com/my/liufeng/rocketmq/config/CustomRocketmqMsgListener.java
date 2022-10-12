package com.my.liufeng.rocketmq.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * MessageListenerConcurrently 并发消费模式
 * MessageListenerOrderly 顺序消费模式
 * 根据实际情况选择
 */
@Slf4j
@Component(value = "messageListener")
public class CustomRocketmqMsgListener implements MessageListenerConcurrently {
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        log.info("consumer size:{} msg:{} context:{}", msgs.size(), msgs, context);
//        ConsumeConcurrentlyStatus consumeConcurrentlyStatus = Math.random() > 0.5D ? ConsumeConcurrentlyStatus.RECONSUME_LATER : ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//        if (consumeConcurrentlyStatus == ConsumeConcurrentlyStatus.CONSUME_SUCCESS) {
//            for (MessageExt messageExt : msgs) {
//                log.info("消费消息：{}", new String(messageExt.getBody(), StandardCharsets.UTF_8));
//            }
//        } else {
//            for (MessageExt messageExt : msgs) {
//                log.info("重消费消息：{}", new String(messageExt.getBody(), StandardCharsets.UTF_8));
//            }
//        }

        ConsumeConcurrentlyStatus consumeConcurrentlyStatus = ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        for (MessageExt messageExt : msgs) {
            log.info("消费消息：{}", new String(messageExt.getBody(), StandardCharsets.UTF_8));
        }

        return consumeConcurrentlyStatus;
    }

    //    @Override
    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
        log.info("consumer size:{} msg:{} context:{}", msgs.size(), msgs, context);
        ConsumeOrderlyStatus consumeOrderlyStatus = Math.random() > 0.5D ? ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT : ConsumeOrderlyStatus.SUCCESS;
        if (consumeOrderlyStatus == ConsumeOrderlyStatus.SUCCESS) {
            for (MessageExt messageExt : msgs) {
                log.info("消费消息：{}", new String(messageExt.getBody(), StandardCharsets.UTF_8));
            }
        } else {
            for (MessageExt messageExt : msgs) {
                log.info("重消费消息：{}", new String(messageExt.getBody(), StandardCharsets.UTF_8));
            }
        }
        return consumeOrderlyStatus;
    }
}
