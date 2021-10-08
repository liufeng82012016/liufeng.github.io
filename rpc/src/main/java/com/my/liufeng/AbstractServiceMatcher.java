package com.my.liufeng;

/**
 * @Author liufeng
 * @Description: service匹配器
 * @since 2021/5/27 19:50
 */
public abstract class AbstractServiceMatcher {

    abstract <T> T find(Class<T> clazz);
}
