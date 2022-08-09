package liufeng.design.pattern;

/**
 * 1. 将变化的部分与不变剥离开来
 * 2. 面向接口变成，而不是面向实现变成
 *
 * 步骤
 * 1. 定义FlyBehavior和QuackBehavior
 * 2. 定义FlyBehavior和QuackBehavior实现类
 * 3. Duck类增加行为成员变量
 * 4. 增加getter和setter方法
 */
public abstract class Duck2 {
    protected FlyBehavior flyBehavior;
    protected QuackBehavior quackBehavior;

    public void swim() {
        System.out.println("All duck float,even decoys");
    }

    public abstract void display();

    void performQuack() {
        quackBehavior.quark();
    }

    void performFly() {
        flyBehavior.fly();
    }

    public FlyBehavior getFlyBehavior() {
        return flyBehavior;
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public QuackBehavior getQuackBehavior() {
        return quackBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}

/**
 * 飞行行为 接口
 */
interface FlyBehavior {
    void fly();
}

class FlyWithWings implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("fly~");
    }
}

class FlyNoWay implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("not fly~");
    }
}

/**
 * 鸣叫行为
 */
interface QuackBehavior {
    void quark();
}

class Quack implements QuackBehavior {

    @Override
    public void quark() {
        System.out.println("呱呱叫");
    }
}

class Squack implements QuackBehavior {

    @Override
    public void quark() {
        System.out.println("吱吱叫");
    }
}

class MuteQuack implements QuackBehavior {

    @Override
    public void quark() {
        System.out.println("不会叫");
    }
}
