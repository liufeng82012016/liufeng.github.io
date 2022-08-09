package liufeng.design.pattern.decorate;

/**
 * 装饰者模式
 * 特征
 * 1. 装饰者和被装饰对象有相同的超类
 * 2. 可以用一个或多个装饰者包装一个对象
 * 3. 装饰者可以在所委托被装饰者的行为或之后，加上自己的行为，以达成特定的目的
 * 4. 对象可以在任何时候被装饰，所以可以运行时动态的、不限量的用你喜欢的装饰者来装饰对象
 * <p>
 * 突然想到InputStream
 *
 * @author liufeng
 */
public abstract class Beverage {
    String description = "unknown beverage";

    public String getDescription() {
        return description;
    }

    /**
     * 计算饮料的价格
     *
     * @return 饮料价格
     */
    public abstract int cast();

}

// 简单实现 ---------------- start  ----------------

/**
 * 深焙咖啡
 */
class DarkRoast extends Beverage {

    @Override
    public int cast() {
        return 10;
    }
}

/**
 * 深焙咖啡+摩卡
 */
class Mocha extends Beverage {
    private Beverage beverage;

    @Override
    public int cast() {
        return 8 + beverage.cast();
    }
}

/**
 * 深焙咖啡+摩卡+奶泡
 */
class Whip extends Beverage {
    private Beverage beverage;

    @Override
    public int cast() {
        return 6 + beverage.cast();
    }
}

// 简单实现 ---------------- end ----------------
// 装饰者实现 ---------------- start  ----------------
abstract class CondimentDecorator extends Beverage {
    protected Beverage wrapperObj;

    /**
     * 获取饮料描述
     *
     * @return 饮料描述
     */
    @Override
    public abstract String getDescription();
}

/**
 * 浓缩咖啡
 */
class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public int cast() {
        return 1;
    }
}

/**
 * 另外一种名为HouseBlend的咖啡
 */
class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "HouseBlend";
    }

    @Override
    public int cast() {
        return 2;
    }
}

/**
 * 摩卡咖啡
 */
class Mocha2 extends CondimentDecorator {

    public Mocha2(Beverage beverage) {
        this.wrapperObj = beverage;
    }

    @Override
    public int cast() {
        return 3 + wrapperObj.cast();
    }

    @Override
    public String getDescription() {
        return wrapperObj.getDescription() + ", Mock2";
    }
}
