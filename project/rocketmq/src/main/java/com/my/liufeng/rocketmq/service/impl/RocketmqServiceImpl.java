package com.my.liufeng.rocketmq.service.impl;

import com.my.liufeng.rocketmq.entity.Product;
import com.my.liufeng.rocketmq.entity.TransactionLog;
import com.my.liufeng.rocketmq.service.ILogService;
import com.my.liufeng.rocketmq.service.IProductService;
import com.my.liufeng.rocketmq.service.IRocketmqService;
import com.my.liufeng.rocketmq.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class RocketmqServiceImpl implements IRocketmqService {
    @Resource
    IProductService productService;
    @Resource
    ILogService logService;
    @Resource
    RocketMQTemplate rocketMQTemplate;

    String topicAndTag = "transaction:tt";


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendTransactionMsg(boolean throwException) {

        print("before database");
        // 业务逻辑处理
        Product product = new Product();
        product.setCreateTime(new Date());
        productService.save(product);
        // 写入本地消息
        TransactionLog transactionLog = new TransactionLog();
        transactionLog.setTransactionId(String.valueOf(product.getId()));
        logService.save(transactionLog);
        // 发送事务消息
        Message<String> message = MessageBuilder
                .withPayload("hello")
                .setHeader(RocketMQHeaders.TRANSACTION_ID, transactionLog.getId())
                .build();
        print("before message");
        TransactionSendResult transactionSendResult = rocketMQTemplate.sendMessageInTransaction(topicAndTag, message, throwException);
        if (throwException) {
            print(1 / 0);
        }
        print("receive sendResult:" + transactionSendResult);
        if (transactionSendResult.getSendStatus() != SendStatus.SEND_OK) {
            throw new RuntimeException("发送消息失败");
        }
        print("after message");
    }

    @Override
    public void sendCommonMessage() {
        Message<String> message = MessageBuilder
                .withPayload("common msg")
                .build();
        rocketMQTemplate.asyncSend(topicAndTag, message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                print("发送消息成功");
            }

            @Override
            public void onException(Throwable e) {
                print("发送消息失败");
            }
        });
    }

    AtomicInteger index = new AtomicInteger();

    @Override
    public void sendMsgOrderly() {
        Message<String> message = MessageBuilder
                .withPayload("I am ordered:" + index.incrementAndGet())
                .build();
        rocketMQTemplate.sendOneWayOrderly(topicAndTag, message, topicAndTag);
    }

    @Override
    public void sendMsgDelay(int delayLevel) {
        Message<String> message = MessageBuilder
                .withPayload("delay msg " + delayLevel + " now is " + TimeUtil.getTime())
                .build();
        SendResult sendResult = rocketMQTemplate.syncSend(topicAndTag, message, 3000, delayLevel);
        print("send delay message " + delayLevel + ",result:" + sendResult);
    }

    private void print(Object o) {
        log.info("{} {}", TimeUtil.getTime(), o);
    }
}
