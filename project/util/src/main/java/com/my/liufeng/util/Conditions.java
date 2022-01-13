package com.my.liufeng.util;


import com.my.liufeng.exception.ValidateException;

public class Conditions {
    /**
     * 非空校验，若obj为null，抛出异常
     *
     * @param obj obj
     * @param msg 异常提示语
     */
    public static void expectNonNull(Object obj, String msg) {
        if (obj == null) {
            throw new ValidateException(msg);
        }
    }

    public static void expectNull(Object obj, String msg) {
        if (obj != null) {
            throw new ValidateException(msg);
        }
    }


    public static void expectTrue(boolean value, String msg) {
        if (!value) {
            throw new ValidateException(msg);
        }
    }

    public static void expectFalse(boolean value, String msg) {
        if (value) {
            throw new ValidateException(msg);
        }
    }
}
