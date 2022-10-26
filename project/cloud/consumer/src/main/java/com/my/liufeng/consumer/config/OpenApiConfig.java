package com.my.liufeng.consumer.config;

import io.swagger.v3.oas.models.parameters.HeaderParameter;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * openapi配置类
 *
 * @author lyh
 */
@Configuration
public class OpenApiConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("liufeng")
                .pathsToMatch("/**")
                .displayName("activity模块接口文档")
                .build();
    }


    /**
     * 添加全局的请求头参数，不必要
     */
    @Bean
    public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {
        return openApi -> openApi.getPaths().values().stream().flatMap(pathItem -> pathItem.readOperations().stream())
                .forEach(operation -> {
                    operation.addParametersItem(new HeaderParameter().$ref("#/components/parameters/myGlobalHeader"));
                });
    }

}
