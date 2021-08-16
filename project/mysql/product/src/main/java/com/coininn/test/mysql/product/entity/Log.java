package com.coininn.test.mysql.product.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_log")
public class Log {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String event;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
