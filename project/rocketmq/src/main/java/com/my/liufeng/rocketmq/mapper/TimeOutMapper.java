package com.my.liufeng.rocketmq.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.my.liufeng.rocketmq.entity.Product;
import org.apache.ibatis.annotations.Update;

public interface TimeOutMapper extends BaseMapper<Product> {

    @Update("update t_product_info set stock = stock-1 where id = 1")
    void minusStock();

    @Update("update t_product_info set stock = stock+1 where id = 1")
    void addStock();
}
