## openfeign

### 可配置项
1. @FeignClient 指定配置类
```java
// 默认使用FeignClientsConfiguration
@FeignClient(name = "product-server",configuration = FeignClientsConfiguration.class )
public interface ProductClient {
    
}
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FeignClient {


    /**
     * A custom configuration class for the feign client. Can contain override
     * <code>@Bean</code> definition for the pieces that make up the client, for instance
     * {@link feign.codec.Decoder}, {@link feign.codec.Encoder}, {@link feign.Contract}.
     *
     * @see FeignClientsConfiguration for the defaults
     * @return list of configurations for feign client
     */
    Class<?>[] configuration() default {};
}
```
2. 异常解码器
```java
@Component
public class CustomFeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            // todo 返回值应返回自定义异常，带code和msg
            Reader reader = response.body().asReader(StandardCharsets.UTF_8);
            String resp = Util.toString(reader);
            JSONObject jsonObject = JSON.parseObject(resp);
            throw new RuntimeException(jsonObject.getString("msg"));
        } catch (IOException e) {
            throw new RuntimeException("服务器异常");
        }
    }
}
```