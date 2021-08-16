package com.coininn.test.mysql.order.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.coininn.test.mysql.order.client.ProductClient;
import com.coininn.test.mysql.order.entity.Order;
import com.coininn.test.mysql.order.entity.OrderDetail;
import com.coininn.test.mysql.order.mapper.OrderMapper;
import com.coininn.test.mysql.order.service.IOrderDetailService;
import com.coininn.test.mysql.order.service.IOrderService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private ProductClient productClient;
    @Autowired
    private IOrderDetailService orderDetailService;

    Random random = new Random();

    //这里切记不要加@GlobalTransactional
    @Override
    @Transactional(rollbackFor = Exception.class)
    @ShardingTransactionType(TransactionType.BASE)
    public void atTest(Boolean hasError) {
        //下单操作
        Order order = new Order();
        order.setOrderName("at测试数据");
        order.setBuyNum(2);
        order.setUserId(random.nextInt(10000002));
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setAmount(BigDecimal.TEN);
        orderDetail.setProductName("三国志");
        baseMapper.insert(order);
        orderDetail.setId(order.getId());
        orderDetailService.insert(orderDetail);


        //减库存（这里参数什么的就自己脑补了）
        productClient.minusStock();

        //异常模拟
        if (hasError) {
            int i = 1 / 0;
        }
    }

    @Override
    public void atTest2(Boolean hasError) {
        //下单操作
        Order order = new Order();
        order.setOrderName("at测试数据");
        order.setBuyNum(2);
        order.setUserId(random.nextInt(10000002));
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setAmount(BigDecimal.TEN);
        orderDetail.setProductName("三国志");
        baseMapper.insert(order);
        orderDetail.setId(order.getId());
        orderDetailService.insert(orderDetail);
        //减库存（这里参数什么的就自己脑补了）
        productClient.minusStock();

        //异常模拟
        if (hasError) {
            int i = 1 / 0;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @ShardingTransactionType(TransactionType.BASE)
    public void atTest3(Boolean hasError) {
        atTest(hasError);
    }

    @Override
    public void atTest4(Boolean hasError) {
        atTest(hasError);
    }


    @Override
    public void mockInsert() {
        insert(mockOrder(1));
        insert(mockOrder(2));
        insert(mockOrder(3));
        insert(mockOrder(4));
        /**
         * 规则:userId分库，id分表
         * 1413069824457347074, 4   db0 table0
         * 1413069824440569858, 3   db1 table0
         * 1413069824415404033, 2   db0 table1
         * 1413069821009629185  1   db1 table1
         * ds0 ::: INSERT INTO t_order_info_0 ( id,user_id,order_namebuy_num )  VALUES (?, ?, ?, ?) ::: [1413069824457347074, 4, 测试数据, 2]
         * ds1 ::: INSERT INTO t_order_info_0 ( id,user_id,order_namebuy_num )  VALUES (?, ?, ?, ?) ::: [1413069824440569858, 3, 测试数据, 2]
         * ds0 ::: INSERT INTO t_order_info_1 ( id,user_id,order_namebuy_num )  VALUES (?, ?, ?, ?) ::: [1413069824415404033, 2, 测试数据, 2]
         * ds1 ::: INSERT INTO t_order_info_1 ( id,user_id,order_namebuy_num )  VALUES (?, ?, ?, ?) ::: [1413069821009629185, 1, 测试数据, 2]
         */
    }

    @Override
    public void atTest6(Boolean hasError) {
        //下单操作
        Order order = new Order();
        order.setOrderName("at测试数据");
        order.setBuyNum(2);
        order.setUserId(random.nextInt(10000002));
        baseMapper.insert(order);
        productClient.minusStock();
        //异常模拟
        if (hasError) {
            int i = 1 / 0;
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    @ShardingTransactionType(TransactionType.BASE)
    public void atTest7(Boolean hasError) {
        //下单操作
        Order order = new Order();
        order.setOrderName("at测试数据");
        order.setBuyNum(2);
        order.setUserId(random.nextInt(10000002));
        baseMapper.insert(order);
        //减库存（这里参数什么的就自己脑补了）
        productClient.minusStock2();

        //异常模拟
        if (hasError) {
            int i = 1 / 0;
        }
    }


    private Order mockOrder(Integer userId) {
        Order order = new Order();
        order.setOrderName("part测试数据");
        order.setBuyNum(2);
        order.setUserId(userId);
        return order;
    }
}
