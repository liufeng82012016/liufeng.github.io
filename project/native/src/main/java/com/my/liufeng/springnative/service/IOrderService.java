package com.my.liufeng.springnative.service;


import com.my.liufeng.springnative.entity.Order;

public interface IOrderService  {

    /**
     * 写入随机数据
     */
    Order randomInsert(Boolean hasError);

}
