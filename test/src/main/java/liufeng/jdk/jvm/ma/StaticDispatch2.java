package liufeng.jdk.jvm.ma;

/**
 * 静态分派：在编译期间已经确定了参数类型
 */
public class StaticDispatch2 {

    // order:6
//    static void sayHello(Object arg) {
//        System.out.println("hello,Object");
//    }
    // order:2
//    static void sayHello(int arg) {
//        System.out.println("hello,int");
//    }
    // order:3
//    static void sayHello(long arg) {
//        System.out.println("hello,long");
//    }
    // order:4
//    static void sayHello(Character arg) {
//        System.out.println("hello,Character");
//    }
    // order:1
//    static void sayHello(char arg) {
//        System.out.println("hello,char");
//    }
    // order:7
    static void sayHello(char... arg) {
        System.out.println("hello,char[]");
    }
    // order:5
//    static void sayHello(Serializable arg) {
//        System.out.println("hello,Serializable");
//    }

    public static void main(String[] args) {
        sayHello('a');
    }
}
