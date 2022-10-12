package com.my.liufeng.rocketmq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_transaction_log")
public class TransactionLog {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String transactionId;

}
