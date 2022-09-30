package com.my.liufeng.sentinel.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;

/**
 * sentinel默认将每个url识别为1个资源，如果使用了@PathVariable，需要将多个资源识别为1个
 */
public class WebUrlCleaner implements UrlCleaner {
    @Override
    public String clean(String originUrl) {
        return originUrl;
    }
}
