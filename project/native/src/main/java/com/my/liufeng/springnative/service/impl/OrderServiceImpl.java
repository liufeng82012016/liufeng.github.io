package com.my.liufeng.springnative.service.impl;


import com.my.liufeng.springnative.entity.Order;
import com.my.liufeng.springnative.service.IOrderService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OrderServiceImpl implements IOrderService {


    Random random = new Random();

    @Override
    public Order randomInsert(Boolean hasError) {
        //下单操作
        Order order = new Order();
        order.setOrderName("at测试数据");
        order.setBuyNum(2);
        order.setUserId(random.nextInt(10000002));
        return order;
    }

}
