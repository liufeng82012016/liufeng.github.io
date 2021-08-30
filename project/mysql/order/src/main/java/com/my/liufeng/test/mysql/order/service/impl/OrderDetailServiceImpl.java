package com.my.liufeng.test.mysql.order.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.my.liufeng.test.mysql.order.entity.OrderDetail;
import com.my.liufeng.test.mysql.order.mapper.OrderDetailMapper;
import com.my.liufeng.test.mysql.order.service.IOrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {

}
