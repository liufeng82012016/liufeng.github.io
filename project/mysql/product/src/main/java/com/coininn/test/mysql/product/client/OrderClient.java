package com.coininn.test.mysql.product.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "order-server",path = "/order")
public interface OrderClient {

    /**
     * tcc测试
     */
    @PutMapping("/tcc/test")
    void tccTest(@RequestParam(value = "hasError") Boolean hasError);

    @PutMapping("/tcc/test3")
    void tccTest3(@RequestParam(value = "hasError") Boolean hasError);


    @PutMapping("/tcc/test4")
    void tccTest4(@RequestParam(value = "hasError") Boolean hasError);

}
