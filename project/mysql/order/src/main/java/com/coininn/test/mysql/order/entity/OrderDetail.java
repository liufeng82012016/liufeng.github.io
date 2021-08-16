package com.coininn.test.mysql.order.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@TableName("t_order_detail")
public class OrderDetail {

    @TableId
    private Long id;

    private String productName;

    private BigDecimal amount;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
