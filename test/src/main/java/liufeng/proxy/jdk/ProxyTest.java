package liufeng.proxy.jdk;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author Ailwyn
 * @Description: todo
 * @Date 2021/8/11 19:57
 */
public class ProxyTest {
    @Test
    public void test1() {
        //invoke 代表的是执行代理对象的方法
        //method：代表目标对象的方法字节码对象
        //args:代表目标对象的响应的方法的参数
        TargetInterface newProxyInstance = (TargetInterface) Proxy.newProxyInstance(
                Target1.class.getClassLoader(),
                new Class[]{TargetInterface.class},
                (proxy, method, args) -> {
                    System.out.println("before invoke");
                    Object invoke = method.invoke(new Target1(), args);
                    System.out.println("after invoke");
                    return invoke;
                });

        newProxyInstance.doSomeThing();
    }

    @Test
    public void test2() {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Target2 target = new Target2();
        TargetInterface newProxyInstance = (TargetInterface) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //反射知识点
                        System.out.println("before invoke");
                        Object invoke = method.invoke(target, args);
                        System.out.println("after invoke");
                        return invoke;
                    }
                });
        newProxyInstance.doSomeThing();
    }

    @Test
    public void test3() {
        TargetInterface newProxyInstance = (TargetInterface) Proxy.newProxyInstance(
                TargetInterface.class.getClassLoader(),
                new Class[]{
                        TargetInterface.class
                },
                new InterfaceProxy());
        newProxyInstance.doSomeThing();
    }
}


class Target1 implements TargetInterface {

    @Override
    public String doSomeThing() {
        System.out.println("target1 doSomeThing running...");
        return "aaa";
    }

}

class Target2 implements TargetInterface {

    @Override
    public String doSomeThing() {
        System.out.println("target2 doSomeThing running...");
        return "aaa";
    }

}

class InterfaceProxy<T> implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke");
        Object invoke;
        if (proxy.getClass().equals(method.getDeclaringClass())) {
            // method.getDeclaringClass() 获取声明这个类的class对象
            invoke = method.invoke(this, args);
            System.out.println("parent");
        } else {
            // mybatis在这里执行sql
            System.out.println(this);
            invoke = null;
        }
        System.out.println("after invoke");
        return invoke;
    }
}
