# 限流
### 常用限流算法
1. 固定窗口
   1. 优点：固定时间段计数，实现简单，适用不太精准的场景
   2. 对边界没有很好处理，导致限流不能精准控制
2. 滑动窗口
   1. 优点：将固定时间段分块，时间比“计数器”复杂，适用于稍微精准的场景
   2. 缺点：实现稍微复杂，还是不能彻底解决“计数器”存在的边界问题
3. 漏桶算法
   1. 优点：可以很好的控制消费频率
   2. 缺点：实现稍微复杂，单位时间内，不能多消费，感觉不太灵活
4. 令牌桶算法
   1. 优点：可以解决“漏桶”不能灵活消费的问题，又能避免过渡消费，强烈推荐
   2. 缺点：实现稍微复杂
5. Redis + Lua 分布式限流
   1. 优点：支持分布式限流，有效保护下游依赖的服务资源
   2. 缺点：依赖 Redis，对边界没有很好处理，导致限流不能精准控制
### sentinel(https://sentinelguard.io/zh-cn/docs/logs.html)
#### docker 安装(默认版本latest)
1. 搜索镜像：docker search sentinel
2. pull镜像：docker pull bladex/sentinel-dashboard
3. 查看镜像：docker images bladex/sentinel-dashboard
4. 运行容器 docker run --name sentinel -d -p8858:8858 bladex/sentinel-dashboard
5. 查看运行的容器：docker ps
6. 访问本地容器 localhost:8858

#### 入门
1. 特征
   1. 丰富的应用场景，如秒杀、消息削峰填谷、集群流量监控、实时熔断
   2. 完备的实时监控
   3. 广泛的开源生态：如SpringCloud、Dubbo、gRPC
   4. 完善的SPI扩展点
2. 组成
   1. 核心库（Java 客户端）不依赖任何框架/库,能够运行于所有 Java 运行时环境
   2. 控制台
3. 基本概念
   1. 资源
   2. 规则
4. 功能
   1. 流量控制
   2. 熔断降级
      1. 通过并发线程数控制
      2. 通过响应时间对资源降级
   3. 系统负载保护
5. Sentinel规则
   1. 流控规则：监控应用流量QPS或并发线程数等字表，达到阈值时对流量进行控制
      1. 资源名
      2. 针对来源：针对哪个微服务进行限流，默认指default，意思不区分来源，全部限制
      3. 阈值类型：QPS或线程数
      4. 单机阈值
      5. 流控模式
         1. 直接：接口达到限流条件时，开启限流
         2. 关联：当关联的资源达到限流条件时，开启限流
         3. 链路：当从某个接口过来的资源达到限流条件时，开启限流
      6. 流控效果
         1. 快速失败：抛出异常
         2. Warm Up：从开始阈值到最大阈值有一个缓冲时间，一开始是最大阈值的1/3，然后慢慢增长；适合于将突发流量转换为缓步增长的场景
         3. 排队等待：让请求以均匀的速度通过，单机阈值为每秒通过的数量，其他的排队等待；他还会让摄制一个超时时间，当请求超市仍未处理，则会被丢弃
   2. 降级规则
      1. 降级策略
         1. 平均响应时间RT+时间窗口：当平均响应时间超过阈值后，资源进入准降级状态；如果接下来1s内持续进入5个请求，仍超过阈值，则在接下来的时间窗口内对这个资源降级
         2. 异常比例+时间窗口
         3. 异常数+事件窗口
   3. 热点规则：对参数限流
   4. 授权规则：自定义实现RequestOriginParser，可将服务加入黑、白名单
   5. 系统规则
      1. load
      2. RT
      3. 线程数
      4. 入口QPS
      5. CPU使用率
6. @SentinelResource配置
7. Sentinel规则持久化

#### Spring Boot集成
1. 增加依赖
```text
        <!--sentinel依赖-->
        <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-core</artifactId>
            <version>1.8.5</version>
        </dependency>
        <!--sentinel 和dashBoard通信-->
        <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-transport-simple-http</artifactId>
            <version>1.8.5</version>
        </dependency>
        <!--web应用支持-->
        <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-web-servlet</artifactId>
            <version>1.8.5</version>
        </dependency>
```
2. 定义资源
```java
@RestController
@RequestMapping("/resource")
public class ResourceController {
    // 如果定义了拦截器，不需要定义资源，直接用就行
    @SentinelResource("HelloWorld")
    @GetMapping("/time")
    public Object time() {
        return System.currentTimeMillis();
    }
}
```
3. 定义拦截器
```java
@Configuration
public class SentinelFilter {
    static {
        // 设置统一异常处理和url清洗
        WebCallbackManager.setUrlBlockHandler(new WebUrlBlockHandler());
        WebCallbackManager.setUrlCleaner(new WebUrlCleaner());
    }

    @Bean
    public FilterRegistrationBean sentinelFilterRegistration() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new CommonFilter());
        registration.addUrlPatterns("/*");
        registration.setName("sentinelFilter");
        registration.setOrder(1);
        return registration;
    }
}
```
4. 定义流控规则
5. 增加启动参数，启动时链接sentinel服务，如-Dcsp.sentinel.dashboard.server=localhost:8858 -Dproject.name=LearnProject
### hystrix