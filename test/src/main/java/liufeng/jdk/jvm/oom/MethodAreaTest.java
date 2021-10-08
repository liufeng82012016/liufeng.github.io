package liufeng.jdk.jvm.oom;

import java.lang.reflect.Proxy;

public class MethodAreaTest {

    public static void main(String[] strings) {
        while (true) {
            TargetInterface newProxyInstance = (TargetInterface) Proxy.newProxyInstance(
                    Target1.class.getClassLoader(),
                    new Class[]{TargetInterface.class},
                    (proxy, method, args) -> {
                        System.out.println("before invoke");
                        Object invoke = method.invoke(new Target1(), args);
                        System.out.println("after invoke");
                        return invoke;
                    });

//            newProxyInstance.doSomeThing();
        }
    }

    static class Target1 implements TargetInterface {

        @Override
        public String doSomeThing() {
//            System.out.println("target1 doSomeThing running...");
            return "aaa";
        }

    }

}

