package com.my.liufeng.test.mysql.product.controller;


import com.my.liufeng.test.mysql.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    Random random = new Random();

    @PutMapping("/minus/stock")
    public ResponseEntity<Void> minusStock() {
        productService.minusStock();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping("/minus/stock2")
    public ResponseEntity<Void> minusStock2() {
        productService.minusStock2();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 被调用的Tcc方法
     *
     * @return
     */
    @PutMapping("/tcc/test")
    public ResponseEntity<Void> tccTest() {
        productService.prepareTestTcc(random.nextInt(1000));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
