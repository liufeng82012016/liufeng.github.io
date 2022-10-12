package com.my.liufeng.rocketmq.config;

import com.my.liufeng.rocketmq.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

/**
 * 自定义rocketmq消费者
 * 顺序消息，配置多个消费者之后，指定consumeMode = ConsumeMode.ORDERLY，保证消费有序
 *
 * @author lyh
 */
@Slf4j
//@Component
@RocketMQMessageListener(consumerGroup = "transaction", topic = "transaction",
        messageModel = MessageModel.CLUSTERING, consumeMode = ConsumeMode.ORDERLY)
public class CustomRocketmqConsumer implements RocketMQListener {

    public CustomRocketmqConsumer() {
        log.info("CustomRocketmqConsumer init");
    }

    @Override
    public void onMessage(Object message) {
        log.info("{} receive msg:{}", TimeUtil.getTime(), message);
    }
}
