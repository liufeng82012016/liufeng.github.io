package com.my.liufeng.rocketmq.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.liufeng.rocketmq.entity.TransactionLog;
import com.my.liufeng.rocketmq.mapper.LogMapper;
import com.my.liufeng.rocketmq.service.ILogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, TransactionLog> implements ILogService {

}
