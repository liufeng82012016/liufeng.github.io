package com.my.liufeng.consumer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;

/**
 * 健康检查信息
 *
 * @author lyh
 */
@Schema(name = "服务健康信息")
public class HealthVO {
    @SchemaProperty(name = "服务器当前时间")
    private Long time;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
