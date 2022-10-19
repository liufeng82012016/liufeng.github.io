package com.my.liufeng.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {
    @Resource
    private RouteDefinitionWriter routeDefinitionWriter;
    @Resource
    private RouteDefinitionLocator routeDefinitionLocator;
    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    /**
     * 删除路由
     */
    public String delete(String id) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(id)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            log.info("delete route:{} success", id);
            return "delete success";
        } catch (Exception e) {
            log.warn("delete route:{} fail", id, e);
            return "delete fail";
        }
    }

    /**
     * 更新路由列表
     */
    public String updateList(List<RouteDefinition> definitions) {
        List<RouteDefinition> routeDefinitionExists = routeDefinitionLocator.getRouteDefinitions().buffer().blockFirst();
        if (!CollectionUtils.isEmpty(routeDefinitionExists)) {
            routeDefinitionExists.forEach(routeDefinition -> {
                delete(routeDefinition.getId());
            });
        }
        definitions.forEach(this::updateById);
        return "success";
    }

    /**
     * 更新单个路由
     */
    private String updateById(RouteDefinition definition) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
            this.routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            log.info("update route:{} success", definition.getId());
            return "update success";
        } catch (Exception e) {
            log.warn("update route:{} fail", definition.getId(), e);
            return "update fail";
        }
    }

    /**
     * 添加路由
     */
    public String add(RouteDefinition definition) {
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        log.info("add route:{} success", definition.getId());
        return "success";
    }

}