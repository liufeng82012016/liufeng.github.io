package com.my.liufeng.springnative.entity;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Order {

    private Long id;
    private Integer userId;

    private String orderName;

    private Long productId;

    private Integer buyNum;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
    private Boolean isDeleted;

    public Boolean getDeleted() {
        return isDeleted;
    }
}
