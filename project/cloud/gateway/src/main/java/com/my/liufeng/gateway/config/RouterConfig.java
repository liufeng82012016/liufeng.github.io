package com.my.liufeng.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * 自定义路由配置
 *
 * @author liufeng
 */
@Slf4j
//@Configuration
public class RouterConfig {

    @Bean
    public RouteLocator customRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/activity/**")
                        .filters(f -> f.addRequestHeader("Hello", "world"))
                        .uri("http://localhost:8002"))
                .build();
    }

}
