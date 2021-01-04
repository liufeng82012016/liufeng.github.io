---
layout: page
title: "问答：Faqs"
---
记录工作中遇到的一些小问题及解决办法，以防忘记后重头再找答案。

#### 采坑记（SpringCloudAlibaba，springcloud版本为Hoxton.SR8，其他见 https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E）
1. [springcloud]  [feign] Get/Post请求参数 需要加上@RequestParam(value="")，post请求可以当做表单接受（springboot自动对request.getParameter()进行可封装）否则可能导致参数无法识别。如果Post请求参数为对象且没有加任何注解，参数默认以application/json格式传递，对应方法需要用@RequestBody注解接受。
2. [springcloud]  [feign] 方法返回值为null时，feign得到的返回值不为null？feign会默认封装一个参数都为默认值的空对象。
3. [springcloud]  [feign] 如何转发上一个请求的请求头到feign调用？继承feign.RequestInterceptor，重写apply方法，代码如下：
    ```java
    public class FeignInterceptor implements RequestInterceptor {
        @Override
        public void apply(RequestTemplate requestTemplate) {
            try {
                Map<String, String> headers = getHeaders();
                for (String headerName : headers.keySet()) {
                    requestTemplate.header(headerName, headers.get(headerName));
                    // 这里要注意请求头数据过长的问题，默认是2048Bytes
                    log.info("add feign header [{}] [{}]",headerName,headers.get(headerName));
                }
            } catch (Exception e) {
                // 异常处理
            }
        }
        private Map<String, String> getHeaders() {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (requestAttributes == null) {
                return null;
            }
            HttpServletRequest request = (requestAttributes).getRequest();
            Map<String, String> map = new LinkedHashMap<>();
            Enumeration<String> enumeration = request.getHeaderNames();
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                String value = request.getHeader(key);
                map.put(key, value);
            }
            return map;
        }
    }
    ```
4. [springcloud]  [feign] feign默认超时时间很短，需要额外配置。
7. [springcloud]  [feign] 自定义encoder，decoder，errorDecoder，logLevel，客户端等，目前只用到了errorDecoder。
    ```java
    // ErrorDecoder和Decoder类似，只是继承的类不同
    public class FeignErrorDecoder implements feign.codec.ErrorDecoder {
        @Override
        public Exception decode(String s, Response response) {
            try {
                InputStream inputStream = response.body().asInputStream();
                byte[] buffer = new byte[1024];
                int length = 0;
                StringBuilder builder = new StringBuilder();
                while ((length = inputStream.read(buffer)) != -1) {
                    builder.append(new String(buffer, 0, length));
                }
                responseBody = builder.toString();
                // 读取响应内容，进行逻辑处理
            } catch (IOException e) {
                // 异常处理;
            }
            // 抛出自定义异常，然后用springboot的异常处理器直接处理，或者用try...catch...捕获
            return new RuntimeException();
        }
    }
    // feign配置类，只需要实例化响应的bean即可，feign会自动根据类型去装配
    @Configuration
    public class FeignConfig {
        @Bean
        public ErrorDecoder errorDecoder() {
            return new FeignDecoder();
        }
        @Bean
        Logger.Level feignLoggerLevel() {
            // 这里记录所有，根据实际情况选择合适的日志level
            return Logger.Level.FULL;
        }
    }
    // FeignClient指定配置
    @FeignClient(name = ServiceNameConstants.USER_SERVICE, path = "/user", configuration = {FeignConfig.class})
    public interface RemoteUserService {}
    ```
5. [springcloud]  [zipkin] 某个历史版本中zipkin-starter和redis-starter的Lettuce连接池不兼容（疑问），无法连接redis服务端。后面版本注意调用顺序，服务启动完成前最好不使用redis进行初始化操作，如PostContruct注解。
6. [springcloud]  [nacos] nacos在Linux系统默认以集群方式启动（启动参数中-m standlone无效，虽然windows可以），数据保存在mysql中。如果没有配置数据库，大概率无法启动。可以修改config下application.properties，修改启动模式为standlone。
11. [springboot] [redis] 使用springboot提供的注解时，自定义每个方法的缓存时间。
    ```java
    // 自定义cacheManager
    public class RedisConfig{
         class CustomRedisCacheManager extends RedisCacheManager {
            public CustomRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
                super(cacheWriter, defaultCacheConfiguration);
            }
            @Override
            protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
                // cacheName中的key和时间用#分割 -- 可自定义
                String[] array = StringUtils.delimitedListToStringArray(name, "#");
                name = array[  0];
                // 解析TTL -- 可自定义格式
                if (array.length > 1) {
                    long ttl = Long.parseLong(array[1]);
                    // 注意单位 -- 可自定义单位
                    cacheConfig = cacheConfig.entryTtl(Duration.ofMillis(ttl));
                }
                return super.createRedisCache(name, cacheConfig);
            }
        }
        // bean 初始化
        @Bean
        public CacheManager cacheManager(RedisConnectionFactory factory) {
            RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                    .entryTtl(Duration.ofDays(1))
                    // 所有由springboot-cache框架管理的key都使用这个前缀，可随时刷新
                    .computePrefixWith(cacheName -> "cache:" + cacheName);
            CustomRedisCacheManager redisCacheManager = new CustomRedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(factory), defaultCacheConfig);
            return redisCacheManager;
        }
    }
    // 待完成 springboot-data-redis执行流程
    ```
13. [springboot] [redis] 部分序列化算法没有保存类信息，无法反序列化。可能是版本原因？
8. [jdk] [cglib 代理] 普通注解如果没有@Inherited，在代理类无法用可能无法直接获取。
9. [jdk] SimpleDateFormat线程不安全，可以使用DateTimeFormater，或者线程私有化该变量
10. [mysql] 8.0版本第一次连接是可能会报错Public Key Retrieval is not allowed。使用navicat连接后恢复正常。可参考https://mysqlconnector.net/connection-options/
12. [jdk] [javafx] sceneBuilder和css的样式，有时候不生效？
#### 或作为url收藏记录
1,Markdown语法：<http://wowubuntu.com/markdown/basic.html>  
2,Oracle现在真是恶心，下载个Java SDK还非得让登陆不可。只好再找下载地址了: <http://ghaffarian.net/downloads/>  
3,git文章收藏:  
Git分支管理策略: <http://www.ruanyifeng.com/blog/2012/07/git.html>    
Git查看、删除、重命名远程分支和tag: http://zengrong.net/post/1746.htm  
4,Google无障碍浏览链接：<https://github.com/greatfire/wiki>  
5,在线Android等源码：<http://www.grepcode.com/>, <http://androidxref.com/>  
6,Twitter University: <http://www.bluemobi.cn/>  
更多请看本文评论，欢迎来盖楼！
