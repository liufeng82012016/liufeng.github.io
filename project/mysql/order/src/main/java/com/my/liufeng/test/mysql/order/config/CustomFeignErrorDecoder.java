package com.my.liufeng.test.mysql.order.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/**
 * 自定义feign异常编码器，如果远程调用抛出了异常，直接抛出去
 *
 * @author lyh
 */
@Component
public class CustomFeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            Reader reader = response.body().asReader(StandardCharsets.UTF_8);
            String resp = Util.toString(reader);
            JSONObject jsonObject = JSON.parseObject(resp);
            throw new RuntimeException(jsonObject.getString("msg"));
        } catch (IOException e) {
            throw new RuntimeException("服务器异常");
        }
    }
}
