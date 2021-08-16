package com.coininn.test.mysql.order.service;


import com.baomidou.mybatisplus.service.IService;
import com.coininn.test.mysql.order.entity.Order;

public interface IOrderService extends IService<Order> {

    /**
     * seate at模式 测试方法
     */
    void atTest(Boolean hasError);

    void atTest2(Boolean hasError);

    void atTest3(Boolean hasError);

    void atTest4(Boolean hasError);

    void mockInsert();

    void atTest6(Boolean hasError);


    void atTest7(Boolean hasError);
}
