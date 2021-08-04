### springcloud-alibaba  nacos
    1. 注册中心
    2. 配置中心

```text
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
        </dependency>
```

```yaml
spring:
  cloud:
    nacos:
      discovery:
        group: dev
        namespace: c2b80660-8e5c-4181-8590-6e705eb53b3d
        server-addr: 192.168.0.135:8848
#      https://github.com/alibaba/spring-cloud-alibaba/wiki/Nacos-config
#      使用nacos作为配置中心
      config:
#       配置文件dataId=${spring.application.name}-+${spring.profiles.active}+${spring.cloud.nacos.config.file-extension}
        file-extension: yaml
        group: dev
        namespace: c2b80660-8e5c-4181-8590-6e705eb53b3d
        server-addr: 192.168.0.135:8848
  application:
    name: order-server
```  

```java
@SpringBootApplication
@EnableDiscoveryClients
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
```

```text
# 拆分配置文件
# config external configuration
# 1、Data Id 在默认的组 DEFAULT_GROUP,不支持配置的动态刷新
spring.cloud.nacos.config.extension-configs[0].data-id=ext-config-common01.properties

# 2、Data Id 不在默认的组，不支持动态刷新
spring.cloud.nacos.config.extension-configs[1].data-id=ext-config-common02.properties
spring.cloud.nacos.config.extension-configs[1].group=GLOBALE_GROUP

# 3、Data Id 既不在默认的组，也支持动态刷新
spring.cloud.nacos.config.extension-configs[2].data-id=ext-config-common03.properties
spring.cloud.nacos.config.extension-configs[2].group=REFRESH_GROUP
spring.cloud.nacos.config.extension-configs[2].refresh=true
Note1：
多个 Data Id 同时配置时，他的优先级关系是 spring.cloud.nacos.config.extension-configs[n].data-id 其中 n 的值越大，优先级越高。
Note2：
spring.cloud.nacos.config.extension-configs[n].data-id 的值必须带文件扩展名，文件扩展名既可支持 properties，又可以支持 yaml/yml。 此时 spring.cloud.nacos.config.file-extension 的配置对自定义扩展配置的 Data Id 文件扩展名没有影响。

```
   