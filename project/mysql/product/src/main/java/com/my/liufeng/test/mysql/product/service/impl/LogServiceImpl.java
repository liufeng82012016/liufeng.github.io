package com.my.liufeng.test.mysql.product.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.my.liufeng.test.mysql.product.client.OrderClient;
import com.my.liufeng.test.mysql.product.entity.Log;
import com.my.liufeng.test.mysql.product.mapper.LogMapper;
import com.my.liufeng.test.mysql.product.service.ILogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

    @Autowired
    private OrderClient orderClient;

    @Override
    public void forward1(Boolean hasError) {
        orderClient.tccTest(hasError);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void forward2(Boolean hasError) {
        insert(init("forward2"));
        orderClient.tccTest(hasError);
    }

    @Override
    public void forward3(Boolean hasError) {
        insert(init("forward3"));
        orderClient.tccTest(hasError);
    }

    private Log init(String event) {
        Log log = new Log();
        log.setEvent(event);
        return log;
    }
}
