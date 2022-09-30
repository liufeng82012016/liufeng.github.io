package com.my.liufeng.exception;

public class ValidateException extends RuntimeException {
    private String msg;
    private Integer code;

    public ValidateException(String msg) {
        super(msg);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
