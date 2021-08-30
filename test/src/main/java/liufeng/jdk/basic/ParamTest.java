package liufeng.jdk.basic;

import org.junit.Test;

/**
 * @Author Ailwyn
 * @Description: todo
 * @Date 2021/8/16 15:58
 */
public class ParamTest {

    /**
     * 值传递（pass by value）:
     * 是指在调用函数时将实际参数复制一份传递到函数中，这样在函数中如果对参数进行修改，将不会影响到实际参数。
     * 引用传递（pass by reference）:
     * 是指在调用函数时将实际参数的地址直接传递到函数中，那么在函数中对参数所进行的修改，将影响到实际参数。
     */

    @Test
    public void test1() {
        Person person = new Person(2, "zhangsan");
        System.out.println(String.format("test1: %s %s", person, person.getAge()));
        modify(person);
        System.out.println(String.format("test1: %s %s", person, person.getAge()));
    }

    @Test
    public void test2() {
        Person person = new Person(2, "zhangsan");
        System.out.println(String.format("test2: %s %s", person, person.getAge()));
        newObject(person);
        System.out.println(String.format("test2: %s %s", person, person.getAge()));
    }

    private void modify(Person person) {
        // 这里的age指向了另外一个地址，原地址的2并没有改变，只是没有指针指向它，将会在垃圾回收的时候被回收掉
        person.setAge(12);
        System.out.println(String.format("modify: %s %s", person, person.getAge()));
    }

    private void newObject(Person person) {
        // 这里的age指向了另外一个地址，原地址的2并没有改变，只是没有指针指向它，将会在垃圾回收的时候被回收掉
        person = new Person(20, "lisi");
        System.out.println(String.format("newObject: %s %s", person, person.getAge()));
    }


    class Person {
        private Integer age;
        private String name;

        public Person() {
        }

        public Person(Integer age, String name) {
            this.age = age;
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
