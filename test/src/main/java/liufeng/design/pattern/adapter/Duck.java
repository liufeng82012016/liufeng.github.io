package liufeng.design.pattern.adapter;

/**
 * 适配器模式：从一个接口转成另一个接口
 * 装饰者模式：不改变接口，但加入责任
 * 外观模式：让接口更简单
 *
 * @author liufeng
 */
public interface Duck {
    /**
     * 呱呱叫
     */
    void quack();

    /**
     * 鸭子飞行
     */
    void fly();


}

/**
 * 火鸡
 */
interface Turkey {
    /**
     * 咕咕叫
     */
    void gobble();

    /**
     * 飞行
     */
    void fly();
}

/**
 * 绿头鸭
 */
class MallardDuck implements Duck {

    @Override
    public void quack() {
        System.out.println("呱呱～");
    }


    @Override
    public void fly() {
        System.out.println("飞起来～");
    }
}

/**
 * 火鸡-实现类
 */
class WIldTurkey implements Turkey {

    @Override
    public void gobble() {
        System.out.println("咕咕～");
    }

    @Override
    public void fly() {
        System.out.println("只能飞很短的距离！");
    }
}

/**
 * 装饰器，将火鸡伪装承鸭子
 */
class TurkeyAdapter implements Duck {

    Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        turkey.fly();
    }
}
