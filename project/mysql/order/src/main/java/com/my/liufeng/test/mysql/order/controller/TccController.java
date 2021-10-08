package com.my.liufeng.test.mysql.order.controller;

import com.my.liufeng.test.mysql.order.service.IOrderService;
import com.my.liufeng.test.mysql.order.service.impl.TccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author liufeng
 * @Description: 事务相关测试接口 seata.TCC模式
 * @since 2021/7/9 14:22
 */
@RestController
@RequestMapping("/tcc")
public class TccController {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private TccService tccService;

    /**
     * 1. 本地事务(由数据库操作)+远程事务 -- 回滚
     * 2. 本地事务(无数据库操作)+远程事务 -- 回滚
     * 3. 本地事务注解(@Transactional @GlobalTransactional )只采用@Transactional   -- 不会回滚
     * 4. 本地事务注解(@Transactional @GlobalTransactional )只采用@GlobalTransactional -- 回滚
     */
    @RequestMapping("/test")
    public void test(Boolean hasError) {
        tccService.tccTest(hasError);
    }

    @RequestMapping("/test2")
    public void test2(Boolean hasError) {
        tccService.tccTest2(hasError);
    }


    @RequestMapping("/test3")
    public void test3(Boolean hasError) {
        tccService.tccTest3(hasError);
    }


    @RequestMapping("/test4")
    public void test4(Boolean hasError) {
        tccService.tccTest4(hasError);
    }

}
