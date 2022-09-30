package com.my.liufeng.sentinel.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 没生效？？
 */
@Component
public class RequestOriginParserDefinition implements RequestOriginParser {

    public RequestOriginParserDefinition(){
        System.out.println("RequestOriginParserDefinition init");
    }
    @Override
    public String parseOrigin(HttpServletRequest request) {
        String origin = request.getParameter("origin");
        System.out.println("origin: " + origin);
        return origin;
    }
}
