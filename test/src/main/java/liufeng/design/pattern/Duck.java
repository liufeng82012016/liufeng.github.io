package liufeng.design.pattern;

/**
 * v1 模拟鸭子，所有的鸭子会游泳、会叫，但是外观可能不能不同
 *  methods:
 *      void quack(){}
 *      void swim(){}
 *      abstract void display();
 * v1.1 让鸭子能飞
 *  methods:
 *      void fly() {}
 * v1.2 新增种类橡皮鸭，不会飞，叫声和鸭子不一样
 * v1.3 新增种类诱饵鸭，不会飞也不会叫
 *
 * v2 新增接口Flyable、Quackable，但是接口代码无法复用（default出现之前）
 *
 * // 设计原则：把会变化的部分取出并封装起来，好让其他部分不会受影响
 *
 */
public abstract class Duck {

    // v1
    public void quack() {
        // 所有的鸭子都会呱呱叫
        System.out.println("呱呱");
    }

    // v1
    public void swim() {
        // 所有的鸭子都会游泳
        System.out.println("游泳～");
    }

    // v1
    // 每个鸭子的外观可能不同
    public abstract void display();

    // v1.1 让鸭子都能飞
    public void fly() {
        System.out.println("鸭子飞起来了");
        // v1.2 橡皮鸭子能飞，坏事了～
    }

}

class GreenDuck extends Duck {

    @Override
    public void display() {
        System.out.println("我是绿头ð");
    }
}

class RedDuck extends Duck {

    @Override
    public void display() {
        System.out.println("我是红头ð");
    }
}

class RubberDuck extends Duck {

    @Override
    public void quack() {
        System.out.println("吱吱");
    }

    @Override
    public void display() {
        System.out.println("我是橡皮ð");
    }
}
