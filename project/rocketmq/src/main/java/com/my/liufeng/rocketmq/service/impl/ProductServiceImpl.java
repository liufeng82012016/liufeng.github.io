package com.my.liufeng.rocketmq.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.liufeng.rocketmq.entity.Product;
import com.my.liufeng.rocketmq.mapper.TimeOutMapper;
import com.my.liufeng.rocketmq.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl extends ServiceImpl<TimeOutMapper, Product> implements IProductService {


}
