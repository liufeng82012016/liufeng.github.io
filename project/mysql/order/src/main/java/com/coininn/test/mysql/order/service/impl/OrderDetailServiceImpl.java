package com.coininn.test.mysql.order.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.coininn.test.mysql.order.client.ProductClient;
import com.coininn.test.mysql.order.entity.Order;
import com.coininn.test.mysql.order.entity.OrderDetail;
import com.coininn.test.mysql.order.mapper.OrderDetailMapper;
import com.coininn.test.mysql.order.mapper.OrderMapper;
import com.coininn.test.mysql.order.service.IOrderDetailService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {

}
