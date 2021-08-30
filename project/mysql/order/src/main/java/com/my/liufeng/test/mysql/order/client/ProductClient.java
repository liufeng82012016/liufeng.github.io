package com.my.liufeng.test.mysql.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("product-server")
public interface ProductClient {

    /**
     * 扣除库存(开启事务)
     */
    @PutMapping("/minus/stock")
    void minusStock();


    /**
     * 扣除库存(不开启事务)
     */
    @PutMapping("/minus/stock2")
    void minusStock2();

    /**
     * tcc测试
     */
    @PutMapping("/tcc/test")
    void tccTest();

}
