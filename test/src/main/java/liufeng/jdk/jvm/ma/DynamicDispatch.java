package liufeng.jdk.jvm.ma;

/**
 * 动态分配
 */
public class DynamicDispatch {
    abstract static class Human {
        abstract void sayHello();
    }

    static class Man extends Human {

        @Override
        void sayHello() {
            System.out.println("hello,man");
        }
    }

    static class Woman extends Human {

        @Override
        void sayHello() {
            System.out.println("hello,woman");
        }
    }


    public static void main(String[] args) {
        Human man = new Man();
        man.sayHello();

        Human woman = new Woman();
        woman.sayHello();
    }
}
