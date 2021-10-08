package com.my.liufeng.serial;

import com.my.liufeng.model.Request;
import com.my.liufeng.model.Response;

/**
 * @Author liufeng
 * @Description: 序列化器
 * @since 2021/5/27 19:30
 */
public interface MsgSerializer {
    String serialize(Request request);

    <T> Response<T> deserialize(String resp);
}
