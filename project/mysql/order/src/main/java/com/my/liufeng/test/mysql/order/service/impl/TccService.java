package com.my.liufeng.test.mysql.order.service.impl;

import com.my.liufeng.test.mysql.order.client.ProductClient;
import com.my.liufeng.test.mysql.order.entity.Order;
import com.my.liufeng.test.mysql.order.service.IOrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * @Author liufeng
 * @Description: todo
 * @since 2021/7/12 16:21
 */
@Service
public class TccService {
    private Random random = new Random();
    @Autowired
    private ProductClient productClient;
    @Autowired
    private IOrderService orderService;

    @Transactional(rollbackFor = Exception.class)
    @GlobalTransactional
    public void tccTest(Boolean hasError) {
        //下单操作
        Order order = new Order();
        order.setOrderName("tcc测试数据");
        order.setBuyNum(2);
        order.setUserId(random.nextInt(10000002));
        orderService.insert(order);
        //减库存（这里参数什么的就自己脑补了）
        productClient.tccTest();

        //异常模拟
        if (hasError) {
            int i = 1 / 0;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @GlobalTransactional
    public void tccTest2(Boolean hasError) {
        productClient.tccTest();

        //异常模拟
        if (hasError) {
            int i = 1 / 0;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void tccTest3(Boolean hasError) {
        //下单操作
        Order order = new Order();
        order.setOrderName("tcc测试数据");
        order.setBuyNum(2);
        order.setUserId(random.nextInt(10000002));
        orderService.insert(order);
        //减库存（这里参数什么的就自己脑补了）
        productClient.tccTest();

        //异常模拟
        if (hasError) {
            int i = 1 / 0;
        }
    }

    @GlobalTransactional
    public void tccTest4(Boolean hasError) {
        //下单操作
        Order order = new Order();
        order.setOrderName("tcc测试数据");
        order.setBuyNum(2);
        order.setUserId(random.nextInt(10000002));
        orderService.insert(order);
        //减库存（这里参数什么的就自己脑补了）
        productClient.tccTest();

        //异常模拟
        if (hasError) {
            int i = 1 / 0;
        }
    }
}
