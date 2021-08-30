package com.my.liufeng.test.mysql.order.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.my.liufeng.test.mysql.order.entity.Order;
import com.my.liufeng.test.mysql.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/part")
public class PartController {

    @Autowired
    private IOrderService orderService;


    /**
     * 测试场景
     * 1. 写入  生成4条数据，分别满足分表分库的条件(理论上应当写入2个数据库的2个表，各一条数据)
     * 2. selectById()
     * 3. selectByCondition()
     * 4. selectPage()
     */
    @RequestMapping("/page")
    public ResponseEntity<Page<Order>> page(Page page) {
        page.setSize(10);
        return new ResponseEntity<>(orderService.selectPage(page, Condition.empty()), HttpStatus.OK);
    }

    @RequestMapping("/get")
    public ResponseEntity<Order> get(@RequestParam Long id) {
        return new ResponseEntity<>(orderService.selectById(id), HttpStatus.OK);
    }

    @RequestMapping("/list")
    public ResponseEntity<List<Order>> list() {
        // 条件可重写
        Wrapper condition = Condition.create()
                .orderBy("id",false)
                .last("limit 10");
        return new ResponseEntity<>(orderService.selectList(condition), HttpStatus.OK);
    }

    @RequestMapping("/insert")
    public ResponseEntity<Void> insert() {
        orderService.mockInsert();
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
