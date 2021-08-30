package com.my.liufeng.test.mysql.product.service;


import io.seata.rm.tcc.api.LocalTCC;

@LocalTCC
public interface ILogService {


    void forward1(Boolean hasError);

    void forward2(Boolean hasError);

    void forward3(Boolean hasError);
}
