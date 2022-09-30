package com.my.liufeng.springnative.controller;

import com.my.liufeng.springnative.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author liufeng
 * @Description: 事务相关测试接口 seata.AT模式
 * @since 2021/7/8 15:37
 */
@RestController
@RequestMapping("/at")
public class TestController {

    @Autowired
    private IOrderService orderService;
    /**
     * 测试场景(覆盖spring.@Transactional生效和失效的场景)
     * 1. 注解在serviceImpl方法，由controller调用
     * 2. 注解在controller方法，由接口调用
     * 3. 注解在serviceImpl方法，由带注解的serviceImpl方法平级调用
     * 4. 注解在serviceImpl方法，由不带注解的serviceImpl方法平级调用
     * 5. 被调用方法使用事务 同上
     * 6. 调用方未使用事务，被调用方法使用事务 --- 不会自动回滚
     * 7. 调用方使用事务，被调用方法未使用事务 ---- 会自动回滚
     */

    /**
     * @param hasError 是否抛出异常
     */
    @RequestMapping("/test")
    public Object test(Boolean hasError) {
        return orderService.randomInsert(hasError);
    }

}
