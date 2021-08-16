package com.coininn.test.mysql.product.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.coininn.test.mysql.product.entity.Product;
import org.apache.ibatis.annotations.Update;

public interface ProductMapper extends BaseMapper<Product> {

    @Update("update t_product_info set stock = stock-1 where id = 1")
    void minusStock();

    @Update("update t_product_info set stock = stock+1 where id = 1")
    void addStock();
}
