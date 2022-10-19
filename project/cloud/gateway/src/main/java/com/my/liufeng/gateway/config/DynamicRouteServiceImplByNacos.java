package com.my.liufeng.gateway.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.fasterxml.jackson.core.type.TypeReference;
import com.my.liufeng.gateway.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

@Slf4j
@Component
@DependsOn({"nacosRouterConfig"})
public class DynamicRouteServiceImplByNacos {

    @Resource
    private DynamicRouteServiceImpl dynamicRouteService;
    private ConfigService configService;

    /**
     * 项目启动后初始化路由
     */
    @PostConstruct
    public void init() {
        try {
            initConfigService();
            if (configService == null) {
                return;
            }
            String config = configService.getConfig(NacosRouterConfig.NACOS_ROUTE_DATA_ID, NacosRouterConfig.NACOS_ROUTE_GROUP, 3000);
            log.info("nacos dynamic route init.configStr:{}", config);
            List<RouteDefinition> routeDefinitions = JsonUtils.read(config, new TypeReference<List<RouteDefinition>>() {
            });
            routeDefinitions.forEach(routeDefinition -> dynamicRouteService.add(routeDefinition));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 添加config-listener，监听配置变化
        dynamicRouteByNacosListener(NacosRouterConfig.NACOS_ROUTE_DATA_ID, NacosRouterConfig.NACOS_ROUTE_GROUP);
    }

    public void dynamicRouteByNacosListener(String dataId, String group) {
        try {
            configService.addListener(dataId, group, new Listener() {

                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    List<RouteDefinition> routeDefinitions = JsonUtils.read(configInfo, new TypeReference<List<RouteDefinition>>() {
                    });
                    log.info("nacos dynamic route update.configStr:{}", configInfo);
                    dynamicRouteService.updateList(routeDefinitions);
                }
            });
        } catch (Exception e) {
            log.info("nacos dadd ynamic route listener fail", e);
        }
    }

    /**
     * 初始化Nacos-configService
     */
    public void initConfigService() {
        try {
            Properties properties = new Properties();
            properties.put("serverAddr", NacosRouterConfig.NACOS_SERVER_ADDR);
            properties.put("namespace", NacosRouterConfig.NACOS_NAMESPACE);
            this.configService = NacosFactory.createConfigService(properties);
        } catch (Exception e) {
            log.error("nacos configService init fail", e);
        }
    }
}
