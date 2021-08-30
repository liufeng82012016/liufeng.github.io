### 网关(spring-cloud-gateway)

#### 集成
1. 引入依赖
```text
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-gateway</artifactId>
        <exclusions>
            <exclusion>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-web</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
```
2. 路由转发/自定义拦截器/使用自带拦截器都可


#### 自定义拦截器
```java
@Slf4j
@Component
public class GateWayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 逻辑处理 返回值随便写的，按照需求改
        ServerHttpResponse response = exchange.getResponse();
        DataBuffer dataBuffer = response.bufferFactory().wrap("String body".getBytes());
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Mono.just(dataBuffer));
    }

    @Override
    public int getOrder() {
        // 过滤器顺序，数值越小，越先执行
        return -200;
    }

}
```

#### 自定义异常处理器
```java
    @Slf4j
    @Component
    public class JsonExceptionHandler implements ErrorWebExceptionHandler {
    
        @Override
        public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
            ServerHttpResponse response = exchange.getResponse();
            if (response.isCommitted()) {
                return Mono.error(ex);
            }
            log.warn("gateway catch exception,ex = [{}]",ex);
            response.setStatusCode(HttpStatus.OK);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            DataBuffer dataBuffer = response.bufferFactory().wrap("String body".getBytes());
            return response.writeWith(Mono.just(dataBuffer));
        }
    }
```


#### 使用gateway内置限流
```java
    @Configuration
    public class RateLimitResolver {
    
        /**
         * 官方文档：https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.2.2.RELEASE/reference/html/#gatewayfilter-factories
         * 注意，Mono.just(object)方法不接受null参数，会抛出NullPointerException
         * RedisRequestLimiter使用的令牌桶算法，需要配置容量和每秒发放令牌次数
         * The redis-rate-limiter.replenishRate 每秒新增令牌数
         * The redis-rate-limiter.burstCapacity 令牌桶容量
         * The redis-rate-limiter.requestedTokens 每个请求消耗的容量数，默认1
         * 被限流时将返回HttpStatus.429 Too Many Requests
         */
    
        /**
         * ip限流
         */
        @Bean
        @Primary
        public KeyResolver hostAddrKeyResolver() {
            return exchange -> {
                String hostName = exchange.getRequest().getRemoteAddress().getHostName();
                System.out.println("host resolver : " + hostName);
                return Mono.just(hostName);
            };
        }
    
        /**
         * 用户限流
         */
        @Bean
        public KeyResolver userKeyResolver() {
            return exchange -> {
                HttpHeaders headers = exchange.getRequest().getHeaders();
                String innerToken = headers.getFirst("header");
                System.out.println("user resolver : " + innerToken);
                return Mono.just(innerToken);
            };
        }
    
        /**
         * 接口限流
         */
        @Bean
        public KeyResolver pathKeyResolver() {
            return exchange -> {
                String path = exchange.getRequest().getPath().toString();
                System.out.println("path resolver : " + path);
                return Mono.just(path);
            };
        }
    }
```

#### 路由转发配置示例
```json
    [{
        "id": "example-router",
        "order": 0,
        "predicates": [{
            "args": {
                "pattern": "/example/**"
            },
            "name": "Path"
        }],
        "uri": "lb://example-service"
    }]
```