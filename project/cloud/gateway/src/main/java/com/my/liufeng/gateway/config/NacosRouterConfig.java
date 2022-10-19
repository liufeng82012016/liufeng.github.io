package com.my.liufeng.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class NacosRouterConfig {
    public static String NACOS_SERVER_ADDR;
    public static String NACOS_NAMESPACE;
    public static String NACOS_ROUTE_DATA_ID;
    public static String NACOS_ROUTE_GROUP;

    @Value("${spring.cloud.nacos.config.server-addr}")
    public void setNacosServerAddr(String nacosServerAddr) {
        NACOS_SERVER_ADDR = nacosServerAddr;
        log.info("nacosServerAddr:{}", nacosServerAddr);
    }

    @Value("${spring.cloud.nacos.config.namespace}")
    public void setNacosNamespace(String nacosNamespace) {
        NACOS_NAMESPACE = nacosNamespace;
        log.info("nacosNamespace:{}", nacosNamespace);
    }

    @Value("${spring.cloud.nacos.config.dataId}")
    public void setNacosRouteDataId(String nacosRouteDataId) {
        NACOS_ROUTE_DATA_ID = nacosRouteDataId;
        log.info("nacosRouteDataId:{}", nacosRouteDataId);
    }

    @Value("${spring.cloud.nacos.config.group}")
    public void setNacosRouteGroup(String nacosRouteGroup) {
        NACOS_ROUTE_GROUP = nacosRouteGroup;
        log.info("nacosRouteGroup:{}", nacosRouteGroup);
    }


}
