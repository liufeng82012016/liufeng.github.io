package liufeng.proxy.cglib;

import liufeng.proxy.jdk.TargetInterface;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author Ailwyn
 * @Description: todo
 * @Date 2021/8/11 20:26
 */
public class ProxyTest {
    public void hello(String word) {
        System.out.println("hello " + word);
    }

    /**
     * 实现类代理
     */
    @Test
    public void test() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ProxyTest.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                // 打印obj会陷入死循环，原因不明
                //  System.out.println("obj " + obj);
                System.out.println("method " + method);
                System.out.println("args " + Arrays.asList(args));
                System.out.println("proxy " + proxy.getSignature());
                System.out.println("before method run...");
                Object result = proxy.invokeSuper(obj, args);
                System.out.println("after method run...");
                System.out.println("result " + result);
                return result;
            }
        });
        ProxyTest sample = (ProxyTest) enhancer.create();
        sample.hello("world");
    }

    /**
     * 错误的实现接口代理
     */
    @Test
    public void test1() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetInterface.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                // 打印obj会陷入死循环，原因不明
                //  System.out.println("obj " + obj);
                System.out.println("before method run...");
                Object result = proxy.invokeSuper(obj, args);
                System.out.println("after method run...");
                System.out.println("result " + result);
                return result;
            }
        });
        TargetInterface sample = (TargetInterface) enhancer.create();
        sample.doSomeThing();
    }

    /**
     * 正确的代理接口
     */
    @Test
    public void test2() {
        InterfaceTest testCGLib = new InterfaceTest();
        TargetInterface o = (TargetInterface) testCGLib.getInstance(TargetInterface.class);
        /**
         * ???这个实现类干了啥，好像只打印了方法名
         */
        System.out.println(o.doSomeThing());
    }
}
