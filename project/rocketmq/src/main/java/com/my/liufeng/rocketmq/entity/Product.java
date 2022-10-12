package com.my.liufeng.rocketmq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 测试事务消息超时的场景
 */
@Data
@TableName("t_time_out")
public class Product {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Date createTime;

}
