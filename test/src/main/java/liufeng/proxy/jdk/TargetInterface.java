package liufeng.proxy.jdk;

/**
 * @Author liufeng
 * @Description: 被代理的接口类 生成的代理类继承了Proxy，由于java是单继承，所以只能实现接口，通过接口实现
 * @since 2021/8/11 19:56
 */
public interface TargetInterface {

    String doSomeThing();

}
