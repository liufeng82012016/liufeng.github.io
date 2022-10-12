package com.my.liufeng.rocketmq.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@ResponseBody
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    public Object nullPointException(NullPointerException ex) {
        ex.printStackTrace();
        return new JSONObject().fluentPut("error", "NPE");
    }

    @ExceptionHandler
    public Object nullPointException(NumberFormatException ex) {
        ex.printStackTrace();
        return new JSONObject().fluentPut("error", "Number");
    }

    @ExceptionHandler
    public Object nullPointException(RuntimeException ex) {
        ex.printStackTrace();
        return new JSONObject().fluentPut("error", "Runtime");
    }

}
