# 注册中心

### springcloud-alibaba  nacos
1. 注册中心
2. 配置中心

#### 依赖
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
#### 注册中心-配置
```yaml
spring:
  cloud:
    nacos:
      discovery:
        group: dev
        namespace: setIfExist
        server-addr: 127.0.0.1:8848
#      https://github.com/alibaba/spring-cloud-alibaba/wiki/Nacos-config
#      使用nacos作为配置中心
      config:
#       配置文件dataId=${spring.application.name}-+${spring.profiles.active}+${spring.cloud.nacos.config.file-extension}
        file-extension: yaml
        group: dev
        namespace: setIfExist
        server-addr: 127.0.0.1:8848
  application:
    name: order-server
```  
#### 启动应用
```java
@SpringBootApplication
@EnableDiscoveryClients
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
```
#### 配置中心配置
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

### spring cloud  euraka（最终一致）
#### 注册服务存储结构
1. 内存中存取服务注册信息：ConcurrentHashMap<String,Map<String,Lease<InstanceInfo>>> registry
   1. String是服务名称
   2. Map是多个服务示例
      1. String是服务实例的id（生成规则？？）
      2. Lease维护每个服务最近一次发送心跳的心跳，InstanceInfo是服务实例的具体信息
2. 多级缓存
   1. 拉取注册表
      1. 首先从ReadOnlyCacheMap里查询缓存的注册表
      2. 若无，从ReadWriteCacheMap查询
      3. 若无，从内存获取实际的注册表，填充各级缓存
   2. 更新注册表
      1. 更新注册表
      2. 过期掉ReadWriteCacheMap
      3. 一段时间内（默认30是），各服务拉取注册表直接读ReadOnlyCacheMap
      4. 当Euraka Server的后台线程发现ReadWriteCacheMap已经清空，则会过期掉ReadOnlyCacheMap
3. 缺点
   1. 单个节点保存了所有服务的信息，占用内存较大

### consul
1. 功能描述
   1. 服务注册和发现（Raft，强一致，leader推送给大多数follower才算成功）
   2. 健康检查（每个服务有一个agent代理，它把不健康的节点信息推送给server）
   3. kv存储（支持存放一些额外的信息）
   4. 安全的服务通信（服务间授权）
   5. 多数据中心支持
2. 

   