package com.my.liufeng.rocketmq.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.my.liufeng.rocketmq.entity.TransactionLog;
import com.my.liufeng.rocketmq.service.ILogService;
import com.my.liufeng.rocketmq.service.IProductService;
import com.my.liufeng.rocketmq.utils.TimeUtil;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;

import javax.annotation.Resource;

/**
 * 自定义本地事务监听器，用于rocketmq回查事务是否成功
 */
@RocketMQTransactionListener
public class CustomTransactionListener implements RocketMQLocalTransactionListener {
    @Resource
    ILogService logService;
    @Resource
    IProductService productService;



    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        // 当发送事务消息prepare(half)成功后，调用该方法执行本地事务
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            //
        }
        System.out.println(TimeUtil.getTime() + " 执行本地事务" + msg);
        // 事务消息会一致阻塞
        return checkLocalTransaction(msg);
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        // 如果没有收到生产者发送的Half Message的响应，broker发送请求到生产 者回查生产者本地事务的状态
        // 该方法用于获取本地事务执行的状态。
        System.out.println(TimeUtil.getTime() + " 检查本地事务的状态：" + msg);
        Object transactionId = msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        System.out.println(TimeUtil.getTime() + " 检查本地事务的状态,transactionId:" + transactionId);
        QueryWrapper<TransactionLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", transactionId);
        TransactionLog log = logService.getOne(queryWrapper);
        return log != null ? RocketMQLocalTransactionState.COMMIT : RocketMQLocalTransactionState.UNKNOWN;
    }
}
