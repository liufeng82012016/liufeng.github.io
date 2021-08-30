package com.my.liufeng.serial;

import com.my.liufeng.model.Request;
import com.my.liufeng.model.Response;

/**
 * @Author Ailwyn
 * @Description: 序列化器
 * @Date 2021/5/27 19:30
 */
public interface MsgSerializer {
    String serialize(Request request);

    <T> Response<T> deserialize(String resp);
}
