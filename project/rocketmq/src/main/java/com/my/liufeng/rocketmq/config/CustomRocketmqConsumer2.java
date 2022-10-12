package com.my.liufeng.rocketmq.config;

import com.my.liufeng.rocketmq.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

/**
 * 自定义rocketmq消费者
 *
 * @author lyh
 */
@Slf4j
//@Component
@RocketMQMessageListener(consumerGroup = "transaction", topic = "transaction",
        messageModel = MessageModel.CLUSTERING,consumeMode = ConsumeMode.CONCURRENTLY)
public class CustomRocketmqConsumer2 implements RocketMQListener {
    @Override
    public void onMessage(Object message) {
        log.info("{} receive msg:{}", TimeUtil.getTime(), message);
    }
}
