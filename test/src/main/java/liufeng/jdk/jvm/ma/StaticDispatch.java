package liufeng.jdk.jvm.ma;

/**
 * 静态分派：在编译期间已经确定了参数类型
 */
public class StaticDispatch {
    abstract static class Human {

    }

    static class Man extends Human {

    }

    static class Woman extends Human {

    }

    void sayHello(Human human) {
        System.out.println("hello,human");
    }

    void sayHello(Man man) {
        System.out.println("hello,man");
    }

    void sayHello(Woman woman) {
        System.out.println("hello,woman");
    }

    public static void main(String[] args) {
        StaticDispatch staticDispatch = new StaticDispatch();
        Human man = new Man();
        staticDispatch.sayHello(man);

        Human woman = new Woman();
        staticDispatch.sayHello(woman);
    }
}
