### doc https://springdoc.org/
1. 添加maven依赖
```text
    依赖
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-ui</artifactId>
        <version>1.4.5</version>
    </dependency>
```
2. 集成
    1. 拦截器配置
    ```text
        @Bean
        public GroupedOpenApi publicApi() {
            return GroupedOpenApi.builder()
                    .setGroup("jinjiang")
                    .pathsToMatch("/**")
                    .build();
        }
       
       
        /**
         * 添加全局的请求头参数
         */
        @Bean
        public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {
            return openApi -> openApi.getPaths().values().stream().flatMap(pathItem -> pathItem.readOperations().stream())
                    .forEach(operation -> {
                        operation.addParametersItem(new HeaderParameter().$ref("#/components/parameters/myGlobalHeader"));
                    });
        }
    ```
    2. 文件配置
3. 访问url： http://host:port/servlet.context-path/swagger-ui.html
4. 方法注解
```text
        @Operation(summary = "title",
            parameters = {
                    @Parameter(name = "parameterName", required = true, description = "desc"),
            },
            responses = {
                    @ApiResponse(description = "")
            })
```
5. 类注解
    ```java
        @Schema(name="***", description="***")
        public class Person {
            @Schema(description = "***")
            private String name;
        
        }
    ```