package com.my.liufeng.test.mysql.order.controller;

import com.my.liufeng.test.mysql.order.service.IOrderService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Ailwyn
 * @Description: 事务相关测试接口 seata.AT模式
 * @Date 2021/7/8 15:37
 */
@RestController
@RequestMapping("/at")
public class AtController {

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
    public ResponseEntity<Void> test(Boolean hasError) {
        orderService.atTest(hasError);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping("/test2")
    @Transactional(rollbackFor = Exception.class)
    @ShardingTransactionType(TransactionType.BASE)
    public ResponseEntity<Void> test2(Boolean hasError) {
        orderService.atTest2(hasError);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping("/test3")
    public ResponseEntity<Void> test3(Boolean hasError) {
        orderService.atTest3(hasError);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping("/test4")
    public ResponseEntity<Void> test4(Boolean hasError) {
        orderService.atTest4(hasError);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 被调用方没有使用事务时，
     */
    @RequestMapping("/test6")
    public ResponseEntity<Void> test6(Boolean hasError) {
        orderService.atTest6(hasError);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping("/test7")
    public ResponseEntity<Void> test7(Boolean hasError) {
        orderService.atTest7(hasError);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
